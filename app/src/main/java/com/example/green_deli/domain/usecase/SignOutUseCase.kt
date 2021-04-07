package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository

class SignOutUseCase: UseCase<Boolean>() {
    override suspend fun executeOnBackground(): Boolean {
        appRepository?.setUserLogin(null)
        return true
    }
}