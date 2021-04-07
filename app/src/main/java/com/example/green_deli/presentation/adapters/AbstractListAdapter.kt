package com.example.green_deli.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class AbstractListAdapter<E>(diffUtil: DiffUtil.ItemCallback<E>, private val resId: Int) :
    ListAdapter<E, RecyclerView.ViewHolder>(diffUtil) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder.itemView) {
            onBind(getItem(position), holder.adapterPosition, itemCount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(resId, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    /*
    * @param bindingPosition
    * @param itemCount
    * let handle scroll etc.
    */
    abstract fun View.onBind(item: E, bindingPosition: Int, itemCount: Int)

    fun addToStart(items: MutableList<E>) {
        submitList(currentList.toMutableList().apply { addAll(0, items) })
    }

    fun append(items: MutableList<E>) {
        submitList(currentList.toMutableList().apply { addAll(items) })
    }

    fun append(item: E) {
        submitList(currentList.toMutableList().apply { add(item) })
    }

    fun update(items: MutableList<E>) {
        submitList(items)
    }

    fun clear() {
        submitList(mutableListOf())
    }
}