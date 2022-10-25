package com.sdk.bigtextshower.model

import android.graphics.Color
import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import com.sdk.bigtextshower.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class State(
    var text: String = "Preview",
    var textSize: Float = 220f,
    var textColor: Int = Color.MAGENTA,
    var textSpeed: Int = 200,
    var backgroundColor: Int = Color.GRAY,
    @FontRes val font: Int = R.font.baloo
): Parcelable