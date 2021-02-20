package dashkudov.feetmonitor.data.objects.foot

sealed class FootPart: DataSet

interface DataSet {
    var dataSet: List<Float>
}

class BottomFootPart: FootPart() {
    companion object {
        const val NAME = "Пятка"
        const val CHART_DESCRIPTION = "Пятка"
    }
    override var dataSet = listOf<Float>()
}

class InternalFootPart: FootPart() {
    companion object {
        const val NAME = "Внутренняя часть стопы"
        const val CHART_DESCRIPTION = "Внутренняя"
    }
    override var dataSet = listOf<Float>()
}

class ExternalFootPart: FootPart() {
    companion object {
        const val NAME = "Внешняя часть стопы"
        const val CHART_DESCRIPTION = "Внешняя"
    }
    override var dataSet = listOf<Float>()
}