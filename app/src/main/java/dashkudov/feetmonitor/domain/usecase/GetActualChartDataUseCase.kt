package dashkudov.feetmonitor.domain.usecase

import dashkudov.feetmonitor.data.entities.chart.ChartData
import dashkudov.feetmonitor.data.objects.foot.BottomFootPart
import dashkudov.feetmonitor.data.objects.foot.InternalFootPart

class GetActualChartDataUseCase: UseCase<ChartData>() {
    fun execute(onComplete: () -> Unit, onError: () -> Unit): ChartData {
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
            (0..12).forEach {
                val randomValue = (Math.random() * 70).toFloat() + 60
                add(randomValue)
            }
        }
    }
}