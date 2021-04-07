package com.example.green_deli.domain.pojo.response

import com.google.gson.annotations.SerializedName

data class ApiUser(
    @SerializedName("id")
    val id: Long,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("bonus")
    val bonus: Double,
)