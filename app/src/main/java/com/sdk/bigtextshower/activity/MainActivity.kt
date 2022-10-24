package com.sdk.bigtextshower.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrudultora.colorpicker.ColorPickerBottomSheetDialog
import com.mrudultora.colorpicker.listeners.OnSelectColorListener
import com.mrudultora.colorpicker.util.ColorItemShape
import com.sdk.bigtextshower.R
import com.sdk.bigtextshower.adapter.FontAdapter
import com.sdk.bigtextshower.databinding.ActivityMainBinding
import com.sdk.bigtextshower.util.Constants
import com.sdk.bigtextshower.viewmodel.MainViewModel
import com.sdk.bigtextshower.viewmodel.State


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val fontAdapter by lazy { FontAdapter() }
    private var checkedFont = 0
    private var backColor = -1
    private var textColor = -1
    private var fontList: MutableList<Int> = Constants.fontList()
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRv()
        binding.btnStart.setOnClickListener {
            val text = binding.editTextTextPersonName.text.toString().trim()
            val textSize = binding.seekBar.progressRight.toFloat()

        }
        binding.linearText.setOnClickListener {
            showBottomSheet(true)
        }
        binding.linearBack.setOnClickListener {
            showBottomSheet(false)
        }
        fontAdapter.onClick = {
            checkedFont = fontList[it]
        }
    }

    private fun showBottomSheet(isForText: Boolean) {
        val colorPickerDialog = ColorPickerBottomSheetDialog(this)
        colorPickerDialog.apply {
            setColors()
            setColorItemShape(ColorItemShape.CIRCLE)
            setOnSelectColorListener(object : OnSelectColorListener {
                override fun onColorSelected(color: Int, position: Int) {
                    if (isForText) {
                        textColor = color
                        binding.shapeText.setBackgroundColor(color)
                    } else {
                        backColor = color
                        binding.shapeBack.setBackgroundColor(color)
                    }
                }

                override fun cancel() {
                    colorPickerDialog.dismissDialog()
                }
            })
        }
        colorPickerDialog.show()
    }

    private fun setupRv() = binding.rv.apply {
        layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        adapter = fontAdapter
        fontAdapter.fontList = fontList
    }
}