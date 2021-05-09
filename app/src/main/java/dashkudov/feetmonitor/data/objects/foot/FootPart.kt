package dashkudov.feetmonitor.data.objects.foot

sealed class FootPart: DataSet

interface DataSet {
    var dataSet: MutableList<Float>
}

class BottomFootPart: FootPart() {
    companion object {
        const val CHART_DESCRIPTION = "Пятка"
    }

    override var dataSet = mutableListOf<Float>()
}

class InternalFootPart: FootPart() {
    companion object {
        const val CHART_DESCRIPTION = "Внутренняя"
    }

    override var dataSet = mutableListOf<Float>()
}

class ExternalFootPart: FootPart() {
    companion object {
        const val CHART_DESCRIPTION = "Внешняя"
    }

    override var dataSet = mutableListOf<Float>()
}