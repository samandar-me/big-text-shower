package com.sdk.bigtextshower.activity

import android.content.Intent
import android.os.Bundle
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
//
//        viewModel.apply {
//            setText("Test")
//            setFont(R.font.baloo)
//            setBackgroundColor("#242B2E")
//            setTextSize(220f)
//            setTextSpeed(60)
//            setTextColor("#E91E63")
//        }
        binding.btn.setOnClickListener {
            val bundle = bundleOf("state" to State())
            val intent = Intent(this, TextShowActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}