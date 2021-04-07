package com.example.green_deli.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.DiffUtil
import com.example.green_deli.Common
import com.example.green_deli.Common.view
import com.example.green_deli.R
import com.example.green_deli.domain.pojo.OrderStatus
import com.example.green_deli.domain.pojo.response.ApiCategory
import com.example.green_deli.domain.pojo.response.ApiFood
import com.example.green_deli.domain.pojo.response.ApiOrder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_food_menu.view.*
import kotlinx.android.synthetic.main.view_category.view.*
import kotlinx.android.synthetic.main.view_order.view.*
import java.lang.IllegalStateException

class MyOrdersAdapter : AbstractListAdapter<ApiOrder>(
    object: DiffUtil.ItemCallback<ApiOrder>() {
        override fun areContentsTheSame(oldItem: ApiOrder, newItem: ApiOrder) = false
        override fun areItemsTheSame(oldItem: ApiOrder, newItem: ApiOrder) = false
    }, R.layout.view_order
) {
    override fun View.onBind(item: ApiOrder, bindingPosition: Int, itemCount: Int) {
        val status = when (item.status) {
            OrderStatus.CREATED -> "Создан"
            OrderStatus.IN_PROGRESS -> "Готовится"
            OrderStatus.READY -> "Готов"
            OrderStatus.FINISHED -> "Выполнен"
        }
        (container.getChildAt(0) as TextView).let {
            it.text = it.text.toString() + status
        }
        (container.getChildAt(1) as TextView).let {
            it.text = it.text.toString() + item.totalPrice.toString() + " руб"
        }
        item.food.forEachIndexed { i, it ->
            container.addView(
                LayoutInflater.from(context).inflate(R.layout.view_basket_item_no_border, container, false).apply {
                    view<TextView>(R.id.tv_basket).text = it.name + " - " + item.count[i] + "шт"
                }
            )
        }
    }
}