package com.example.green_deli.presentation.adapters

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.DiffUtil
import com.example.green_deli.Common.BASE_URL
import com.example.green_deli.Common.INFO_1
import com.example.green_deli.Common.INFO_2
import com.example.green_deli.Common.INFO_3
import com.example.green_deli.Common.INFO_4
import com.example.green_deli.Common.INFO_5
import com.example.green_deli.Common.view
import com.example.green_deli.R
import com.example.green_deli.domain.pojo.response.ApiFood
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_food_menu.view.*
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class FoodMenuAdapter(
    private val longClickToAdd: (ApiFood) -> Unit
): AbstractListAdapter<ApiFood>(
    object: DiffUtil.ItemCallback<ApiFood>() {
        override fun areContentsTheSame(oldItem: ApiFood, newItem: ApiFood) = oldItem.name == newItem.name
        override fun areItemsTheSame(oldItem: ApiFood, newItem: ApiFood) = oldItem.name == newItem.name
    }, R.layout.card_food_menu
) {

    override fun View.onBind(item: ApiFood, bindingPosition: Int, itemCount: Int) {
        tv_name.text = item.name
        tv_about.text = item.foodEV.about
        Picasso.get().load("$BASE_URL/image/${item.image}").into(img_food)

        container_info.children.filterIndexed { i, it -> i != 0 }.forEachIndexed { i, it ->
            it.view<TextView>(R.id.tv_info_name).text = when (i) {
                0 -> {
                    var string = String()
                    item.tags.forEach {
                        string = string + it.name + ", "
                    }
                    if (string.length > 2) {
                        string.substring(0..string.length - 3)
                    } else {
                        String()
                    }
                }
                1 -> INFO_1
                2 -> INFO_2
                3 -> INFO_3
                4 -> INFO_4
                5 -> INFO_5
                else -> throw IllegalStateException()
            }
            it.view<TextView>(R.id.tv_info_value).text = when (i) {
                0 -> { "" }
                1 -> item.foodEV.proteins.toString() + " г"
                2 -> item.foodEV.lipids.toString() + " г"
                3 -> item.foodEV.hydr.toString() + " г"
                4 -> item.foodEV.energy.toString() + " ккал"
                5 -> item.price.toString() + " руб"
                else -> throw IllegalStateException()
            }
        }

        setOnLongClickListener {
            longClickToAdd(item)
            true
        }

    }
}