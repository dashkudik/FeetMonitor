package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository
import com.example.green_deli.domain.pojo.SignInModel
import kotlin.properties.Delegates

class SignInUseCase: UseCase<Int>() {
    var login: String by Delegates.notNull()
    var password: String by Delegates.notNull()

    override suspend fun executeOnBackground(): Int {
        val signInModel = SignInModel(login, password)
        return appRepository!!.signIn(signInModel).execute().body()!!
    }
}