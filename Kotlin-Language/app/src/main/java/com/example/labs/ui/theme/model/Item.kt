package edu.miu.myapplication.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Item (
    @field:StringRes
    @param:StringRes
    val title: Int, // it should take only from strings.xml

    @field:DrawableRes
    @param:DrawableRes
    val image: Int,
)