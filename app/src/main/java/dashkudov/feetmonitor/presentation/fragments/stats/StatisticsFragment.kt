package dashkudov.feetmonitor.presentation.fragments.stats

import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.feetmonitor.R
import dashkudov.feetmonitor.presentation.fragments.AbstractFragment
import dashkudov.feetmonitor.presentation.util.LineChartDrawer


class StatisticsFragment : AbstractFragment<StatisticsViewModel>(R.layout.fragment_statistics) {
    override val viewModel by lazy {
        createViewModel<StatisticsViewModel>()
    }

    override fun fragmentBlock() {
        /* view?.findViewById<LinearLayoutCompat>(R.id.stats_container)?.apply {
            (getChildAt(1).findViewById<AppCompatTextView>(R.id.stats_label)).text = "Шаги: "
            (getChildAt(2).findViewById<AppCompatTextView>(R.id.stats_label)).text = "Предупреждения: "
            (getChildAt(3).findViewById<AppCompatTextView>(R.id.stats_label)).text = "Среднее давление пятки: "
            (getChildAt(4).findViewById<AppCompatTextView>(R.id.stats_label)).text = "Среднее давление внутренней части стопы: "
            (getChildAt(5).findViewById<AppCompatTextView>(R.id.stats_label)).text = "Среднее давление внешней части стопы: "
            (getChildAt(6).findViewById<AppCompatTextView>(R.id.stats_label)).text = "Время в состоянии соединения: "

            (getChildAt(1).findViewById<AppCompatTextView>(R.id.stats_value)).text = "8346"
            (getChildAt(2).findViewById<AppCompatTextView>(R.id.stats_value)).text = "2"
            (getChildAt(3).findViewById<AppCompatTextView>(R.id.stats_value)).text = "36"
            (getChildAt(4).findViewById<AppCompatTextView>(R.id.stats_value)).text = "25"
            (getChildAt(5).findViewById<AppCompatTextView>(R.id.stats_value)).text = "44"
            (getChildAt(6).findViewById<AppCompatTextView>(R.id.stats_value)).text = "15 часов 46 минут"
        } */

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

    }

    override fun StatisticsViewModel.observeBlock() {
        mActualChartData.observe(viewLifecycleOwner) {
            LineChartDrawer(this@StatisticsFragment).show(it)
        }
    }

    override fun StatisticsViewModel.processBlock() {
        getActualChartData()
    }
}
