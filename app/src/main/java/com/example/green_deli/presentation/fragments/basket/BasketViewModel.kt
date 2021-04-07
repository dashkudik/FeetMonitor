package com.example.green_deli.presentation.fragments.basket

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.green_deli.Common
import com.example.green_deli.Common.UNKNOWN_ERROR
import com.example.green_deli.domain.pojo.response.ApiFood
import com.example.green_deli.domain.usecase.SendOrderUseCase

class BasketViewModel: ViewModel() {
    private val sendOrderUseCase = SendOrderUseCase()

    val content by lazy {
        MutableLiveData<Pair<MutableList<ApiFood>, MutableList<Int>>>().apply {
            value = mutableListOf<ApiFood>() to mutableListOf()
        }
    }
    val orderSuccess by lazy {
        MutableLiveData<Boolean?>()
    }

    val error by lazy {
        MutableLiveData<String>()
    }

    fun notifyBasketWasCleared() {
        content.value = mutableListOf<ApiFood>() to mutableListOf()
    }

    fun notifyMealAdded(food: ApiFood) {
        var index = -1
        content.value!!.first.find {
            index++
            it.name == food.name
        }.let {
            with(content.value!!) {
                if (it != null) {
                    second[index].let {
                        apply {
                            second[index] = it + 1
                        }
                    }
                } else {
                    first.add(food)
                    second.add(DEFAULT_AMOUNT)
                }
            }
        }
    }

    fun order() {
        sendOrderUseCase.apply {
            this.amount = content.value!!.second
            this.food = content.value!!.first
        }.execute {
            onFail { error.value = UNKNOWN_ERROR }
            onComplete {
                orderSuccess.value = it
            }
        }
    }

    companion object {
        const val DEFAULT_AMOUNT = 1
    }

}