package com.example.green_deli.presentation.fragments.my_orders

import android.widget.Toast
import com.example.green_deli.Common.invisible
import com.example.green_deli.Common.visible
import com.example.green_deli.R
import com.example.green_deli.presentation.adapters.MyOrdersAdapter
import com.example.green_deli.presentation.fragments.AbstractFragment
import kotlinx.android.synthetic.main.fragment_my_orders.*

class MyOrdersFragment : AbstractFragment<MyOrdersViewModel>(R.layout.fragment_my_orders) {

    private val adapter = MyOrdersAdapter()

    override val viewModel by lazy {
        createViewModel<MyOrdersViewModel>()
    }

    override fun fragmentBlock() {
        recycler_orders.adapter = adapter

    }

    override fun MyOrdersViewModel.observeBlock() {
        error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        orders.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                tv_no.visible()
                recycler_orders.invisible()
            } else {
                tv_no.invisible()
                recycler_orders.visible()
            }
            adapter.update(it.toMutableList())
        }
    }

    override fun MyOrdersViewModel.processBlock() {
        getOrders()
    }

    companion object
}