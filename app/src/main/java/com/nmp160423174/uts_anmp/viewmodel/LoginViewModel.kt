package com.nmp160423174.uts_anmp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    val statusLoginLD = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        if(username=="student" && password == "123") {
            statusLoginLD.value = true
        } else {
            statusLoginLD.value = false
        }
    }

}