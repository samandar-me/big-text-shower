package com.sdk.bigtextshower.viewmodel

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import com.sdk.bigtextshower.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class State(
    val text: String = "Preview",
    val textSize: Int = 220,
    @ColorRes val textColor: Int = R.color.red,
    val textSpeed: Int = 200,
    @ColorRes val backgroundColor: Int = R.color.black_light,
    @FontRes val font: Int = R.font.baloo,
    val ledType: String = "1"
): Parcelable