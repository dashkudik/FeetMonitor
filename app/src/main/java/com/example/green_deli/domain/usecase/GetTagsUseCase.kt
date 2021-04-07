package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository
import com.example.green_deli.common.ApiTag

class GetTagsUseCase: UseCase<List<ApiTag>>() {
    override suspend fun executeOnBackground(): List<ApiTag> {
        return appRepository!!.getTags().execute().body()!!
    }
}