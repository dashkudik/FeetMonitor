package dashkudov.feetmonitor.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.feetmonitor.R
import dashkudov.feetmonitor.domain.usecase.GetActualChartDataUseCase
import dashkudov.feetmonitor.presentation.util.LineChartDrawer


class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_stats, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LineChartDrawer(this).show(
            GetActualChartDataUseCase().execute()
        )

    }


}
