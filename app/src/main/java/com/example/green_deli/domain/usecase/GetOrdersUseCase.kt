package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository
import com.example.green_deli.domain.pojo.response.ApiOrder

class GetOrdersUseCase: UseCase<List<ApiOrder>>() {
    override suspend fun executeOnBackground(): List<ApiOrder> {
        return appRepository!!.getOrders(GetUserIdUseCase().executeOnBackground()).execute().body()!!
    }
}