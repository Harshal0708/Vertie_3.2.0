package com.vertie.modules.login

import androidx.lifecycle.MutableLiveData
import com.vertie.R
import com.vertie.data.user.*
import com.vertie.data.user.source.UserDataSource
import com.vertie.di.qualifiers.Repo
import com.vertie.javacode.singleton.SingletonClass
import com.vertie.modules.base.BaseViewModel
import com.vertie.utils.bindingUtils.EmailUtils.isValidEmail
import com.vertie.utils.extenstions.notifyObserver
import com.vertie.utils.providers.BaseResourceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val resourceProvider: BaseResourceProvider,
    @Repo private val userRepository: UserDataSource,
    private val session: Session
) : BaseViewModel(){

    val errorBinder : MutableLiveData<LoginErrorBinder?> = MutableLiveData<LoginErrorBinder?>(
        LoginErrorBinder()
    )
    val email : MutableLiveData<String> = MutableLiveData<String>()
    val password : MutableLiveData<String> = MutableLiveData<String>()
    val emailError : MutableLiveData<String> = MutableLiveData<String>()
    val passwordError : MutableLiveData<String> = MutableLiveData<String>()
    val errorLogin : MutableLiveData<String> = MutableLiveData<String>("")
    val goToDashBoard : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val goToChieldActivity : MutableLiveData<Boolean> = MutableLiveData<Boolean>()
//    lateinit var familyList : List<FamilyMember>
    lateinit var proxyUsers : List<Userr>

    fun login(){

        email.value = "sachin.prajapati0533@gmail.com"
        password.value = "P@ssw0rd"

//        email.value = "loricmarshall16@gmail.com"
//        password.value = "M490RN8"

        var enter : Boolean = true

        emailError.value = ""
        passwordError.value = ""

        // Validate email
        val mail = validateEmail()

        // Validate password
        val pass = validatePassword()

        if(mail&&pass){
            // Begin Login
            loading.value = true
            disposable.add(
                userRepository.userLogin(
                    loginRequest()
                ).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if(it.resultCode == "-1"){
                            loading.value = false
                            //errorLogin.value = it.resultMessage
                            snackMessage.value = it.resultMessage
                        }else{
                            //save to local
                            SingletonClass.getInstance().email = email.value
                            val myArrayList = ArrayList<Userr>()
                            it.user?.let { it1 -> myArrayList.add(it1) }
                            if(it?.proxyUsers?.size!! > 0){
                                for (item in it?.proxyUsers!!){
                                    item.registration?.let { it1 -> myArrayList.add(it1) }
                                }
                                proxyUsers = myArrayList
                                goToChieldActivity.value = true
                                saveToPrefs(Userr(email = email.value,password = password.value),it,true)
                            }else{
                                goToChieldActivity.value = false
                                saveToPrefs(Userr(email = email.value,password = password.value),it,false)
                                SingletonClass.getInstance().userId = it?.user?.id
                            }
                        }
                    },{
                        handleDataError("tag",it)
                    })
            )
        }
    }

    fun saveToPrefs(user : Userr,response: LoginResponse,isChield:Boolean){
        disposable.add(
            userRepository.setUserPrefs(
                user,response.jwtToken!!,response.user?.id!!
            ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loading.value = false
                    if(isChield){}else{goToDashBoard.value = true}
                },{
                    handleDataError("tag",it)
                })
        )
    }

//    fun saveToSession(user : User,token : String){
//        session.token = token
//        session.currentUser = user
//    }

    fun loginRequest() : LoginRequest{
        return LoginRequest(
            email = email.value,
            password = password.value
        )
    }

    fun forgetPassword(){
    }

    fun validateEmail() : Boolean{
        //val errorBinder = LoginErrorBinder()
        var enter = true
        if(email.value.isNullOrEmpty()){
            enter = false
            errorBinder.value?.emailError = resourceProvider.getString(R.string.emailrequired)
            errorBinder.notifyObserver()
        }else{
            if(!isValidEmail(email.value!!)){
                enter = false
                errorBinder.value?.emailError = resourceProvider.getString(R.string.emailisnotvalid)
                errorBinder.notifyObserver()
            }else{
                enter = true
                errorBinder.value?.emailError = ""
                errorBinder.notifyObserver()
            }
        }
        return enter
    }

    fun validatePassword() : Boolean{
        //val errorBinder = LoginErrorBinder()
        var enter = true
        if(password.value.isNullOrEmpty()){
            enter = false
            errorBinder.value?.passwordError = resourceProvider.getString(R.string.passwordrequired)
            errorBinder.notifyObserver()
        }else{
            enter = true
            errorBinder.value?.passwordError = ""
            errorBinder.notifyObserver()
        }
        return enter
    }

    data class LoginErrorBinder(
        var emailError : String? = null,
        var passwordError : String? = null
    )

}