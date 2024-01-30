package com.example.sellerapp.presentation.adapters

import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sellerapp.data.model.TodayPaysData
import com.example.sellerapp.databinding.ItemLatePayBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

class LatePaysAdapter : ListAdapter<TodayPaysData, LatePaysAdapter.MyHolder>(MyDiffUtil) {
    private var listenerOnClick : ((TodayPaysData) -> Unit)? = null
    private var onClickEdit : ((TodayPaysData) -> Unit)? = null
    inner class MyHolder(private val binding: ItemLatePayBinding) : ViewHolder(binding.root) {
        init {
            binding.item.setOnClickListener {
                listenerOnClick?.invoke(getItem(absoluteAdapterPosition))
            }
            binding.btnEdit.setOnClickListener {
                onClickEdit?.invoke(getItem(absoluteAdapterPosition))
            }
        }
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.fullName.text = item.fullName
            binding.number.text = item.phoneNumber
            binding.productName.text = item.productName
            binding.productPrice.text = amountFormat(item.priceProduct)
            binding.startDate.text = getDate(item.startDate)
            binding.comment.text = item.comment
            binding.paymentDeadline.text = getDate(item.payingDeadline)
        }
    }

    object MyDiffUtil : DiffUtil.ItemCallback<TodayPaysData>() {
        override fun areItemsTheSame(oldItem: TodayPaysData, newItem: TodayPaysData): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: TodayPaysData, newItem: TodayPaysData): Boolean {
            return false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemLatePayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind()
    }

    fun setOnClickListener(listenerOnClick : (TodayPaysData) -> Unit) {
        this.listenerOnClick = listenerOnClick
    }

    @SuppressLint("SimpleDateFormat", "NewApi")
    private fun getDate(millisecond : Long) : String {
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millisecond
        return formatter.format(calendar.time)
    }

    private fun amountFormat(amount : Long) : String {
        return String.format("%,d", amount).replace(",", " ")
    }
    private fun amountFormat(amount : Double) : String {
        return NumberFormat.getInstance(Locale.getDefault()).format(amount)
    }
    fun setOnclickEdit(onClickEdit : (TodayPaysData) -> Unit) {
        this.onClickEdit = onClickEdit
    }
}