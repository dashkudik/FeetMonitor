package dashkudov.feetmonitor.presentation

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.feetmonitor.R
import dagger.android.support.DaggerAppCompatActivity
import dashkudov.feetmonitor.Constants
import dashkudov.feetmonitor.Constants.MAC
import dashkudov.feetmonitor.Constants.create
import dashkudov.feetmonitor.data.entities.chart.ChartData
import dashkudov.feetmonitor.gateway.AppRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_toolbar.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val bluetooth = BluetoothAdapter.getDefaultAdapter()

    lateinit var flow: Flow<ChartData>

    val mainViewModel by lazy {
        viewModelFactory.create(this, MainViewModel::class.java)
    }

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appRepository.context = applicationContext
        mainViewModel.notifyBluetoothIsEnabled(bluetooth.isEnabled)

        with(mainViewModel) {
            connectionSuccessful.observe(this@MainActivity) {
                notifyItIsLoading(false)
                if (it) {
                    Toast.makeText(applicationContext, "Успешно подключено", Toast.LENGTH_SHORT)
                        .show()
                    btn_connection.imageTintList = ColorStateList.valueOf(Color.GREEN)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Не удалось подключиться",
                        Toast.LENGTH_LONG
                    ).show()
                    btn_connection.imageTintList = ColorStateList.valueOf(Color.WHITE)
                }
            }
            bluetoothIsEnabled.observe(this@MainActivity) {
                if (it) {
                    btn_bluetooth_connection.imageTintList = ColorStateList.valueOf(Color.GREEN)
                } else {
                    btn_bluetooth_connection.imageTintList = ColorStateList.valueOf(Color.WHITE)
                }
            }
            isLoading.observe(this@MainActivity) {
                progressBar.isVisible = it
            }
        }

        nav_view.setupWithNavController((fragment_host as NavHostFragment).navController)

        btn_connection.setOnClickListener {
            if (bluetooth.isEnabled) {
                if (mainViewModel.connectionSuccessful.value != true) {
                    CoroutineScope(Dispatchers.Default).launch {
                        BluetoothAdapter.getDefaultAdapter().bondedDevices.find {
                            it.address == MAC
                        }?.connect() ?: mainViewModel.notifyConnectionWasSuccessful(false)
                    }
                }
            } else {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, Constants.BLUETOOTH_REQUEST_CODE)
            }
        }

        btn_bluetooth_connection.setOnClickListener {
            if (mainViewModel.bluetoothIsEnabled.value != true) {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, Constants.BLUETOOTH_REQUEST_CODE)
            }
        }

        if (appRepository.getAutoconnection()) {
            if (bluetooth.isEnabled) {
                if (mainViewModel.connectionSuccessful.value != true) {
                    CoroutineScope(Dispatchers.Default).launch {
                        BluetoothAdapter.getDefaultAdapter().bondedDevices.find {
                            it.address == MAC
                        }?.connect() ?: mainViewModel.notifyConnectionWasSuccessful(false)
                    }
                }
            } else {
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, Constants.BLUETOOTH_REQUEST_CODE)
            }
        }
    }

    @InternalCoroutinesApi
    private suspend fun BluetoothDevice.connect() {
        val uuid = uuids[0].uuid
        val socket = createRfcommSocketToServiceRecord(uuid)
        withContext(Dispatchers.IO) {
            try {
                mainViewModel.notifyItIsLoading(true)
                socket.connect()
                mainViewModel.notifyItIsLoading(false)
                mainViewModel.notifyConnectionWasSuccessful(true)
                val inStream = socket.inputStream
                val buffer = ByteArray(1024)
                while (true) {
                    try {
                        inStream.read(buffer)
                    } catch (e: IOException) {
                        mainViewModel.notifyConnectionWasSuccessful(false)
                        break
                    }
                }
            } catch (e: Exception) {
                mainViewModel.notifyConnectionWasSuccessful(false)
            }
        }
    }

    private fun ChartData.processData(index: Int, data: Float) {
        when (index) {
            1, 2, 3 -> bottomFootPartDataSet.dataSet.add(data)
            4, 5, 6 -> internalFootPartDataSet.dataSet.add(data)
            7, 8 -> externalFootPartDataSet.dataSet.add(data)
        }
    }

    private fun ChartData.clear() {
        this.bottomFootPartDataSet.dataSet.clear()
        this.internalFootPartDataSet.dataSet.clear()
        this.externalFootPartDataSet.dataSet.clear()
    }


    @InternalCoroutinesApi
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.BLUETOOTH_REQUEST_CODE && resultCode != RESULT_CANCELED) {
            mainViewModel.notifyBluetoothIsEnabled(true)
            if (appRepository.getAutoconnection()) {
                CoroutineScope(Dispatchers.Default).launch {
                    BluetoothAdapter.getDefaultAdapter().bondedDevices.find {
                        it.address == MAC
                    }?.connect() ?: mainViewModel.notifyConnectionWasSuccessful(false)
                }
            }
        }
    }

    companion object {
        val appRepository = AppRepositoryImpl()
    }
}