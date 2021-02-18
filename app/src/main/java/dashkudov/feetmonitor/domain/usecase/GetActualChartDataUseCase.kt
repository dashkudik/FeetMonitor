package dashkudov.feetmonitor.domain.usecase

import dashkudov.feetmonitor.Constants.CHART_ENTRIES_AMOUNT
import dashkudov.feetmonitor.data.entities.chart.ChartData
import dashkudov.feetmonitor.data.objects.foot.BottomFootPart
import dashkudov.feetmonitor.data.objects.foot.InternalFootPart
import javax.inject.Inject

class GetActualChartDataUseCase @Inject constructor(

) : UseCase<ChartData>() {
    override suspend fun executeOnBackground(): ChartData {
        return ChartData(
            BottomFootPart().apply {
                dataSet = generateRandomList()
            },
            InternalFootPart().apply {
                dataSet = generateRandomList()
            }
        )
    }

    private fun generateRandomList(): List<Float> {
        return ArrayList<Float>().apply {
            (0 until CHART_ENTRIES_AMOUNT).forEach {
                val randomValue = (Math.random() * 70).toFloat() + 60
                add(randomValue)
            }
        }
    }
}