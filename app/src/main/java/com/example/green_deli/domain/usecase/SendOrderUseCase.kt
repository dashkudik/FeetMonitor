package com.example.green_deli.domain.usecase

import android.util.Log
import com.example.green_deli.App.Companion.appRepository
import com.example.green_deli.domain.pojo.response.ApiFood
import com.example.green_deli.domain.pojo.response.PostOrder
import kotlin.properties.Delegates

class SendOrderUseCase: UseCase<Boolean>() {
    var food: List<ApiFood> by Delegates.notNull()
    var amount: List<Int> by Delegates.notNull()
    override suspend fun executeOnBackground(): Boolean {
        Log.i("TEST_LOGIN", (GetUserLoginUseCase().executeOnBackground() == null).toString())
        val postOrderModel = PostOrder(food, amount, GetUserLoginUseCase().executeOnBackground() ?: return false)
        appRepository!!.order(postOrderModel).execute()
        return true
    }
}