package dashkudov.feetmonitor.presentation

import android.os.Bundle
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import com.example.feetmonitor.R
import dagger.android.support.DaggerAppCompatActivity
import dashkudov.feetmonitor.presentation.custom.ChartDrawer
import kotlinx.android.synthetic.main.activity_full_chart.*
import javax.inject.Inject


class FullChartActivity : DaggerAppCompatActivity() {
    private val viewModel by lazy {
        viewModelFactory.create(FullChartViewModel::class.java)
    }

    private val chartDrawer by lazy {
        ChartDrawer(chart, true)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_chart)

        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, minutes: Int, fromUser: Boolean) {
                if (minutes != 0) {
                    viewModel.getLastChartData(minutes)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        viewModel.mLastChartData.observe(this) {
            chart.notifyDataSetChanged()
            chart.invalidate()
            chartDrawer.show(it)
        }

        viewModel.getLastChartData(10)
    }
}