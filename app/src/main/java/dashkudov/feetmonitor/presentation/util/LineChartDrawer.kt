package dashkudov.feetmonitor.presentation.util

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import dashkudov.feetmonitor.Constants.DEFAULT_START_VALUE
import dashkudov.feetmonitor.data.entities.chart.ChartData
import dashkudov.feetmonitor.data.objects.foot.BottomFootPart
import dashkudov.feetmonitor.data.objects.foot.InternalFootPart
import dashkudov.feetmonitor.presentation.fragments.stats.StatsFragment
import kotlinx.android.synthetic.main.fragment_stats.*

class LineChartDrawer(private val fragment: StatsFragment): OnChartValueSelectedListener {

    fun show(data: ChartData) {
        with(fragment) {
            LineChartUtils.Styler.styleChart(chart)
            chart.data = createLineDataFromChartData(data)
        }
    }

    fun createLineDataFromChartData(data: ChartData): LineData {
        var i = DEFAULT_START_VALUE
        return LineData(
            LineDataSet(data.bottomFootPartDataSet.dataSet.map { Entry().apply { y = it; x = (++i).toFloat() } }, BottomFootPart.CHART_DESCRIPTION).apply {
                LineChartUtils.Styler.styleDataSet(this, BottomFootPart())
            }.also { i = DEFAULT_START_VALUE },
            LineDataSet(data.internalFootPartDataSet.dataSet.map { Entry().apply { y = it; x = (++i).toFloat() } }, InternalFootPart.CHART_DESCRIPTION).apply {
                LineChartUtils.Styler.styleDataSet(this, InternalFootPart())
            }
        )
    }

    override fun onValueSelected(e: Entry, h: Highlight?) {}
    override fun onNothingSelected() {}
}

