package com.example.green_deli.presentation.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.green_deli.R
import com.example.green_deli.domain.pojo.response.ApiCategory
import com.example.green_deli.presentation.adapters.FoodMenuAdapter
import com.example.green_deli.presentation.fragments.basket.BasketViewModel
import kotlinx.android.synthetic.main.fragment_food.*

class FoodFragment(private val category: ApiCategory): Fragment() {

    val menuViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MenuViewModel::class.java)
    }
    val basketViewModel by lazy {
        ViewModelProvider(requireActivity()).get(BasketViewModel::class.java)
    }
    val adapter = FoodMenuAdapter {
        basketViewModel.notifyMealAdded(it)
        Toast.makeText(context, "Добавлено", Toast.LENGTH_SHORT).show()
    }


    companion object {
        @JvmStatic
        fun newInstance(category: ApiCategory): FoodFragment {
            return FoodFragment(category)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_food.adapter = adapter

        menuViewModel.getFood(category)
        menuViewModel.currentFood.observe(requireActivity()) {
            adapter.update(it.toMutableList())
        }
    }
}