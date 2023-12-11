package com.denispotapov.beerrecipes.ui.compose_ui.styles

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import com.denispotapov.beerrecipes.R

@Composable
fun composeTextStyle(
    @ColorRes colorRes: Int,
    font: AppFonts,
    @DimenRes fontSizeRes: Int,
    @DimenRes lineHeightRes: Int,
    letterSpacing: Double
): TextStyle {
    val context = LocalContext.current
    val color = colorResource(id = colorRes)
    val fontSize = dimensionResource(id = fontSizeRes).value.sp
    val lineHeight = dimensionResource(id = lineHeightRes).value.sp
    val fontFamily = remember(font) {
        ResourcesCompat.getFont(context, font.res)?.let {
            FontFamily(typeface = it)
        }
    }

    return remember(color, fontSize, lineHeight, letterSpacing, fontFamily) {
        TextStyle(
            color = color,
            fontSize = fontSize,
            lineHeight = lineHeight,
            letterSpacing = (letterSpacing).sp,
            fontFamily = fontFamily,
            fontStyle = FontStyle.Normal,
            fontWeight = font.weight
        )
    }
}

@Composable
fun TextTitle1(@ColorRes color: Int): TextStyle = composeTextStyle(
    colorRes = color,
    font = AppFonts.SansDisplayRegular,
    fontSizeRes = R.dimen.text_size_title1,
    lineHeightRes = R.dimen.text_size_title1_height,
    letterSpacing = 0.01
)

@Composable
fun TextHeadline(@ColorRes color: Int): TextStyle = composeTextStyle(
    colorRes = color,
    font = AppFonts.SansDisplaySemibold,
    fontSizeRes = R.dimen.text_size_headline,
    lineHeightRes = R.dimen.text_size_headline_height,
    letterSpacing = -0.02
)

@Composable
fun TextSubheadlineRegular(
    @ColorRes color: Int,
    @DimenRes fontSizeRes: Int = R.dimen.text_size_subheadline
): TextStyle = composeTextStyle(
    colorRes = color,
    font = AppFonts.SansTextRegular,
    fontSizeRes = fontSizeRes,
    lineHeightRes = R.dimen.text_size_subheadline_height,
    letterSpacing = -0.02
)

@Composable
fun TextBodyMedium(@ColorRes color: Int): TextStyle = composeTextStyle(
    colorRes = color,
    font = AppFonts.SansTextMedium,
    fontSizeRes = R.dimen.text_size_body,
    lineHeightRes = R.dimen.text_size_body_height,
    letterSpacing = -0.02
)

@Composable
fun TextFootnoteRegular(@ColorRes color: Int): TextStyle = composeTextStyle(
    colorRes = color,
    font = AppFonts.SansTextRegular,
    fontSizeRes = R.dimen.text_size_footnote,
    lineHeightRes = R.dimen.text_size_footnote_height,
    letterSpacing = -0.08
)

@Composable
fun TextFootnoteSemiBold(@ColorRes color: Int): TextStyle = composeTextStyle(
    colorRes = color,
    font = AppFonts.SansDisplaySemibold,
    fontSizeRes = R.dimen.text_size_footnote,
    lineHeightRes = R.dimen.text_size_footnote_height,
    letterSpacing = -0.08
)

@Composable
fun TextCaptionRegular(color: Int): TextStyle = composeTextStyle(
        colorRes = color,
        font = AppFonts.SansTextRegular,
        fontSizeRes = R.dimen.text_size_caption1,
        lineHeightRes = R.dimen.text_size_caption1_height,
        letterSpacing = -0.26
    )