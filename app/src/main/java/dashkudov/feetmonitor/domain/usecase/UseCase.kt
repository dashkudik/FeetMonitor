package dashkudov.feetmonitor.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.reflect.KProperty

abstract class UseCase<T> {

    abstract fun execute(): T



    protected class ExecutingHandler<T> : State<T> {
        override var onStart = {}
        override var onComplete = { result: T -> }
        override var onStop = {}
        override var onFail = { e: Exception -> }

        fun onStart(block: () -> Unit) {
            onStart = block
        }
        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }
        fun onStop(block: () -> Unit) {
            onStop = block
        }
        fun onFail(block: (e: Exception) -> Unit) {
            onFail = block
        }
    }

    private interface State<T> {
        var onStart: () -> Unit
        var onComplete: (T) -> Unit
        var onStop: () -> Unit
        var onFail: (e: Exception) -> Unit
    }

}
