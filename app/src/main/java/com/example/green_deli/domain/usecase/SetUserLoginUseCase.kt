package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository
import kotlin.properties.Delegates

class SetUserLoginUseCase: UseCase<Boolean>() {
    var login: String by Delegates.notNull()
    override suspend fun executeOnBackground(): Boolean {
        appRepository?.setUserLogin(login)
        return true
    }
}