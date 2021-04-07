package com.example.green_deli.presentation.fragments.basket

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.green_deli.R
import com.example.green_deli.domain.pojo.response.ApiFood
import com.example.green_deli.presentation.fragments.AbstractFragment
import kotlinx.android.synthetic.main.fragment_basket.*

class BasketFragment : AbstractFragment<BasketViewModel>(R.layout.fragment_basket) {

    val adapter = BasketItemAdapter()

    override val viewModel by lazy {
        createViewModel<BasketViewModel>()
    }

    val basketSharedViewModel by lazy {
        ViewModelProvider(requireActivity()).get(BasketViewModel::class.java)
    }

    override fun fragmentBlock() {
        btn_create_order.setOnClickListener {
            if (adapter.itemCount != 0) {
                basketSharedViewModel.order()
            } else {
                Toast.makeText(context, "Корзина пуста!", Toast.LENGTH_SHORT).show()
            }
        }
        recycler_basket.adapter = adapter
        basketSharedViewModel.content.observe(requireActivity()) {
            adapter.update(listToPairs(it))
        }
        basketSharedViewModel.orderSuccess.observe(requireActivity()) {
            try {
                if (it != null) {
                    if (it) {
                        Toast.makeText(context, "Заказ успешно создан", Toast.LENGTH_SHORT).show()
                        basketSharedViewModel.orderSuccess.value = null
                    } else {
                        Toast.makeText(context, "Вы не авторизованы", Toast.LENGTH_SHORT).show()
                        basketSharedViewModel.orderSuccess.value = null
                    }
                }
            } catch (e: java.lang.NullPointerException) {}
        }
        btn_clear.setOnClickListener {
            adapter.clear()
            basketSharedViewModel.notifyBasketWasCleared()
        }
    }

    override fun BasketViewModel.observeBlock() {
        error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun BasketViewModel.processBlock() {
    }

    private fun listToPairs(pairs: Pair<MutableList<ApiFood>, MutableList<Int>>): MutableList<Pair<ApiFood, Int>> {
        return mutableListOf<Pair<ApiFood, Int>>().apply {
            pairs.first.forEachIndexed { i, it ->
                add(it to pairs.second[i])
            }
        }
    }

}