package com.sdk.bigtextshower.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.sdk.bigtextshower.R
import com.sdk.bigtextshower.databinding.ActivityMainBinding
import com.sdk.bigtextshower.viewmodel.MainViewModel
import com.sdk.bigtextshower.viewmodel.State


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val state =
                State("Preview", 200, R.color.red, 200, R.color.black_light, R.font.baloo, "1")
            val bundle = bundleOf("state" to state)
            val intent = Intent(this, TextShowActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }, 3000)

    }
}