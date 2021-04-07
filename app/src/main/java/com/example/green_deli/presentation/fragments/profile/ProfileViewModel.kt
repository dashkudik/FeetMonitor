package com.example.green_deli.presentation.fragments.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.green_deli.Common
import com.example.green_deli.Common.SIGN_IN_ERROR
import com.example.green_deli.Common.SIGN_UP_ERROR
import com.example.green_deli.Common.UNKNOWN_ERROR
import com.example.green_deli.domain.pojo.response.ApiUser
import com.example.green_deli.domain.usecase.*
import kotlin.math.log

class ProfileViewModel: ViewModel() {
    private val getUserLoginUseCase = GetUserLoginUseCase()
    private val setUserLoginUseCase = SetUserLoginUseCase()
    private val setUserIdUseCase = SetUserIdUseCase()
    private val signUpUseCase = SignUpUseCase()
    private val signOutUseCase = SignOutUseCase()
    private val signInUseCase = SignInUseCase()
    private val getUserInfoUseCase = GetUserInfoUseCase()

    val error by lazy { MutableLiveData<String>() }
    val state by lazy { MutableLiveData<ProfileFragment.State>() }
    val signUpSuccess by lazy { MutableLiveData<Boolean>() }
    val userInfo by lazy { MutableLiveData<ApiUser>() }

    fun fetchUserLogin() {
        getUserLoginUseCase.execute {
            onFail {
                error.value = UNKNOWN_ERROR
            }
            onComplete {
                if (it != null) {
                    state.value = ProfileFragment.State.OK.apply {
                        login = it
                    }
                } else {
                    state.value = ProfileFragment.State.AUTH
                }
            }
        }
    }

    fun signUp(
        phone: String, login: String, password: String
    ) {
        signUpUseCase.apply {
            this.phone = phone
            this.login = login
            this.password = password
        }.execute {
            onFail {
                error.value = UNKNOWN_ERROR
            }
            onComplete {
                Log.i("TEST", "Id --> $it")
                signUpSuccess.value = it != -1
                if (it != -1) {
                    setUserLoginUseCase.apply {
                        this.login = login
                    }.execute({})
                    setUserIdUseCase.apply {
                        this.id = it.toLong()
                    }.execute {
                        state.value = ProfileFragment.State.OK.apply {
                            this.login = login
                        }
                    }
                } else {
                    error.value = SIGN_UP_ERROR
                }
            }
        }
    }

    fun getUserInfo() {
        getUserInfoUseCase.execute {
            onFail { error.value = "Не удалось загрузить данные пользователя" }
            onComplete {
                userInfo.value = it
            }
        }
    }


    fun signOut() {
        signOutUseCase.execute {
            onFail {
                error.value = UNKNOWN_ERROR
            }
            onComplete {
                state.value = ProfileFragment.State.AUTH
            }
        }
    }

    fun signIn(login: String, password: String) {
        signInUseCase.apply {
            this.login = login
            this.password = password
        }.execute {
            onFail {
                error.value = UNKNOWN_ERROR
            }
            onComplete {
                if (it != -1) {
                    setUserLoginUseCase.apply {
                        this.login = login
                    }.execute {
                        state.value = ProfileFragment.State.OK.apply {
                            this.login = login
                        }
                    }
                } else {
                    error.value = SIGN_IN_ERROR
                }
            }
        }
    }

    fun setState(value: ProfileFragment.State) {
        state.value = value
        Log.i("TEST", "2")
    }

    fun getState(): ProfileFragment.State? {
        return state.value
    }
}