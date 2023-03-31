package com.tamayo.jetrecycler

import androidx.annotation.DrawableRes

data class SuperHero(
    val superHeroName: String,
    val realName: String,
    val publisher: String,
    @DrawableRes var photo: Int
)
