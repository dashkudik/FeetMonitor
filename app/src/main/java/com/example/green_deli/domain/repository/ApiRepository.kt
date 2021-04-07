package com.example.green_deli.domain.repository

import com.example.green_deli.Common.BASE_URL
import com.example.green_deli.common.ApiTag
import com.example.green_deli.domain.pojo.Generator
import com.example.green_deli.domain.pojo.SignInModel
import com.example.green_deli.domain.pojo.SignUpModel
import com.example.green_deli.domain.pojo.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiRepository {
    /**
     * Заказ
     */
    @POST("$BASE_URL/create_order")
    fun order(@Body order: PostOrder): Call<Unit>

    /**
     * Авторизация
     */
    @POST("$BASE_URL/auth")
    fun signIn(@Body singInModel: SignInModel): Call<Int>

    /**
     * Регистрация
     */
    @POST("$BASE_URL/signUp")
    fun signUp(@Body signUpModel: SignUpModel): Call<Int>

    /**
     * Просто запрос для показа меню
     */
    @POST("$BASE_URL/meals")
    fun getMealsByCategory(@Body category: ApiCategory): Call<List<ApiFood>>

    /**
     * Просто запрос для показа меню
     */
    @GET("$BASE_URL/category")
    fun getCategories(): Call<List<ApiCategory>>

    /**
     * Запрос рекомендации
     */
    @POST("$BASE_URL/recommend_meals")
    fun getRecommendMeals(@Body generator: Generator): Call<List<ApiFood>>

    /**
     * Мои заказы
     */
    @GET("$BASE_URL/my_orders?")
    fun getOrders(@Query("id") id: Long): Call<List<ApiOrder>>

    /**
     * Получение тегов
     */
    @GET("$BASE_URL/tag")
    fun getTags(): Call<List<ApiTag>>

    /**
     * Данные юзера
     */
    /**
     * Мои заказы
     */
    @GET("$BASE_URL/user?")
    fun getUserInfo(@Query("id") id: Long): Call<ApiUser>

}