package dashkudov.feetmonitor.presentation.util

import android.graphics.Color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineDataSet
import dashkudov.feetmonitor.data.objects.foot.BottomFootPart
import dashkudov.feetmonitor.data.objects.foot.FootPart
import dashkudov.feetmonitor.data.objects.foot.InternalFootPart

class LineChartUtils {
    object Styler {
        fun styleChart(chart: LineChart) {
            with (chart) {
                setBackgroundColor(Color.WHITE)
                setTouchEnabled(true)
                setDrawGridBackground(false)
                isDragEnabled = true
                setScaleEnabled(true)
                setPinchZoom(true)

                axisLeft.axisMaximum = 200f
                axisLeft.axisMinimum = 0f
                axisRight.isEnabled = false
                xAxis.isEnabled = false

                description.isEnabled = false
                legend.textSize = 11f
                axisLeft.setDrawGridLines(false)
            }
        }

        fun styleDataSet(dataSet: LineDataSet, footPart: FootPart) {
            with(dataSet) {
                lineWidth = 1f
                circleRadius = 3f
                setDrawCircleHole(false)
                valueTextSize = 8f

                when (footPart) {
                    is BottomFootPart -> {
                        color = Color.RED
                        setCircleColor(Color.RED)
                    }
                    is InternalFootPart -> {
                        color = Color.BLUE
                        setCircleColor(Color.BLUE)
                    }
                }
            }
        }
    }
}