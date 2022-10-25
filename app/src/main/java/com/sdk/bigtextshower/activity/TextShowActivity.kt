package com.sdk.bigtextshower.activity

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.sdk.bigtextshower.R
import com.sdk.bigtextshower.customview.MyLedView
import com.sdk.bigtextshower.model.State

class TextShowActivity : AppCompatActivity() {
    private lateinit var ledView: MyLedView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lp = window.attributes
        lp.screenBrightness = 200f
        window.attributes = lp
        @Suppress("DEPRECATION")
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.insetsController?.hide(WindowInsets.Type.statusBars())
            } else {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        setContentView(R.layout.activity_text_show)
        ledView = findViewById(R.id.ledView)

        val state = intent.getParcelableExtra<State>("state")

        state?.let {
            ledView.apply {
                setText(it.text)
                setTextSize(it.textSize)
                setTextColor(it.textColor)
                setBackgroundColor(it.backgroundColor)
                setFont(it.font)
                invalidate()
            }
        }
    }
}
