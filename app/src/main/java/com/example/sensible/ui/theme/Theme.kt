package com.example.sensible.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Brown200,
    primaryVariant = Brown50,
    secondary = Orange200,
    onPrimary = Color.White,
    onSecondary = Color.White,
    surface = DarkSurface
)

private val LightColorPalette = lightColors(
    primary = Brown500,
    primaryVariant = Brown700,
    secondary = Orange200,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

//private val ExtLightColorPalette = ExtendedColors(LightColorPalette,Color.White,Color.LightGray,Color.Gray)
private val ExtDarkColorPalette = ExtendedColors(
    material = DarkColorPalette,
    carbs = OrangeA100,
    fat = OrangeA200,
    protein = OrangeA400
    )

private val LocalColors = staticCompositionLocalOf { ExtDarkColorPalette }

val MaterialTheme.extendedColors: ExtendedColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

@Composable
fun SensibleTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = Color.Transparent
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }

    val colors = if (darkTheme) {
        ExtDarkColorPalette
    } else {
        //ExtLightColorPalette
        ExtDarkColorPalette
    }

    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            colors = colors.material,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}