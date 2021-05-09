package dashkudov.feetmonitor.domain.usecase

import dashkudov.feetmonitor.data.entities.chart.ChartData
import dashkudov.feetmonitor.data.objects.foot.BottomFootPart
import dashkudov.feetmonitor.data.objects.foot.ExternalFootPart
import dashkudov.feetmonitor.data.objects.foot.InternalFootPart
import dashkudov.feetmonitor.domain.repository.AppRepository
import javax.inject.Inject

class GetLastChartDataUseCase @Inject constructor(
    private val repository: AppRepository
) : UseCase<ChartData>() {
    var minutes: Int? = null

    override suspend fun executeOnBackground(): ChartData {
        return ChartData(
            BottomFootPart().apply {
                dataSet = generateRandomList(minutes!!)
            },
            InternalFootPart().apply {
                dataSet = generateRandomList(minutes!!)
            },
            ExternalFootPart().apply {
                dataSet = generateRandomList(minutes!!)
            }
        )
    }

    private fun generateRandomList(minutes: Int): MutableList<Float> {
        return mutableListOf<Float>().apply {
            (0 until minutes * 60).forEach {
                val randomValue = (Math.random() * 90).toFloat() + 70
                add(randomValue)
            }
        }
    }
}