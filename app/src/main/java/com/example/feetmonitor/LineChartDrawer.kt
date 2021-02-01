package com.example.feetmonitor

import android.graphics.Color
import android.graphics.DashPathEffect
import android.widget.SeekBar
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.fragment_stats.*
import java.util.ArrayList

class LineChartDrawer(private val fragment: StatsFragment): SeekBar.OnSeekBarChangeListener,
    OnChartValueSelectedListener {

    fun drawLineChart(data: Set<Pair<Float, Float>>) {
        with(fragment) {
            seekBarX.setOnSeekBarChangeListener(this@LineChartDrawer)
            seekBarY.max = 180
            seekBarY.setOnSeekBarChangeListener(this@LineChartDrawer)

            // background color
            chart.setBackgroundColor(Color.WHITE)

            // disable description text
            chart.description.isEnabled = false

            // enable touch gestures
            chart.setTouchEnabled(true)

            // set listeners
            chart.setOnChartValueSelectedListener(this@LineChartDrawer)
            chart.setDrawGridBackground(false)

            // enable scaling and dragging
            chart.isDragEnabled = true
            chart.setScaleEnabled(true)
            chart.setPinchZoom(true)

            // // X-Axis Style // //
            val xAxis = chart.xAxis

            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f)

            // // Y-Axis Style // //
            val yAxis: YAxis = chart.axisLeft

            // disable dual axis (only use LEFT axis)
            chart.axisRight.isEnabled = false

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f)

            // axis range
            yAxis.axisMaximum = 200f
            yAxis.axisMinimum = -50f

            // add data
            seekBarX.progress = 45
            seekBarY.progress = 180
            setData(data)

            // draw points over time

            // draw points over time
            chart.animateX(1500)


            // get the legend (only possible after setting data)
            val l: Legend = chart.legend

            // draw legend entries as lines
            l.form = Legend.LegendForm.LINE
        }
    }

    private fun setData(data: Set<Pair<Float, Float>>) {
        with(fragment) {
            val values = ArrayList<Entry>()
            var i = 0
            data.forEach { value ->
                val entry = value.first
                values.add(Entry(i.toFloat(), value.first, null))
            }
            for (i in data.indices) {
                val `val` = (Math.random() * 20).toFloat() - 30
                values.add(Entry(i.toFloat(), `val`, null))
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
                set1 = LineDataSet(values, "DataSet 1")
                set1.setDrawIcons(false)

                // draw dashed line
                set1.enableDashedLine(10f, 5f, 0f)

                // black lines and points
                set1.color = Color.BLACK
                set1.setCircleColor(Color.BLACK)

                // line thickness and point size
                set1.lineWidth = 1f
                set1.circleRadius = 3f

                // draw points as solid circles
                set1.setDrawCircleHole(false)

                // customize legend entry
                set1.formLineWidth = 1f
                set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
                set1.formSize = 15f

                // text size of values
                set1.valueTextSize = 9f

                // draw selection line as dashed
                set1.enableDashedHighlightLine(10f, 5f, 0f)

                // set the filled area
                set1.setDrawFilled(true)
                set1.fillFormatter =
                    IFillFormatter { dataSet, dataProvider -> chart.axisLeft.axisMinimum }

                val dataSets = ArrayList<ILineDataSet>()
                dataSets.add(set1) // add the data sets

                // create a data object with the data sets
                val data = LineData(dataSets)

                // set data
                chart.data = data
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {}
    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    override fun onValueSelected(e: Entry, h: Highlight?) {}
    override fun onNothingSelected() {}
}