package dashkudov.feetmonitor.presentation.custom

import android.graphics.Color
import androidx.core.content.ContextCompat
import com.example.feetmonitor.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dashkudov.feetmonitor.Constants.DEFAULT_START_VALUE
import dashkudov.feetmonitor.data.entities.chart.ChartData
import dashkudov.feetmonitor.data.objects.foot.BottomFootPart
import dashkudov.feetmonitor.data.objects.foot.ExternalFootPart
import dashkudov.feetmonitor.data.objects.foot.FootPart
import dashkudov.feetmonitor.data.objects.foot.InternalFootPart
import kotlin.math.roundToInt

class ChartDrawer(private val chart: LineChart, private val isFull: Boolean) {

    fun show(data: ChartData) {
        styleChart(data)
        chart.data = create(data)
    }

    private fun create(data: ChartData): LineData {
        var i = DEFAULT_START_VALUE
        return LineData(
            LineDataSet(data.bottomFootPartDataSet.dataSet.map {
                Entry().apply {
                    y = it; x = (++i).toFloat()
                }
            }, BottomFootPart.CHART_DESCRIPTION).apply {
                styleDataSet(this, BottomFootPart())
            }.also { i = DEFAULT_START_VALUE },
            LineDataSet(data.internalFootPartDataSet.dataSet.map {
                Entry().apply {
                    y = it; x = (++i).toFloat()
                }
            }, InternalFootPart.CHART_DESCRIPTION).apply {
                styleDataSet(this, InternalFootPart())
            }.also { i = DEFAULT_START_VALUE },
            LineDataSet(data.externalFootPartDataSet.dataSet.map {
                Entry().apply {
                    y = it; x = (++i).toFloat()
                }
            }, ExternalFootPart.CHART_DESCRIPTION).apply {
                styleDataSet(this, ExternalFootPart())
            })
    }

    private fun styleChart(data: ChartData) {
        with(chart) {
            setTouchEnabled(isFull)
            isDragEnabled = isFull
            setScaleEnabled(isFull)
            setPinchZoom(isFull)
            animateX(if (isFull) 1500 else 1000)
            setBackgroundColor(ContextCompat.getColor(context, R.color.bright_white))
            axisLeft.axisMaximum = 200f
            axisLeft.axisMinimum = 0f
            axisRight.isEnabled = false
            xAxis.isEnabled = false
            legend.form = Legend.LegendForm.CIRCLE
            legend.textSize = 13f
            legend.setDrawInside(false)
            setDrawGridBackground(false)
            description.isEnabled = false
            legend.textSize = 12F
            axisLeft.setDrawGridLines(false)

            val visibleRange = (data.bottomFootPartDataSet.dataSet.size.toFloat() / 5).roundToInt()

            chart.setVisibleXRangeMaximum(visibleRange.toFloat())
        }
    }

    private fun styleDataSet(dataSet: LineDataSet, footPart: FootPart) {
        with(dataSet) {
            lineWidth = 1f
            circleRadius = 3f
            setDrawCircleHole(false)
            valueTextSize = 8f
            setDrawValues(false)

            this.color = when (footPart) {
                is BottomFootPart -> Color.RED
                is InternalFootPart -> Color.BLUE
                is ExternalFootPart -> Color.GREEN
            }.also {
                setCircleColor(it)
            }
        }
    }
}

