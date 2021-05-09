package dashkudov.feetmonitor.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dashkudov.feetmonitor.data.entities.chart.ChartData
import dashkudov.feetmonitor.domain.usecase.GetLastChartDataUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getLastChartDataUseCase: GetLastChartDataUseCase
) : ViewModel() {

    val chartDataFlow: MutableLiveData<Flow<ChartData>?> by lazy {
        MutableLiveData<Flow<ChartData>?>()
    }

    val connectionSuccessful by lazy {
        MutableLiveData<Boolean>()
    }

    val bluetoothIsEnabled by lazy {
        MutableLiveData<Boolean>()
    }

    val isLoading by lazy {
        MutableLiveData<Boolean>()
    }

    fun notifyConnectionWasSuccessful(value: Boolean) {
        connectionSuccessful.postValue(value)
    }

    fun notifyBluetoothIsEnabled(value: Boolean) {
        bluetoothIsEnabled.postValue(value)
    }

    fun notifyItIsLoading(value: Boolean) {
        isLoading.postValue(value)
    }
}