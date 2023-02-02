package com.vertie.modules.splash

import androidx.lifecycle.MutableLiveData
import com.vertie.data.user.Session
import com.vertie.data.user.source.UserDataSource
import com.vertie.di.qualifiers.Repo
import com.vertie.javacode.singleton.SingletonClass
import com.vertie.modules.base.BaseViewModel
import com.vertie.utils.connectivityUtils.OnlineChecker
import com.vertie.utils.providers.BaseResourceProvider
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val onlineChecker: OnlineChecker,
    private val resourceProvider: BaseResourceProvider,
    @Repo private val userRepository: UserDataSource,
    private val session: Session
        )
    : BaseViewModel() {
    var navigateToDashboard = MutableLiveData<Boolean>()
    val TAG = this.javaClass.simpleName
    private val delayInMillis: Long = 1000
    var sessionDidInit = MutableLiveData<Boolean>()
    var navigateToLoadData = MutableLiveData<Boolean>()
    var navigateToLogin = MutableLiveData<Boolean>()


    fun checkUser(splashActivity: SplashActivity?) {
        if (!session.token.isNullOrEmpty()) { //dashboard
            navigateToDashboard.value = true
            SingletonClass.getInstance().email = session.currentUser?.email
//            SingletonClass.getInstance().userId = "73270e39-e6c1-42d4-91d2-2bc04c33cbb9"//
            if (splashActivity != null) {
                SingletonClass.getInstance().userId = splashActivity.getSharedPreferences("user", 0).getString("user_id", null)
            }
//            SingletonClass.getInstance().userId = session.currentUser?.id

//            if(position == 0){
//                editor.putBoolean("isFamilyMembar", false);
//                editor.putString("user_id", item.getUserId());
//                SingletonClass.getInstance().userId = item.getUserId();
//            }else {
//                editor.putString("user_email", "");
//                editor.putString("user_id", item.getId());
//                SingletonClass.getInstance().userId = item.getId();
//                SingletonClass.getInstance().email="";
//                editor.putBoolean("isFamilyMembar", true);
//            }
        } else { //login
            navigateToLogin.value = true
        }
    }

    fun initSession(){
        userRepository.initSession()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : CompletableObserver {
                    override fun onSubscribe(d: Disposable) {
                        disposable.add(d)
                    }

                    override fun onComplete() {
                        sessionDidInit.value = true
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        handleDataError(tag = TAG, error = e)
                    }

                }

            )
    }
}