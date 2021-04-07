package com.example.green_deli.presentation.fragments.menu

import com.example.green_deli.R
import com.example.green_deli.presentation.adapters.CategoryAdapter
import com.example.green_deli.presentation.fragments.AbstractFragment
import kotlinx.android.synthetic.main.fragment_menu.*


class MenuFragment : AbstractFragment<MenuViewModel>(R.layout.fragment_menu) {
    override val viewModel by lazy {
        createViewModel<MenuViewModel>()
    }

    private val adapter = CategoryAdapter { it, _ , _->
        requireActivity().
            supportFragmentManager.
            beginTransaction().
            replace(R.id.root, FoodFragment.newInstance(it)).
            addToBackStack(null).
            commit()
        }

    override fun fragmentBlock() {
        recycler_categories.adapter = adapter
    }

    override fun MenuViewModel.observeBlock() {
        categories.observe(viewLifecycleOwner) {
            adapter.update(it.toMutableList())
        }
    }

    override fun MenuViewModel.processBlock() {
        fetchCategories()
    }
}
