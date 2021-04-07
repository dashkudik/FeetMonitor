package com.example.green_deli.presentation.fragments.my_orders

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.green_deli.Common
import com.example.green_deli.Common.ORDERS_ERROR
import com.example.green_deli.domain.pojo.response.ApiOrder
import com.example.green_deli.domain.usecase.GetOrdersUseCase

class MyOrdersViewModel: ViewModel() {
    private val getOrdersUseCase = GetOrdersUseCase()

    val orders by lazy {
        MutableLiveData<List<ApiOrder>>()
    }
    val error by lazy {
        MutableLiveData<String>()
    }

    fun getOrders() {
        getOrdersUseCase.execute {
            onFail { error.value = ORDERS_ERROR }
            onComplete {
                Log.i("TEST_SHIT", it.toString())
                orders.value = it
            }
        }
    }
}