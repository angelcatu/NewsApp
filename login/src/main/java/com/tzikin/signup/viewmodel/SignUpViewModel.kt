package com.tzikin.signup.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.*
import com.google.firebase.auth.FirebaseUser
import com.tzikin.core.repository.login.AuthAppRepository
import com.tzikin.login.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {


    private var authAppRepository: AuthAppRepository = AuthAppRepository()



    fun registerUser(){
        viewModelScope.launch {
            authAppRepository.registerUser(email = email.value.toString(), password = password.value.toString())
        }
    }


    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password

    private val _confirmationPassword = MutableLiveData("")
    val confirmationPassword: LiveData<String> = _confirmationPassword

    private val _allValuesCorrect = MutableLiveData(false)
    val allValuesCorrect: LiveData<Boolean> = _allValuesCorrect

    fun setEmail(value: String){
        _email.value = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun setConfirmationPassword(value: String){
        _confirmationPassword.value = value
    }

    fun getErrorFromInputs(): String {
        if(!Patterns.EMAIL_ADDRESS.matcher(email.value.toString()).matches()){
            return getApplication<Application?>().getString(com.tzikin.core.R.string.txt_email_error)
        }

        if(password.value?.length!! < 6) {
            return getApplication<Application?>().getString(com.tzikin.core.R.string.txt_password_must_be_6_characters)
        }

        if(password.value != confirmationPassword.value){
            return getApplication<Application?>().getString(com.tzikin.core.R.string.txt_password_doesnt_match)
        }

        _allValuesCorrect.value = true
        return ""
    }

}