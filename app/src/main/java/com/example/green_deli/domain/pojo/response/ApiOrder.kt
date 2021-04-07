package com.example.green_deli.domain.pojo.response

import com.example.green_deli.domain.pojo.OrderStatus
import com.google.gson.annotations.SerializedName

/**
 * Приходит с сервера
 */
data class ApiOrder(
    @SerializedName("food")
    val food: List<ApiFood>,
    @SerializedName("amount")
    val count: List<Int>,
    @SerializedName("status")
    val status: OrderStatus,
    @SerializedName("price")
    val totalPrice: Double
    )

/**
 * Я отправляю
 */
data class PostOrder(
    @SerializedName("food")
    val food: List<ApiFood>,
    @SerializedName("amount")
    val count: List<Int>,
    @SerializedName("login")
    val login: String
)