package cdu145.tickets.model.hint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import cdu145.tickets.data.RestoringHintsDao

class ReviseAvailableHints(
    private val restoringHintsDao: RestoringHintsDao,
    private val restoringHintDeletion: RestoringHintDeletion,
    private val scope: CoroutineScope,
) {

    operator fun invoke() {
        scope.launch {
            restoringHintsDao.transaction {
                all().forEach {
                    if (it.timeToRestore <= 0) {
                        delete(it)
                    } else {
                        restoringHintDeletion.schedule(it)
                    }
                }
            }
        }
    }

    private val RestoringHint.timeToRestore: Long
        get() = HintRestorationTime - (System.currentTimeMillis() - this.usedAt)
}