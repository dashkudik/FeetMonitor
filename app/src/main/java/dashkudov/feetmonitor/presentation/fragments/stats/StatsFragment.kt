package dashkudov.feetmonitor.presentation.fragments.stats

import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feetmonitor.R
import dashkudov.feetmonitor.presentation.fragments.AbstractFragment
import dashkudov.feetmonitor.presentation.util.LineChartDrawer


class StatsFragment : AbstractFragment<StatsViewModel>(R.layout.fragment_stats) {
    override val viewModel by lazy {
        createViewModel<StatsViewModel>()
    }

    override fun fragmentBlock() {
        view?.findViewById<LinearLayoutCompat>(R.id.stats_container)?.apply {
            (getChildAt(0).findViewById<AppCompatTextView>(R.id.stats_label)).text = "Шаги: "
            (getChildAt(1).findViewById<AppCompatTextView>(R.id.stats_label)).text = "Опасения: "
        }
    }

    override fun StatsViewModel.observeBlock() {
        mActualChartData.observe(viewLifecycleOwner) {
            LineChartDrawer(this@StatsFragment).show(it)
        }
    }

    override fun StatsViewModel.processBlock() {
        getActualChartData()
    }
}
