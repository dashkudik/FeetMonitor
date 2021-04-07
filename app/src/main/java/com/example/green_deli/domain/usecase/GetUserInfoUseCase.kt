package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository
import com.example.green_deli.domain.pojo.response.ApiUser

class GetUserInfoUseCase: UseCase<ApiUser>() {
    override suspend fun executeOnBackground(): ApiUser {
        return appRepository!!.getUserInfo(GetUserIdUseCase().executeOnBackground()).execute().body()!!
    }
}