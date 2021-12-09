package cdu145.tickets.domain.hint

import cdu145.model.Actual
import cdu145.tickets.domain.hint.available.AvailableHints
import cdu145.tickets.domain.solution.AlteredSolution
import cdu145.tickets.domain.solution.Solution
import cdu145.tickets.domain.solution.sign.replacement.SignReplacement
import kotlinx.coroutines.channels.SendChannel

internal class RevealSignHint(
    private val replacement: SignReplacement,
    private val solution: Actual.Mutable<Solution>,
    private val availableHints: AvailableHints,
    private val justRevealedGapChannel: SendChannel<Int>,
) : Hint {

    override suspend fun use() {
        availableHints.acquire {
            solution.mutate {
                AlteredSolution(
                    original = it,
                    targetPosition = replacement.position,
                    newSign = replacement.newSign,
                )
            }
            justRevealedGapChannel.send(replacement.position)
        }
    }
}