package com.sdk.bigtextshower.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.HorizontalScrollView
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sdk.bigtextshower.R
import com.sdk.bigtextshower.customview.MyLedView
import com.sdk.bigtextshower.customview.TextLedView
import com.sdk.bigtextshower.viewmodel.MainViewModel
import com.sdk.bigtextshower.viewmodel.State
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class TextShowActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var myLedView: MyLedView
    var scrollX = 0
    var direction = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lp = window.attributes
        lp.screenBrightness = 200f
        window.attributes = lp

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )
        setContentView(R.layout.activity_text_show)

        val scrollView: HorizontalScrollView = findViewById(R.id.horizontalSc)
        val textLedView: TextLedView = findViewById(R.id.ledView)
        val handler = Handler(mainLooper)
        textLedView.ledText = "         Hello❤️ "
        textLedView.ledType = "1"
        textLedView.ledColor = getColor(R.color.red)

        handler.post(object : Runnable {
            override fun run() {
                scrollView.scrollTo(scrollX, 0)
                scrollX += (textLedView.ledRadius + textLedView.ledSpace) * direction
                if (scrollX <= 0 || scrollX >= textLedView.width - scrollView.width) {
                    scrollX = 0
                }
                handler.postDelayed(this, 10)
            }
        })
    }
}