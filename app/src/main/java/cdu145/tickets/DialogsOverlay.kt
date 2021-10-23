package cdu145.tickets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.get
import org.koin.core.component.createScope
import org.koin.core.component.getScopeOrNull
import cdu145.actual.Actual
import cdu145.tickets.guide.Guide
import cdu145.tickets.guide.GuideCompletedFlag
import cdu145.tickets.guide.GuideDialog
import cdu145.tickets.guide.GuideDialogStateFlow
import cdu145.tickets.hint.AlmostCompletedDialog
import cdu145.tickets.hint.AlmostCompletedDialogState
import cdu145.tickets.hint.NoHintsAvailableDialogState
import cdu145.tickets.hint.available.dialog.NoHintsAvailableDialog
import cdu145.tickets.ratedialog.RateDialog
import cdu145.ui.state.DialogState
import cdu145.ui.state.DialogState.Shown

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