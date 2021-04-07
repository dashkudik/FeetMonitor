package com.example.green_deli.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


abstract class AbstractFragment<T : ViewModel> constructor(
    private val layoutId: Int
) : Fragment() {

    protected abstract val viewModel: T
    protected abstract fun fragmentBlock()
    protected abstract fun T.observeBlock()
    protected abstract fun T.processBlock()

    protected inline fun <reified T : ViewModel> createViewModel(): T {
        return ViewModelProvider(this).get(T::class.javaObjectType)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBlock()
        viewModel.observeBlock()
        viewModel.processBlock()
    }

    protected fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}