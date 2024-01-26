package com.example.sellerapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sellerapp.data.model.LifeTimeData
import com.example.sellerapp.databinding.ItemTimePieceBinding

class LifeTimeAdapter : ListAdapter<LifeTimeData, LifeTimeAdapter.MyHolder> (myDiffUtil) {

    inner class MyHolder(private val binding: ItemTimePieceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val dt = getItem(absoluteAdapterPosition)
            binding.muddat.text = dt.date
            binding.summa.text = dt.summa
            binding.tolandi.text = dt.payment
        }
    }

    object myDiffUtil : DiffUtil.ItemCallback<LifeTimeData>() {
        override fun areItemsTheSame(oldItem: LifeTimeData, newItem: LifeTimeData): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: LifeTimeData, newItem: LifeTimeData): Boolean {
            return oldItem.date == newItem.date
                    && oldItem.payment == newItem.payment
                    && oldItem.summa == newItem.summa
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemTimePieceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind()
    }
}