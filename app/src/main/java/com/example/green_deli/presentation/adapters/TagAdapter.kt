package com.example.green_deli.presentation.adapters

import android.view.View
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.DiffUtil
import com.example.green_deli.Common
import com.example.green_deli.Common.view
import com.example.green_deli.R
import com.example.green_deli.common.ApiTag
import com.example.green_deli.domain.pojo.response.ApiCategory
import com.example.green_deli.domain.pojo.response.ApiFood
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_food_menu.view.*
import kotlinx.android.synthetic.main.view_category.view.*
import java.lang.IllegalStateException

class TagAdapter(
    private val onClickToFood: (ApiTag, View, Int) -> Unit
) : AbstractListAdapter<ApiTag>(
    object: DiffUtil.ItemCallback<ApiTag>() {
        override fun areContentsTheSame(oldItem: ApiTag, newItem: ApiTag) = false
        override fun areItemsTheSame(oldItem: ApiTag, newItem: ApiTag) = false
    }, R.layout.view_category
) {
    override fun View.onBind(item: ApiTag, bindingPosition: Int, itemCount: Int) {
        tv_category.text = item.name
        setOnClickListener {
            onClickToFood(item, this, bindingPosition)
        }
    }
}