package cdu145.tickets.data

import androidx.room.*
import cdu145.tickets.model.hint.HintUseTimestamp
import cdu145.tickets.model.hint.RestoringHint
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RestoringHintsDao {

    @Transaction
    open suspend fun transaction(block: suspend RestoringHintsDao.() -> Unit) {
        return block.invoke(this)
    }

    @Query("SELECT count(*) FROM restoring_hints")
    abstract suspend fun count(): Int

    @Query("SELECT count(*) FROM restoring_hints")
    abstract fun countFlow(): Flow<Int>

    @Insert
    abstract suspend fun insert(entity: RestoringHint)

    @Delete
    abstract suspend fun delete(entity: RestoringHint): Int

    @Query("SELECT * FROM restoring_hints")
    abstract suspend fun all(): List<RestoringHint>

    @Query("SELECT usedAt FROM restoring_hints ORDER BY usedAt ASC LIMIT 1")
    abstract suspend fun earliestUsedAt(): HintUseTimestamp
}