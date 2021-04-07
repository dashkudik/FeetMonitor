package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository
import com.example.green_deli.domain.pojo.response.ApiCategory

class GetCategoriesUseCase: UseCase<List<ApiCategory>>() {
    override suspend fun executeOnBackground(): List<ApiCategory> {
        return appRepository!!.getCategories().execute().body()!!
    }
}