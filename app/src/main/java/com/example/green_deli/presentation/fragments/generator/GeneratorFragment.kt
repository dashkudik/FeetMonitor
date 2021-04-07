package com.example.green_deli.presentation.fragments.generator

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.green_deli.R
import com.example.green_deli.domain.pojo.response.ApiCategory
import com.example.green_deli.presentation.adapters.CategoryAdapter
import com.example.green_deli.presentation.adapters.FoodMenuAdapter
import com.example.green_deli.presentation.adapters.TagAdapter
import com.example.green_deli.presentation.fragments.AbstractFragment
import com.example.green_deli.presentation.fragments.basket.BasketViewModel
import kotlinx.android.synthetic.main.fragment_food.*
import kotlinx.android.synthetic.main.fragment_generator.*


class GeneratorFragment : AbstractFragment<GeneratorViewModel>(R.layout.fragment_generator) {
    override val viewModel by lazy {
        createViewModel<GeneratorViewModel>()
    }

    val basketViewModel by lazy {
        ViewModelProvider(requireActivity()).get(BasketViewModel::class.java)
    }
    val adapter = FoodMenuAdapter {
        basketViewModel.notifyMealAdded(it)
        Toast.makeText(context, "Добавлено", Toast.LENGTH_SHORT).show()
    }

    val adapterCategory = CategoryAdapter { it, view, pos ->
        view.background = requireContext().getDrawable(R.drawable.bg_gray)
        viewModel.notifyCategoryAdded(it)
        view.isClickable = false
    }
    val adapterTags = TagAdapter { it, view, pos ->
        view.background = requireContext().getDrawable(R.drawable.bg_gray)
        viewModel.notifyTagAdded(it)
        view.isClickable = false
    }

    override fun fragmentBlock() {
        recycler_generator.adapter = adapter
        recycler_tags.adapter = adapterTags
        recycler_categories.adapter = adapterCategory

        (recycler_tags.layoutManager as GridLayoutManager).spanCount = 2
        (recycler_categories.layoutManager as GridLayoutManager).spanCount = 2

        btn_generate.setOnClickListener {
            viewModel.generateFood()
        }
        btn_reset.setOnClickListener {
            repeat(adapterCategory.itemCount) {
                (recycler_categories.layoutManager as LinearLayoutManager).getChildAt(it)?.background =
                    requireContext().getDrawable(R.drawable.bg_default)
            }
            repeat(adapterTags.itemCount) {
                (recycler_tags.layoutManager as LinearLayoutManager).getChildAt(it)?.background =
                    requireContext().getDrawable(R.drawable.bg_default)
            }
            adapter.clear()
            viewModel.notifyDataReset()
            viewModel.fetchCategories()
            viewModel.fetchTags()
        }
    }

    override fun GeneratorViewModel.observeBlock() {
        categories.observe(viewLifecycleOwner) {
            adapterCategory.update(it.toMutableList())
        }
        tags.observe(viewLifecycleOwner) {
            Log.i("TEST_G", it.size.toString())
            adapterTags.update(it.toMutableList())
        }
        error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        generated.observe(viewLifecycleOwner) {
            Log.i("TEST_G1", it.size.toString())
            if (it.isEmpty()) {
                Toast.makeText(context, "Нет результатов :(", Toast.LENGTH_SHORT).show()
            }
            adapter.update(it.toMutableList())
        }
    }

    override fun GeneratorViewModel.processBlock() {
        fetchCategories()
        fetchTags()
    }
}
