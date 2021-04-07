package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository

class GetUserIdUseCase: UseCase<Long>() {
    override suspend fun executeOnBackground(): Long {
        return appRepository!!.getUserId()
    }
}