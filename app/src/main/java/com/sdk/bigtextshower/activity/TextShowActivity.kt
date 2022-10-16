package com.sdk.bigtextshower.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.sdk.bigtextshower.R
import com.sdk.bigtextshower.customview.MyLedView

class TextShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lp = window.attributes
        lp.screenBrightness = 200f
        window.attributes = lp

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val constraintLayout = ConstraintLayout(this)
        val layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        constraintLayout.layoutParams = layoutParams
        val myLedView = MyLedView(this)
        myLedView.apply {
            setText("textView")
            setTextSize(221f)
            setBackgroundColor("#03203C")
            setTextSpeed(100)
            setTextColor("#E91E63")
            setFont(R.font.baloo)
        }
        constraintLayout.addView(myLedView)

        setContentView(constraintLayout, layoutParams)
    }
}