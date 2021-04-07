package com.example.green_deli.domain.pojo

import com.example.green_deli.common.ApiTag
import com.example.green_deli.domain.pojo.response.ApiCategory
import com.google.gson.annotations.SerializedName

data class Generator(
    @SerializedName("type")
    val categories: List<ApiCategory>,
    @SerializedName("tags")
    val tags: List<ApiTag>
    )