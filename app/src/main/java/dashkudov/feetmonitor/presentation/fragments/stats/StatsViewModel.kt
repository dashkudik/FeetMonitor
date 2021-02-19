package dashkudov.feetmonitor.presentation.fragments.stats

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dashkudov.feetmonitor.data.entities.chart.ChartData
import dashkudov.feetmonitor.domain.usecase.GetActualChartDataUseCase
import javax.inject.Inject

class StatsViewModel @Inject constructor(
    private val getActualChartDataUseCase: GetActualChartDataUseCase
) : ViewModel() {
    val mActualChartData = MutableLiveData<ChartData>()

    fun getActualChartData() {
        getActualChartDataUseCase.execute {
            onComplete {
                mActualChartData.postValue(it)
            }
        }
    }
}