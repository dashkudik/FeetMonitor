package com.example.green_deli

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

object Common {
    const val BASE_URL = "http://5.200.47.32:1337"
    const val PREF_NAME = "Super"
    const val KEY_LOGIN = "Login key"
    const val KEY_ID = "Id key"

    const val UNKNOWN_ERROR = "Неизвестная ошибка"
    const val SIGN_IN_ERROR = "Ошибка при авторизации"
    const val SIGN_UP_ERROR = "Ошибка при регистрации"
    const val ORDERS_ERROR = "Невозможно получить заказы"
    const val GENERATE_ERROR = "Не удалось сгенерировать блюда"

    const val INFO_1 = "Белки:"
    const val INFO_2 = "Жиры:"
    const val INFO_3 = "Углеводы:"
    const val INFO_4 = "Калорийность:"
    const val INFO_5 = "Цена:"

    fun <T : View> View.view(id: Int): T {
        return findViewById(id)
    }

    fun View.gone() {
        this.visibility = View.GONE
    }

    fun View.invisible() {
        this.visibility = View.INVISIBLE
    }

    fun View.visible() {
        this.visibility = View.VISIBLE
    }

}