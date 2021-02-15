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

            /*val values = ArrayList<Entry>().apply {

            }
            (0..15).forEach {
                val randomValue = (Math.random() * 20).toFloat() + 50
                values.add(Entry(it.toFloat(), randomValue, null))
            }
            val set1: LineDataSet
            if (chart.data != null &&
                chart.data.dataSetCount > 0
            ) {
                set1 = chart.data.getDataSetByIndex(0) as LineDataSet
                set1.values = values
                set1.notifyDataSetChanged()
                chart.data.notifyDataChanged()
                chart.notifyDataSetChanged()
            } else {
                // create a dataset and give it a type
                set1 = LineDataSet(values, "Давление пятки")

                val dataSets = ArrayList<ILineDataSet>()
                dataSets.add(set1) // add the data sets

                // create a data object with the data sets
                val data = LineData(dataSets)

                // set data
                chart.data = data

            } */
        }
    }

    fun createLineDataFromChartData(data: ChartData): LineData {
        var i = -1
        return LineData(
            LineDataSet(data.bottomFootPartDataSet.dataSet.map { Entry().apply { y = it; x = (++i).toFloat() } }, BottomFootPart.CHART_DESCRIPTION).apply {
                LineChartUtils.Styler.styleDataSet(this, BottomFootPart())
            }.also { i = -1 },
            LineDataSet(data.internalFootPartDataSet.dataSet.map { Entry().apply { y = it; x = (++i).toFloat() } }, InternalFootPart.CHART_DESCRIPTION).apply {
                LineChartUtils.Styler.styleDataSet(this, InternalFootPart())
            }
        )
    }
    override fun onValueSelected(e: Entry, h: Highlight?) {}
    override fun onNothingSelected() {}
}

