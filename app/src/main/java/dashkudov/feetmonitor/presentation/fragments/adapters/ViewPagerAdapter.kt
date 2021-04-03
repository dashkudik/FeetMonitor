package dashkudov.feetmonitor.presentation.fragments.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

open class ViewPagerAdapter<T>(
    fragment: Fragment,
    private var items: MutableList<T>,
    private val fragmentBuilder: (T, Int) -> Fragment,
) : FragmentStateAdapter(fragment) {

    private val pageIds
        get() = items.map { it.hashCode().toLong() }

    override fun getItemId(position: Int): Long = items[position].hashCode().toLong()

    override fun containsItem(itemId: Long): Boolean = pageIds.any { it == itemId }

    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment {
        if (position >= items.size) {
            throw IllegalArgumentException("Invalid position")
        }
        return fragmentBuilder(items[position], position)
    }
}