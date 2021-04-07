package com.example.green_deli.presentation.fragments.menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.green_deli.Common.UNKNOWN_ERROR
import com.example.green_deli.domain.pojo.response.ApiCategory
import com.example.green_deli.domain.pojo.response.ApiFood
import com.example.green_deli.domain.usecase.GetCategoriesUseCase
import com.example.green_deli.domain.usecase.GetFoodByCategoryUseCase
import kotlinx.android.synthetic.main.fragment_profile.view.*

class MenuViewModel: ViewModel() {
    private val getCategoriesUseCase = GetCategoriesUseCase()
    private val getFoodByCategoryUseCase = GetFoodByCategoryUseCase()

    val error by lazy { MutableLiveData<String>() }
    val categories by lazy { MutableLiveData<List<ApiCategory>>() }
    val currentFood by lazy { MutableLiveData<List<ApiFood>>() }

    fun fetchCategories() {
        getCategoriesUseCase.execute {
            onFail {
                error.value = UNKNOWN_ERROR
            }
            onComplete {
                categories.value = it
            }
        }
    }

    fun getFood(category: ApiCategory) {
        getFoodByCategoryUseCase.apply {
            this.category = category
        }.execute {
            onFail { error.value = it.message }
            onComplete {
                currentFood.value = it
            }
        }
    }

}