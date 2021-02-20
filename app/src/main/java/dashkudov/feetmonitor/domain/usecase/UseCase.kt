package dashkudov.feetmonitor.domain.usecase

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

abstract class UseCase<T> {
    protected abstract suspend fun executeOnBackground(): T

    fun execute(block: ExecutingHandler<T>.() -> Unit) {
        val handler = ExecutingHandler<T>().apply {
            this.block()
        }
        CoroutineScope(IO).launch {
            Log.i("USE CASE LOGGER::::", "ON START")
            try {
                val result = executeOnBackground()
                handler.onComplete(result)
                Log.i("USE CASE LOGGER::::", "ON COMPLETE")
            } catch (e: Exception) {
                handler.onFail(e)
                Log.i("USE CASE LOGGER::::", "ON FAIL")
            } finally {
                handler.onStop()
                Log.i("USE CASE LOGGER::::", "ON STOP")
            }
        }
    }

    class ExecutingHandler<T> {
        var onStart = {}
        var onComplete = { result: T -> }
        var onStop = {}
        var onFail = { e: Exception -> }

        fun onStart(block: () -> Unit) {
            onStart = block
        }

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        fun onStop(block: () -> Unit) {
            onStop = block
        }

        fun onFail(block: (Exception) -> Unit) {
            onFail = block
        }
    }
}
