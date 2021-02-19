package dashkudov.feetmonitor.presentation.fragments.connection

import com.example.feetmonitor.R
import dashkudov.feetmonitor.presentation.fragments.AbstractFragment


class ConnectionFragment : AbstractFragment<ConnectionViewModel>(R.layout.fragment_connection) {

    override val viewModel by lazy {
        createViewModel<ConnectionViewModel>()
    }

    override fun fragmentBlock() {

    }

    override fun ConnectionViewModel.observeBlock() {

    }

    override fun ConnectionViewModel.processBlock() {

    }

}