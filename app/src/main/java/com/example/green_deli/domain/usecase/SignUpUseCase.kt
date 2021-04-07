package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository
import com.example.green_deli.domain.pojo.SignUpModel
import kotlin.properties.Delegates

class SignUpUseCase: UseCase<Int>() {
    var phone: String by Delegates.notNull()
    var login: String by Delegates.notNull()
    var password: String by Delegates.notNull()


    override suspend fun executeOnBackground(): Int {
        val signUpModel = SignUpModel(phone, login, password)
        return appRepository!!.signUp(signUpModel).execute().body()!!
    }
}