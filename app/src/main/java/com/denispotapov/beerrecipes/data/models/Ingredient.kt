package com.denispotapov.beerrecipes.data.models

import androidx.compose.runtime.MutableState

sealed class Ingredient {

    data class Malt(val name: MutableState<String>, val weight: Int = 0) : Ingredient()

    data class Hop(
        val name: MutableState<String>,
        val alphaAcid: String = "",
        val boilingTime: String = "",
        val weight: Int = 0
    ) : Ingredient()

    data class Yeast(val name: MutableState<String>) : Ingredient()

}
