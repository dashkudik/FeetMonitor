package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository

class GetUserLoginUseCase: UseCase<String?>() {
    override suspend fun executeOnBackground(): String? {
        return appRepository?.getUserLogin()
    }
}