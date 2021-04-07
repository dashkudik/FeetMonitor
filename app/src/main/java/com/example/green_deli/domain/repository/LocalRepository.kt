package com.example.green_deli.domain.repository

interface LocalRepository {
    /**
     * Возвращает null если пользователь не авторизован
     */
    suspend fun getUserLogin(): String?

    /**
     * Запоминаем логин пользователя
     */
    fun setUserLogin(login: String?)

    /**
     * Запоминаем id пользователя
     */
    fun setUserId(id: Long)

    /**
     * Возвращает -1 если пользователь не авторизован
     */
    fun getUserId(): Long
}