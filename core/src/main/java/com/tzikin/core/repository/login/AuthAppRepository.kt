package com.tzikin.core.repository.login

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.tzikin.core.helpers.RequestState

/**
 * @author Angel Elias on 24/09/22.
 * Copyright (c) 2022 NewsApp . All rights reserved.
 **/
class AuthAppRepository {

    private var userLiveData: MutableLiveData<FirebaseUser?> = MutableLiveData()

    val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }


    fun registerUser(email: String, password: String) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userLiveData.postValue(firebaseAuth.currentUser)
                } else {
                }
            }
    }

    fun login(email: String, password: String): MutableLiveData<RequestState<FirebaseUser?>> {
        val requestState = MutableLiveData<RequestState<FirebaseUser?>>()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    requestState.value = RequestState.Success(userLiveData.value)
                }
            }.addOnFailureListener {
                requestState.value = RequestState.Error("User or password incorrect!")
            }
        return requestState

    }
}