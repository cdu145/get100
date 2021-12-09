package cdu145.view.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import cdu145.tickets.R

val Typography = Typography(
    defaultFontFamily = FontFamily(
        Font(R.font.rubik_regular, weight = FontWeight.Normal),
        Font(R.font.rubik_medium, weight = FontWeight.Medium),
    ),
)

val AveriaFontFamily = FontFamily(Font(R.font.averia_regular))