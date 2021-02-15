package dashkudov.feetmonitor.data.objects.foot

sealed class FootPart: DataSet

interface DataSet {
    var dataSet: List<Float>
}

class BottomFootPart: FootPart() {
    companion object {
        const val NAME = "Пятка"
        const val CHART_DESCRIPTION = "Давление пятки"
    }
    override var dataSet = listOf<Float>()
}

class InternalFootPart: FootPart() {
    companion object {
        const val NAME = "Внутренняя часть стопы"
        const val CHART_DESCRIPTION = "Давление внутренней части стопы"
    }
    override var dataSet = listOf<Float>()
}