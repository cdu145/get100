package cdu145.tickets.di

import cdu145.model.Actual
import cdu145.model.DialogState.Hidden
import cdu145.tickets.data.AppDatabase
import cdu145.tickets.domain.hint.SuggestedHint
import cdu145.tickets.model.hint.*
import cdu145.tickets.view.composable.hint.AlmostCompletedDialogImpl
import cdu145.tickets.viewmodel.HintButtonViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module

val JustRevealedGapChannel = StringQualifier("OpenedSignPositionChannel")
val NoHintsAvailableDialogState = StringQualifier("NoHintsAvailableDialogStateFlow")
val AlmostCompletedDialogState = StringQualifier("AlmostThereDialogState")
val RemainingRestorationTime = StringQualifier("RemainingRestorationTime")

val HintModule = module {
    single(JustRevealedGapChannel) {
        Channel<Int>(capacity = Channel.CONFLATED)
    } bind SendChannel ::class bind ReceiveChannel::class

    factory { get<AppDatabase>().restoringHintsDao }

    val availableCountFlow = StringQualifier("AvailableHintCountFlow")
    factory(availableCountFlow) { AvailableHintCountFlow(get()) }

    single(NoHintsAvailableDialogState) { MutableStateFlow(Hidden) } bind StateFlow::class
    single(AlmostCompletedDialogState) { MutableStateFlow(Hidden) } bind StateFlow::class

    viewModel {
        HintButtonViewModel(
            get(availableCountFlow),
            get(SolutionResultFlow),
            SuggestedHint(
                get(CorrectSolutions),
                get(Solution),
                AlmostCompletedDialogImpl(
                    get(AlmostCompletedDialogState),
                ),
                AvailableHints(
                    get(),
                    get(),
                ),
                get(JustRevealedGapChannel),
            ),
            get(NoHintsAvailableDialogState),
        )
    }

    factory { RestoringHintDeletion(get()) }

    factory<Actual<*>>(RemainingRestorationTime) { ActualRemainingHintRestorationTime(get()) }

    factory {
        ReviseAvailableHints(
            get(),
            get(),
            get(ApplicationCoroutineScope),
        )
    }
}