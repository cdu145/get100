package cdu145.tickets.view.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cdu145.model.Actual
import cdu145.model.DialogState
import cdu145.model.DialogState.Shown
import cdu145.tickets.di.AlmostCompletedDialogState
import cdu145.tickets.di.GuideCompletedFlag
import cdu145.tickets.di.GuideDialogStateFlow
import cdu145.tickets.di.NoHintsAvailableDialogState
import cdu145.tickets.model.guide.Guide
import cdu145.tickets.view.composable.guide.GuideDialog
import cdu145.tickets.view.composable.hint.AlmostCompletedDialog
import cdu145.tickets.view.composable.hint.NoHintsAvailableDialog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.get
import org.koin.core.component.createScope
import org.koin.core.component.getScopeOrNull

@Composable
fun DialogsOverlay(
    noHintsAvailableDialogStateFlow: StateFlow<DialogState> = get(NoHintsAvailableDialogState),
    almostThereDialogStateFlow: StateFlow<DialogState> = get(AlmostCompletedDialogState),
    guideCompletedFlag: Actual<Boolean> = get(GuideCompletedFlag),
    guideDialogStateFlow: MutableStateFlow<DialogState> = get(GuideDialogStateFlow),
) {
    val noHintsAvailableDialogState by noHintsAvailableDialogStateFlow.collectAsState()
    if (noHintsAvailableDialogState == Shown) {
        NoHintsAvailableDialog()
    }

    val almostThereDialogState by almostThereDialogStateFlow.collectAsState()
    if (almostThereDialogState == Shown) {
        AlmostCompletedDialog()
    }

    LaunchedEffect(null) {
        if (!guideCompletedFlag.value()) {
            guideDialogStateFlow.value = Shown
        }
    }
    val guideDialogState by guideDialogStateFlow.collectAsState()
    guideDialogState.let {
        if (it == Shown) {
            Guide.createScope()
            GuideDialog()
        } else {
            Guide.getScopeOrNull()?.close()
        }
    }

    RateDialog()
}