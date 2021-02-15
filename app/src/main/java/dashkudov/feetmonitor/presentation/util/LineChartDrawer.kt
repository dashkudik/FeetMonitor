package dashkudov.feetmonitor.presentation.util

import android.graphics.Color
import android.graphics.DashPathEffect
import android.util.Log
import android.widget.SeekBar
import com.example.feetmonitor.R
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import dashkudov.feetmonitor.presentation.fragments.StatsFragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.DataSet
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import dashkudov.feetmonitor.Constants
import dashkudov.feetmonitor.Constants.DEFAULT_START_VALUE
import dashkudov.feetmonitor.data.entities.chart.ChartData
import dashkudov.feetmonitor.data.objects.foot.BottomFootPart
import dashkudov.feetmonitor.data.objects.foot.FootPart
import dashkudov.feetmonitor.data.objects.foot.InternalFootPart
import kotlinx.android.synthetic.main.fragment_stats.*
import java.util.ArrayList

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

