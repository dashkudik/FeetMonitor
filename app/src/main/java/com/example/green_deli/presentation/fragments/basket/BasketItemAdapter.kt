package com.example.green_deli.presentation.fragments.basket

import android.view.View
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.DiffUtil
import com.example.green_deli.Common
import com.example.green_deli.Common.view
import com.example.green_deli.R
import com.example.green_deli.domain.pojo.response.ApiCategory
import com.example.green_deli.domain.pojo.response.ApiFood
import com.example.green_deli.presentation.adapters.AbstractListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_food_menu.view.*
import kotlinx.android.synthetic.main.view_basket_item.view.*
import kotlinx.android.synthetic.main.view_category.view.*
import kotlinx.android.synthetic.main.view_category.view.tv_category
import java.lang.IllegalStateException

class BasketItemAdapter : AbstractListAdapter<Pair<ApiFood, Int>>(
    object: DiffUtil.ItemCallback<Pair<ApiFood, Int>>() {
        override fun areContentsTheSame(oldItem: Pair<ApiFood, Int>, newItem: Pair<ApiFood, Int>) = false
        override fun areItemsTheSame(oldItem: Pair<ApiFood, Int>, newItem: Pair<ApiFood, Int>) = false
    }, R.layout.view_basket_item
) {
    override fun View.onBind(item: Pair<ApiFood, Int>, bindingPosition: Int, itemCount: Int) {
        tv_basket.text = item.first.name + " - ${item.second}шт"
    }
}