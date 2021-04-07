package com.example.green_deli.domain.pojo.response

import com.example.green_deli.common.ApiTag
import com.google.gson.annotations.SerializedName

/**
 * Приходит с сервера
 */
data class ApiFood(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("tag")
    val tags: List<ApiTag>,
    @SerializedName("category")
    val category: ApiCategory,
    @SerializedName("mealEV")
    val foodEV: ApiFoodEV,
    @SerializedName("image")
    val image: String
)

data class ApiFoodEV(
    @SerializedName("id")
    val id: Long,
    @SerializedName("lipids")
    val lipids: Double,
    @SerializedName("proteins")
    val proteins: Double,
    @SerializedName("hydr")
    val hydr: Double,
    @SerializedName("energy")
    val energy: Double,
    @SerializedName("about")
    val about: String
)