package com.example.green_deli.domain.usecase

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<T> {
    abstract suspend fun executeOnBackground(): T

    fun execute(block: ExecutingHandler<T>.() -> Unit) {
        val handler = ExecutingHandler<T>().apply {
            this.block()
        }
        CoroutineScope(Default).launch {
            Log.i("USE CASE LOGGER::::", "ON START")
            try {
                val result = executeOnBackground()
                withContext(Main) {
                    handler.onComplete(result)
                }
                Log.i("USE CASE LOGGER::::", "ON COMPLETE")
            } catch (e: Exception) {
                withContext(Main) {
                    handler.onFail(e)
                }
                Log.i("USE CASE LOGGER::::", "ON FAIL")
                Log.i("ERRR", e.message ?: "")
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
