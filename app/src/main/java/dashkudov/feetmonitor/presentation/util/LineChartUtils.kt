package dashkudov.feetmonitor.presentation.util

import android.graphics.Canvas
import android.graphics.Color
import android.icu.lang.UCharacter.IndicPositionalCategory.BOTTOM
import androidx.core.content.res.ResourcesCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.IMarker
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.LineDataSet
import dashkudov.feetmonitor.data.objects.foot.BottomFootPart
import dashkudov.feetmonitor.data.objects.foot.ExternalFootPart
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
                chart.setVisibleXRangeMaximum(10F)

                axisLeft.axisMaximum = 200f
                axisLeft.axisMinimum = 0f
                axisRight.isEnabled = false
                xAxis.isEnabled = false

                legend.form = Legend.LegendForm.CIRCLE
                legend.setDrawInside(false)

                description.isEnabled = false
                legend.textSize = 12F
                axisLeft.setDrawGridLines(false)
            }
        }

        fun styleDataSet(dataSet: LineDataSet, footPart: FootPart) {
            with(dataSet) {
                lineWidth = 1f
                circleRadius = 3f
                setDrawCircleHole(false)
                valueTextSize = 8f
                setDrawValues(false)

                val color = when (footPart) {
                    is BottomFootPart -> Color.RED
                    is InternalFootPart -> Color.BLUE
                    is ExternalFootPart -> Color.GREEN
                }

                this.color = color
                setCircleColor(color)
            }
        }
    }
}