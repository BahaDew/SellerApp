package com.example.sellerapp.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sellerapp.R
import com.example.sellerapp.data.model.PayMonthData
import com.example.sellerapp.databinding.ItemTimePieceBinding

class PayMonthAdapter :
    ListAdapter<PayMonthData, PayMonthAdapter.ClientInfoViewHolder>(MyDiffUtil) {
    private var longClickListener: ((PayMonthData) -> Unit)? = null

    object MyDiffUtil : DiffUtil.ItemCallback<PayMonthData>() {
        override fun areItemsTheSame(oldItem: PayMonthData, newItem: PayMonthData): Boolean {
            return oldItem.muddat == newItem.muddat
        }

        override fun areContentsTheSame(oldItem: PayMonthData, newItem: PayMonthData): Boolean {
            return oldItem.muddat == newItem.muddat
                    && oldItem.summa == newItem.summa
                    && oldItem.tulangan == newItem.tulangan
        }
    }

    inner class ClientInfoViewHolder(private val binding: ItemTimePieceBinding) :
        ViewHolder(binding.root) {
        init {
            binding.item.setOnLongClickListener {
                longClickListener?.invoke(getItem(absoluteAdapterPosition))
                return@setOnLongClickListener true
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            val data = getItem(absoluteAdapterPosition)
            binding.summa.text = "${formatAmount(data.summa.toString())} so'm"
            binding.muddat.text = data.muddat
            when (data.tulangan) {
                1 -> {
                    binding.tolandi.setText(R.string.checked_tulov)
                    binding.item.setBackgroundResource(R.color.checked_tulov)
                }

                0 -> {
                    binding.tolandi.setText(R.string.unchecked_tulov)
                    binding.item.setBackgroundResource(R.color.unchecked_tulov)
                }

                else -> {
                    binding.tolandi.setText(R.string.not_tulov)
                    binding.item.setBackgroundResource(R.color.not_tulov)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientInfoViewHolder {
        return ClientInfoViewHolder(
            ItemTimePieceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ClientInfoViewHolder, position: Int) {
        holder.bind()
    }

    fun setLongClickListener(longClickListener: ((data: PayMonthData) -> Unit)) {
        this.longClickListener = longClickListener
    }
    fun formatAmount(amountString: String): String {
        val amount = amountString.toLongOrNull()
        if (amount != null) {
            val formattedAmount = String.format("%,d", amount)
            return formattedAmount.replace(",", " ")
        }
        return "Invalid amount"
    }
}