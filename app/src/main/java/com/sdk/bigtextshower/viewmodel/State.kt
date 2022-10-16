package com.sdk.bigtextshower.viewmodel

import android.os.Parcelable
import androidx.annotation.FontRes
import com.sdk.bigtextshower.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class State(
    val text: String = "Preview",
    val textSize: Float = 220f,
    val textColor: String = "#E91E63",
    val textSpeed: Int = 200,
    val backgroundColor: String = "#03203C",
    @FontRes val font: Int = R.font.baloo
): Parcelable