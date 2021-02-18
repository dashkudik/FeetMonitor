package dashkudov.feetmonitor.presentation.fragments.stats

import com.example.feetmonitor.R
import dashkudov.feetmonitor.presentation.fragments.AbstractFragment
import dashkudov.feetmonitor.presentation.util.LineChartDrawer


class StatsFragment : AbstractFragment<StatsViewModel>(R.layout.fragment_stats) {
    override val viewModel by lazy {
        createViewModel<StatsViewModel>()
    }

    override fun prepareBlock() {

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
