package com.denispotapov.beerrecipes.data.models

data class Recipe(
    val name: String,
    val malts: List<Malt>,
    val hops: List<Hop>,
    val yeast: Yeast,
    val tempPauses: List<TemperaturePause>,
    val initialDensity: String,
    val finalDensity: String,
    val alcohol: String,
    val mashWater: String,
    val spargeWater: String,
    val allWater: String
)
