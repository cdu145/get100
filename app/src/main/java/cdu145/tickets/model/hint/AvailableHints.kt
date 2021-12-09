package cdu145.tickets.model.hint

import cdu145.tickets.domain.hint.available.AvailableHints
import cdu145.tickets.domain.hint.available.AvailableHints.Companion.MaxCount
import cdu145.tickets.data.RestoringHintsDao

fun AvailableHints(
    restoringHintsDao: RestoringHintsDao,
    restoringHintDeletion: RestoringHintDeletion,
): AvailableHints {
    return object : AvailableHints {

        override suspend fun acquire(block: suspend () -> Unit) {
            restoringHintsDao.transaction {
                check(count() < MaxCount) { "No hints available" }

                val restoringHint = RestoringHint()
                insert(restoringHint)
                restoringHintDeletion.schedule(restoringHint)

                block.invoke()
            }
        }
    }
}