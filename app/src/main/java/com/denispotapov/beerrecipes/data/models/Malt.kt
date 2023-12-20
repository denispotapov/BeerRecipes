package com.denispotapov.beerrecipes.data.models

import androidx.compose.runtime.MutableState

data class Malt(val name: MutableState<String>, val weight: Int = 0)
