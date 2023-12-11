package com.denispotapov.beerrecipes.ui.compose_ui.styles

import androidx.compose.ui.text.font.FontWeight
import com.denispotapov.beerrecipes.R

sealed class AppFonts(val res: Int, val weight: FontWeight) {
    object SansDisplayRegular : AppFonts(R.font.sb_sans_display_regular, FontWeight.W400)
    object SansDisplaySemibold : AppFonts(R.font.sb_sans_display_semibold, FontWeight.W600)
    object SansTextMedium : AppFonts(R.font.sb_sans_text_medium, FontWeight.W400)
    object SansTextRegular : AppFonts(R.font.sb_sans_text_regular, FontWeight.W400)
}
