package com.sdk.bigtextshower.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sdk.bigtextshower.R
import com.sdk.bigtextshower.customview.MyLedView
import com.sdk.bigtextshower.customview.TextLedView
import com.sdk.bigtextshower.viewmodel.MainViewModel
import com.sdk.bigtextshower.viewmodel.State
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class TextShowActivity : AppCompatActivity(), View.OnTouchListener {
    var scrollX = 0
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lp = window.attributes
        lp.screenBrightness = 200f
        window.attributes = lp
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContentView(R.layout.activity_text_show)
        handler = Handler(Looper.getMainLooper())

        val textLedView: TextLedView = findViewById(R.id.ledView)
        val horizontalScrollView: HorizontalScrollView = findViewById(R.id.scrollView)

        val state = intent.getParcelableExtra<State>("state")
        state?.let {
            textLedView.apply {
                Log.d("@@@", "onCreate: $it")
                ledText = it.text
                ledTextSize = it.textSize
                ledType = it.ledType
                ledColor = it.textColor
                setBackgroundColor(
                    ContextCompat.getColor(
                        this@TextShowActivity,
                        it.backgroundColor
                    )
                )
                font = it.font
            }
        }

        handler.post(object : Runnable {
            override fun run() {
                horizontalScrollView.scrollTo(scrollX, 0)
                scrollX += (textLedView.ledRadius + textLedView.ledSpace)
                if (scrollX <= 0 || scrollX >= textLedView.width - horizontalScrollView.width) {
                    scrollX = 0
                }
                handler.postDelayed(this, 10)
            }
        })
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return true
    }
}
