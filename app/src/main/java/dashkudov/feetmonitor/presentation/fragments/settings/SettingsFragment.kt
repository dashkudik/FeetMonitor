package dashkudov.feetmonitor.presentation.fragments.settings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.feetmonitor.R
import com.suke.widget.SwitchButton
import dashkudov.feetmonitor.presentation.MainActivity.Companion.appRepository
import kotlinx.android.synthetic.main.fragment_settings.*
import javax.inject.Inject

class SettingsFragment : Fragment() {
    @Inject
    lateinit var modelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_settings, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_fullscreen.findViewById<SwitchButton>(R.id.switch_button).apply {
            Log.i("TEST1", "${appRepository.getFullscreenMode()}")
            isChecked = appRepository.getFullscreenMode()
            setOnCheckedChangeListener { view, isChecked ->
                Log.i("TEST", isChecked.toString())
                appRepository.setFullscreenMode(isChecked)
            }
        }

        btn_notifications.findViewById<SwitchButton>(R.id.switch_button).apply {
            isChecked = appRepository.getNotifications()
            setOnCheckedChangeListener { view, isChecked ->
                appRepository.setNotifications(isChecked)
            }
        }

        btn_autoconnection.findViewById<SwitchButton>(R.id.switch_button).apply {
            isChecked = appRepository.getAutoconnection()
            setOnCheckedChangeListener { view, isChecked ->
                appRepository.setAutoconnection(isChecked)
            }
        }

    }

}