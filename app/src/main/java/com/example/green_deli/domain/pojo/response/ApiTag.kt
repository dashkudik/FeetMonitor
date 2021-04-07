package com.example.green_deli.common

import com.google.gson.annotations.SerializedName

data class ApiTag(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
    )