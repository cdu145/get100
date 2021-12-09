package cdu145.view.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import cdu145.view.composable.BottomButton.IconGravity.Left
import cdu145.view.composable.BottomButton.IconGravity.Right

object BottomButton {

    enum class IconGravity { Right, Left }

    @SuppressLint("ComposableNaming")
    @Composable
    operator fun invoke(
        onClick: () -> Unit,
        iconGravity: IconGravity,
        modifier: Modifier = Modifier,
        icon: @Composable () -> Unit,
        text: @Composable () -> Unit,
    ) {
        Button(
            onClick,
            modifier = modifier.setWidthDependingOnLanguage(),
        ) {
            if (iconGravity == Left) {
                icon()
                Margin(4.dp)
            }
            if (Locale.current.language != "ru") {
                text()
            }
            if (iconGravity == Right) {
                Margin(4.dp)
                icon()
            }
        }
    }

    private fun Modifier.setWidthDependingOnLanguage(): Modifier {
        return then(
            if (Locale.current.language == "ru") {
                Modifier.width(100.dp)
            } else {
                Modifier
            }
        )
    }
}