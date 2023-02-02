package com.vertie.utils.networkUtils

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.util.Log
import com.vertie.Constants
import com.vertie.R
import com.vertie.data.user.Session
import com.vertie.modules.login.LoginActivity
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Created by sarahussien on 2/21/19.
 */

class RetrofitInterceptor @Inject constructor(
        private val applicationContext: Context,
        private val session: Session,
        ) : Interceptor {

    private val TAG = this.javaClass.simpleName

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val header = getToken()
            request = if (header.isEmpty()) {
            request.newBuilder()
                .build()
        } else {
            request.newBuilder()
                .addHeader("Authorization", "Bearer $header")
                .build()
        }

        Log.d(TAG, "{Authorization: ${request.header("Authorization")}}")

        try {
            val response = chain.proceed(request)
            // errors handling
            if (!response.isSuccessful) {
                Log.d(TAG, "intercept: ${response.code()}")
                val json = response.body()?.string()
                if (!json.isNullOrEmpty()) {
                    try {
                        val obj = JSONObject(json)
                        Log.d(TAG, obj.toString())
                        val error =
                                (obj.getJSONArray("Errors")[0] as? JSONObject)?.getString("ErrorMessage")
                        if (!error.isNullOrEmpty()) {
                            val intent = Intent()
                            intent.action = Constants.ERROR_BROADCAST_KEY
                            intent.putExtra(
                                    Constants.ERROR_BROADCAST_EXTRA_MESSAGE,
                                    error
                            )
                            applicationContext.sendBroadcast(intent)
                        } else {
//                            showErrorWithCode(response.code(), request)
                        }
                    } catch (t: Throwable) {
                        Log.e(
                                TAG,
                                "Could not parse malformed JSON: \"$json\""
                        )
//                        showErrorWithCode(response.code(), request)
                    }
                } else {
//                    showErrorWithCode(response.code(), request)
                }
            }
            return response
        } catch (e: Exception) { // no available response case
            Log.e(TAG, "Exception ${Log.getStackTraceString(e)}")
            val intent = Intent()
            intent.action = Constants.ERROR_BROADCAST_KEY
            if (e is SocketTimeoutException || e is UnknownHostException || e is ConnectException) {
                intent.putExtra(
                        Constants.ERROR_BROADCAST_EXTRA_MESSAGE,
                        applicationContext.getString(R.string.connectionError)
                )
            } else {
                intent.putExtra(
                        Constants.ERROR_BROADCAST_EXTRA_MESSAGE,
                        applicationContext.getString(R.string.genericNetworkError)
                )
            }
            applicationContext.sendBroadcast(intent)
            return Response.Builder().build()
        }

    }

    private fun showErrorWithCode(code: Int, request: Request) {
        var intent = Intent()
        intent.action = Constants.ERROR_BROADCAST_KEY
        when (code) {
            500 -> { // server error
                intent.putExtra(
                        Constants.ERROR_BROADCAST_EXTRA_MESSAGE,
                        applicationContext.getString(R.string.serverError)
                )
            }
            401 -> { // token expiration
                if (request.url().url().path.contains(Constants.LOGIN)) {
                    intent.putExtra(
                            Constants.ERROR_BROADCAST_EXTRA_MESSAGE,
                            applicationContext.getString(R.string.wrongUsernameOrPassword)
                    )
                } else {

                                intent = LoginActivity.newIntent(applicationContext)
                                intent.putExtra(
                                    Constants.ERROR_BROADCAST_EXTRA_MESSAGE,
                                    applicationContext.getString(R.string.authError)
                                )
                                intent.flags = FLAG_ACTIVITY_NEW_TASK

                                applicationContext.startActivity(intent)

                }
            }

            404 -> { // Not found
                intent.putExtra(
                        Constants.ERROR_BROADCAST_EXTRA_MESSAGE,
                        applicationContext.getString(R.string.notFound)
                )
            }
            else -> {
                intent.putExtra(
                        Constants.ERROR_BROADCAST_EXTRA_MESSAGE,
                        applicationContext.getString(R.string.genericNetworkError)
                )
            }
        }
        applicationContext.sendBroadcast(intent)
    }

    private fun getToken(): String{
      System.out.println(session.token)
      return session.token ?: ""
    }

}
