package com.example.green_deli.domain.usecase

import android.util.Log
import com.example.green_deli.App.Companion.appRepository
import com.example.green_deli.common.ApiTag
import com.example.green_deli.domain.pojo.Generator
import com.example.green_deli.domain.pojo.response.ApiCategory
import com.example.green_deli.domain.pojo.response.ApiFood
import kotlin.properties.Delegates

class GenerateFoodUseCase: UseCase<List<ApiFood>>() {
    var categories: List<ApiCategory> by Delegates.notNull()
    var tags: List<ApiTag> by Delegates.notNull()

    override suspend fun executeOnBackground(): List<ApiFood> {
        Log.i("TTT", categories.toString())
        Log.i("TTT", tags.toString())
        return appRepository!!.getRecommendMeals(Generator(categories, tags)).execute().body()!!
    }
}