package dashkudov.feetmonitor.data.entities.chart

import dashkudov.feetmonitor.data.objects.foot.DataSet

data class ChartData(
    val bottomFootPartDataSet: DataSet,
    val internalFootPartDataSet: DataSet
)