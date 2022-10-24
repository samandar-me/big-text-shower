package com.sdk.bigtextshower.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.sdk.bigtextshower.R
import com.sdk.bigtextshower.databinding.ItemLayoutBinding

class FontAdapter : RecyclerView.Adapter<FontAdapter.FontViewHolder>() {

    var fontList: MutableList<Int> = mutableListOf()
    private var selectedPos = -1
    private lateinit var context: Context

    lateinit var onClick: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FontViewHolder {
        context = parent.context
        return FontViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FontViewHolder, position: Int) {
        holder.bind(fontList[position])
    }

    override fun getItemCount(): Int = fontList.size

    inner class FontViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(font: Int) {
            val typeface = ResourcesCompat.getFont(context, font)
            binding.textView.typeface = typeface
            if (selectedPos == adapterPosition) {
                binding.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.green))
            } else {
                binding.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.gray_dark))
            }
            itemView.setOnClickListener {
                onClick(adapterPosition)
                setSingleItem(adapterPosition)
            }
        }
    }
    private fun setSingleItem(position: Int) {
        notifyItemChanged(selectedPos)
        selectedPos = position
        notifyItemChanged(selectedPos)
    }
}