package com.example.green_deli.domain.pojo.response

import com.google.gson.annotations.SerializedName

data class ApiCategory(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String
    )