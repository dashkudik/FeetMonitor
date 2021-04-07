package com.example.green_deli.gateway

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.green_deli.Common
import com.example.green_deli.Common.BASE_URL
import com.example.green_deli.Common.KEY_ID
import com.example.green_deli.Common.KEY_LOGIN
import com.example.green_deli.Common.PREF_NAME
import com.example.green_deli.common.ApiTag
import com.example.green_deli.domain.pojo.Generator
import com.example.green_deli.domain.pojo.SignInModel
import com.example.green_deli.domain.pojo.SignUpModel
import com.example.green_deli.domain.pojo.response.*
import com.example.green_deli.domain.repository.ApiRepository
import com.example.green_deli.domain.repository.AppRepository
import com.example.green_deli.presentation.MainActivity
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.log

class AppRepositoryImpl(context: Context): AppRepository {

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(3, TimeUnit.SECONDS)
        .readTimeout(2500, TimeUnit.MILLISECONDS)
        .writeTimeout(2, TimeUnit.SECONDS)
        .build()

    private val retrofit =
        Retrofit.Builder().
        baseUrl(BASE_URL).
        client(okHttpClient).
        addConverterFactory(GsonConverterFactory.create()).
        build()

    private val api = retrofit.create(ApiRepository::class.java)

    private val sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE)

    override fun order(order: PostOrder): Call<Unit> {
        return api.order(order)
    }

    override fun signIn(signInModel: SignInModel): Call<Int> {
        return api.signIn(signInModel)
    }

    override fun signUp(signUpModel: SignUpModel): Call<Int> {
        return api.signUp(signUpModel)
    }

    override fun getMealsByCategory(category: ApiCategory): Call<List<ApiFood>> {
        return api.getMealsByCategory(category)
    }

    override fun getCategories(): Call<List<ApiCategory>> {
        return api.getCategories()
    }

    override fun getRecommendMeals(generator: Generator): Call<List<ApiFood>> {
        return api.getRecommendMeals(generator)
    }

    override fun getOrders(id: Long): Call<List<ApiOrder>> {
        return api.getOrders(id)
    }

    override fun getTags(): Call<List<ApiTag>> {
        return api.getTags()
    }

    override suspend fun getUserLogin(): String? {
        return sharedPreferences.getString(KEY_LOGIN, null)
    }

    override fun setUserLogin(login: String?) {
        sharedPreferences.edit().putString(KEY_LOGIN, login).apply()
    }

    override fun setUserId(id: Long) {
        sharedPreferences.edit().putLong(KEY_ID, id).apply()
    }

    override fun getUserId(): Long {
        return sharedPreferences.getLong(KEY_ID, -1)
    }

    override fun getUserInfo(id: Long): Call<ApiUser> {
        return api.getUserInfo(id)
    }

}