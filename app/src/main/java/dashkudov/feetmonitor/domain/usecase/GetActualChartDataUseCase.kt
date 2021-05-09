package dashkudov.feetmonitor.domain.usecase

import dashkudov.feetmonitor.Constants.CHART_ENTRIES_AMOUNT
import dashkudov.feetmonitor.data.entities.chart.ChartData
import dashkudov.feetmonitor.data.objects.foot.BottomFootPart
import dashkudov.feetmonitor.data.objects.foot.ExternalFootPart
import dashkudov.feetmonitor.data.objects.foot.InternalFootPart
import dashkudov.feetmonitor.domain.repository.AppRepository
import javax.inject.Inject

class GetActualChartDataUseCase @Inject constructor(
    private val repository: AppRepository
) : UseCase<ChartData>() {
    override suspend fun executeOnBackground(): ChartData {
        return ChartData(
            BottomFootPart().apply {
                dataSet = generateRandomList()
            },
            InternalFootPart().apply {
                dataSet = generateRandomList()
            },
            ExternalFootPart().apply {
                dataSet = generateRandomList()
            }
        )
    }

    private fun generateRandomList(): MutableList<Float> {
        return mutableListOf<Float>().apply {
            (0 until CHART_ENTRIES_AMOUNT).forEach {
                val randomValue = (Math.random() * 90).toFloat() + 70
                add(randomValue)
            }
        }
    }
}