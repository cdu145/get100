package cdu145.tickets.view.composable.solutionresult

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring.DampingRatioMediumBouncy
import androidx.compose.animation.core.spring
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import cdu145.model.Loadable.Ready
import cdu145.tickets.model.OneShotVibration
import cdu145.tickets.view.composable.solutionresult.SolutionResultTextColor.WhenNotSolved
import cdu145.tickets.view.composable.solutionresult.SolutionResultTextColor.WhenSolved
import cdu145.tickets.view.composable.solutionresult.SolutionResultViewState.NotSolved
import cdu145.tickets.view.composable.solutionresult.SolutionResultViewState.NotSolved.Sign.AlmostEqualTo
import cdu145.tickets.view.composable.solutionresult.SolutionResultViewState.NotSolved.Sign.EqualTo
import cdu145.tickets.view.composable.solutionresult.SolutionResultViewState.NotSolved.Value.Defined
import cdu145.tickets.view.composable.solutionresult.SolutionResultViewState.NotSolved.Value.Undefined
import cdu145.tickets.view.composable.solutionresult.SolutionResultViewState.Solved
import cdu145.tickets.viewmodel.SolutionResultViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@Composable
fun SolutionResultView(
    modifier: Modifier = Modifier,
    viewModel: SolutionResultViewModel = getViewModel(),
    oneShotVibration: OneShotVibration = get(),
) {
    val loadableState by viewModel.state.collectAsState()
    var text by remember { mutableStateOf("") }
    var color by remember { mutableStateOf(WhenNotSolved) }
    val alpha = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }
    if (loadableState is Ready) {
        val state = (loadableState as Ready).value
        LaunchedEffect(state) {
            alpha.animateTo(0f)
            when (state) {
                is Solved -> {
                    text = "=\n100"
                    color = WhenSolved
                    launch {
                        scale.animateTo(
                            targetValue = 1.6f,
                            animationSpec = spring(dampingRatio = DampingRatioMediumBouncy),
                        )
                    }
                    oneShotVibration.start(duration = 200)
                }
                is NotSolved -> {
                    text = createTextFrom(state)
                    color = WhenNotSolved
                    scale.snapTo(1f)
                }
            }
            alpha.animateTo(1f)
        }
    }
    Text(
        text,
        color = color.value,
        textAlign = TextAlign.Center,
        fontSize = 32.sp,
        modifier = modifier.graphicsLayer {
            this.alpha = alpha.value
            transformOrigin = TransformOrigin(pivotFractionX = 0.5f, pivotFractionY = 0f)
            scaleX = scale.value
            scaleY = scale.value
        },
    )
}

private fun createTextFrom(state: NotSolved): String = with(state) { "${sign.text}\n${value.text}" }

private val NotSolved.Value.text: String
    get() = when (this) {
        is Undefined -> "???"
        is Defined -> this.value.toString()
    }

private val NotSolved.Sign.text: String
    get() = when (this) {
        EqualTo -> "="
        AlmostEqualTo -> "≈"
    }

private val SolutionResultTextColor.value: Color
    @Composable
    get() = when (this) {
        WhenSolved -> MaterialTheme.colors.primary
        WhenNotSolved -> MaterialTheme.colors.onSurface
    }
