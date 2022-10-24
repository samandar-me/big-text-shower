package com.sdk.bigtextshower.util

import androidx.annotation.FontRes
import com.sdk.bigtextshower.R

object Constants {
    @FontRes
    fun fontList(): MutableList<Int> {
        val list = mutableListOf(
            R.font.baloo,
            R.font.akronim,
            R.font.aldrich,
            R.font.arizonia,
            R.font.autour_one,
            R.font.knewave,
        )
        return list
    }
}