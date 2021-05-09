package dashkudov.feetmonitor.presentation.fragments.stats

import android.content.Intent
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feetmonitor.R
import dashkudov.feetmonitor.Constants.create
import dashkudov.feetmonitor.presentation.FullChartActivity
import dashkudov.feetmonitor.presentation.MainViewModel
import dashkudov.feetmonitor.presentation.custom.ChartDrawer
import dashkudov.feetmonitor.presentation.fragments.AbstractFragment
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class StatisticsFragment : AbstractFragment<StatisticsViewModel>(R.layout.fragment_statistics) {
    override val viewModel by lazy {
        createViewModel<StatisticsViewModel>()
    }

    private val mainViewModel by lazy {
        viewModelFactory.create(requireActivity(), MainViewModel::class.java)
    }

    override fun fragmentBlock() {
        view?.findViewById<LinearLayoutCompat>(R.id.container_criteria_list)?.apply {
            (getChildAt(1) as AppCompatTextView).text = "Шаги"
            (getChildAt(2) as AppCompatTextView).text = "Предупреждения заболеваний"
            (getChildAt(3) as AppCompatTextView).text = "Время в состоянии подключения"
            (getChildAt(4) as AppCompatTextView).text = "Давление пятки"
            (getChildAt(5) as AppCompatTextView).text = "Давление внутренней части стопы"
            (getChildAt(6) as AppCompatTextView).text = "Давление внешней части стопы"
        }

        view?.findViewById<LinearLayoutCompat>(R.id.container_statistics_today)?.apply {
            (getChildAt(1) as AppCompatTextView).text = "7836"
            (getChildAt(2) as AppCompatTextView).text = "3"
            (getChildAt(3) as AppCompatTextView).text = "8:24"
            (getChildAt(4) as AppCompatTextView).text = "34"
            (getChildAt(5) as AppCompatTextView).text = "45"
            (getChildAt(6) as AppCompatTextView).text = "23"
        }

        view?.findViewById<LinearLayoutCompat>(R.id.container_statistics_all_time)?.apply {
            (getChildAt(1) as AppCompatTextView).text = "7100"
            (getChildAt(2) as AppCompatTextView).text = "3.2"
            (getChildAt(3) as AppCompatTextView).text = "7:56"
            (getChildAt(4) as AppCompatTextView).text = "38.2"
            (getChildAt(5) as AppCompatTextView).text = "43.2"
            (getChildAt(6) as AppCompatTextView).text = "23.2"
        }

        chart_container.setOnClickListener {
            startActivity(Intent(requireActivity(), FullChartActivity::class.java))
        }
    }

    override fun StatisticsViewModel.observeBlock() {
        mActualChartData.observe(viewLifecycleOwner) {
            ChartDrawer(chart, false).show(it)
        }
        mainViewModel.chartDataFlow.observe(viewLifecycleOwner) {
            MainScope().launch {
                it?.collect {
                    chart.invalidate()
                    ChartDrawer(chart, false).show(it)
                }
            }
        }
    }

    override fun StatisticsViewModel.processBlock() {
        getActualChartData()
    }
}
