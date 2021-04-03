package com.example.bluetoothcontroller

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import com.example.bluetoothcontroller.MainActivity.Companion.UUID_STRING
import dashkudov.feetmonitor.domain.connection.ConnectedThread
import java.io.IOException
import java.util.*

class ConnectThread(device: BluetoothDevice) : Thread() {
    companion object {
        const val TAG = "Connect Thread -->"
    }

    private val mmSocket: BluetoothSocket? by lazy(LazyThreadSafetyMode.NONE) {
        device.createRfcommSocketToServiceRecord(UUID.fromString(UUID_STRING))
    }

    public override fun run() {
        mmSocket?.use { socket ->
            Log.i(TAG, "TRYING (Connection state)")
            try {
                socket.connect()
                Log.i("TEST", "Connected")
                ConnectedThread(mmSocket!!)
            } catch (e: Throwable) {
                Log.i("ERROR_MES", e.message ?: "no message")
            }

        }
    }

    // Closes the client socket and causes the thread to finish.
    fun cancel() {
        try {
            mmSocket?.close()
        } catch (e: IOException) {
            Log.e("TEST", "Could not close the client socket", e)
        }
    }
}