package cdu145.tickets.model.hint

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import cdu145.tickets.data.RestoringHintsDao
import cdu145.tickets.domain.hint.available.AvailableHints

@Suppress("FunctionName")
fun AvailableHintCountFlow(
    restoringHintsDao: RestoringHintsDao,
): Flow<Int> {
    return restoringHintsDao.countFlow().map { AvailableHints.MaxCount - it }
}