package cdu145.tickets.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cdu145.tickets.data.RestoringHintsDao
import cdu145.tickets.di.ApplicationCoroutineScope
import cdu145.tickets.model.hint.RestoringHint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class DeleteRestoringHintBroadcastReceiver : BroadcastReceiver(), KoinComponent {

    companion object {

        const val USED_AT_KEY = "tickets.hint.restoring.USED_AT"
    }

    private val scope: CoroutineScope
        get() = get(ApplicationCoroutineScope)

    private val dao: RestoringHintsDao
        get() = get()

    override fun onReceive(context: Context, intent: Intent) {
        intent
            .getLongExtra(USED_AT_KEY, -1)
            .takeIf { it != -1L }
            ?.let { usedAt ->
                scope.launch {
                    dao.delete(RestoringHint(usedAt))
                }
            }
            ?: error("`used_at` value not passed")
    }
}