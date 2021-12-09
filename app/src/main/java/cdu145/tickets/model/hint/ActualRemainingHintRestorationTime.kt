package cdu145.tickets.model.hint

import cdu145.model.Actual
import cdu145.tickets.domain.hint.restoring.RemainingRestorationTime
import cdu145.tickets.data.RestoringHintsDao

class ActualRemainingHintRestorationTime(
    private val restoringHintsDao: RestoringHintsDao,
) : Actual<RemainingRestorationTime> {

    override suspend fun value(): RemainingRestorationTime {
        return RemainingRestorationTime(
            millis = HintRestorationTime - (System.currentTimeMillis() - restoringHintsDao.earliestUsedAt()),
        )
    }
}