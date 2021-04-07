package com.example.green_deli.domain.usecase

import com.example.green_deli.App.Companion.appRepository
import kotlin.properties.Delegates

class SetUserIdUseCase: UseCase<Boolean>() {
    var id: Long by Delegates.notNull()
    override suspend fun executeOnBackground(): Boolean {
        appRepository?.setUserId(id)
        return true
    }
}