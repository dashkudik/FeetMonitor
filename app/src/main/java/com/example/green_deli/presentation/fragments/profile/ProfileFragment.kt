package com.example.green_deli.presentation.fragments.profile

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.green_deli.Common.gone
import com.example.green_deli.Common.view
import com.example.green_deli.Common.visible
import com.example.green_deli.R
import com.example.green_deli.presentation.fragments.AbstractFragment
import com.example.green_deli.presentation.fragments.my_orders.MyOrdersViewModel
import kotlinx.android.synthetic.main.view_authorization.*
import kotlinx.android.synthetic.main.view_authorized.*
import kotlinx.android.synthetic.main.view_registration.*
import java.lang.IllegalArgumentException

class ProfileFragment : AbstractFragment<ProfileViewModel>(R.layout.fragment_profile) {

    override val viewModel by lazy {
        createViewModel<ProfileViewModel>()
    }

    override fun fragmentBlock() {
        btn_registration.setOnClickListener {
            viewModel.setState(State.REG)
        }
        btn_sign_up.setOnClickListener {
            viewModel.signUp(
                input_phone.text.toString(),
                input_login.text.toString(),
                input_password.text.toString()
            )
        }
        btn_sign_out.setOnClickListener {
            viewModel.signOut()
        }
        btn_sign_in.setOnClickListener {
            viewModel.signIn(
                a_input_login.text.toString(),
                a_input_password.text.toString(),
            )
        }
    }

    override fun ProfileViewModel.observeBlock() {
        state.observe(viewLifecycleOwner) {
            submitState(it)
        }
        error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        signUpSuccess.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, getString(R.string.successful), Toast.LENGTH_SHORT).show()
            }
        }
        userInfo.observe(viewLifecycleOwner) {
            tv_bonus_phone.text = "У вас ${it.bonus} бонусных баллов, ваш номер телефона: ${it.phone}"
        }
    }

    override fun ProfileViewModel.processBlock() {
        fetchUserLogin()
    }

    enum class State(var login: String? = null) {
        REG, AUTH, OK
    }

    fun submitState(state: State) {
        when (state) {
            State.REG -> {
                requireView().view<ConstraintLayout>(R.id.view_authorized).gone()
                requireView().view<ConstraintLayout>(R.id.view_authorization).gone()
                requireView().view<ConstraintLayout>(R.id.view_registration).visible()
            }
            State.AUTH -> {
                requireView().view<ConstraintLayout>(R.id.view_authorized).gone()
                requireView().view<ConstraintLayout>(R.id.view_authorization).visible()
                requireView().view<ConstraintLayout>(R.id.view_registration).gone()

            }
            State.OK -> {
                state.login?.let { login ->
                    requireView().view<ConstraintLayout>(R.id.view_authorized).visible()
                    requireView().view<ConstraintLayout>(R.id.view_authorization).gone()
                    requireView().view<ConstraintLayout>(R.id.view_registration).gone()
                    tv_login.text = " $login"
                    viewModel.getUserInfo()
                } ?: throw IllegalArgumentException("No login provided.")
            }
        }
    }

    companion object
}