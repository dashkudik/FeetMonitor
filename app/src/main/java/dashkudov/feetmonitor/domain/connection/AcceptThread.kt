package dashkudov.feetmonitor.domain.connection

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.util.Log
import dashkudov.feetmonitor.FeetMonitorApp.Companion.NAME
import dashkudov.feetmonitor.FeetMonitorApp.Companion.UUID_STRING
import java.io.IOException
import java.util.*

class AcceptThread : Thread() {

    companion object {
        const val TAG = "Accept thread -->"
    }

    private val mmServerSocket: BluetoothServerSocket =
        BluetoothAdapter
            .getDefaultAdapter()
            .listenUsingRfcommWithServiceRecord(NAME, UUID.fromString(UUID_STRING))


    override fun run() {
        // Keep listening until exception occurs or a socket is returned.
        var shouldLoop = true
        while (shouldLoop) {
            val socket: BluetoothSocket? = try {
                mmServerSocket.accept()
            } catch (e: Throwable) {
                e.printStackTrace()
                shouldLoop = false
                null
            }
            socket?.also {
                mmServerSocket.close()
                shouldLoop = false
            }
        }
    }

    // Closes the connect socket and causes the thread to finish.
    fun cancel() {
        try {
            mmServerSocket.close()
        } catch (e: IOException) {
            Log.e("TEST", "Could not close the connect socket", e)
        }
    }
}