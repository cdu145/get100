package cdu145.tickets.domain.hint

internal class SolutionAlmostCompletedHint(
    private val almostCompletedDialog: AlmostCompletedDialog,
) : Hint {

    override suspend fun use() {
        almostCompletedDialog.show()
    }
}