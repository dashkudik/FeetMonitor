package dashkudov.feetmonitor.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dashkudov.feetmonitor.data.entities.chart.ChartData
import dashkudov.feetmonitor.domain.usecase.GetLastChartDataUseCase
import javax.inject.Inject

class FullChartViewModel @Inject constructor(
    private val getLastChartDataUseCase: GetLastChartDataUseCase
) : ViewModel() {
    val mLastChartData by lazy {
        MutableLiveData<ChartData>()
    }

    fun getLastChartData(minutes: Int) {
        getLastChartDataUseCase.apply {
            this.minutes = minutes
        }.execute {
            onComplete {
                mLastChartData.postValue(it)
            }
        }
    }
}