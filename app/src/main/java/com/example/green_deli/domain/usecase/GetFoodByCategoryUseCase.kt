package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository
import com.example.green_deli.domain.pojo.response.ApiCategory
import com.example.green_deli.domain.pojo.response.ApiFood
import kotlin.properties.Delegates

class GetFoodByCategoryUseCase: UseCase<List<ApiFood>>() {
    var category: ApiCategory by Delegates.notNull()
    override suspend fun executeOnBackground(): List<ApiFood> {
        return appRepository!!.getMealsByCategory(category).execute().body()!!
    }
}