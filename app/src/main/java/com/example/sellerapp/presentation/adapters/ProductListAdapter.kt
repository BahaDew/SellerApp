package com.example.sellerapp.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.databinding.ItemAddClientBinding
import com.example.sellerapp.databinding.ItemProductBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class ProductListAdapter : ListAdapter<ProductData, ProductListAdapter.MyHolder>(difUtil) {
    private var onClickListener : ((ProductData) -> Unit) = {}
    inner class MyHolder(private val binding: ItemProductBinding) : ViewHolder(binding.root) {
        init {
            binding.item.setOnClickListener {
                onClickListener.invoke(getItem(absoluteAdapterPosition))
            }
        }
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.productName.text = item.productName
            binding.priceProduct.text = item.priceProduct.toString()
            binding.monthOfRent.text = item.monthOfRent
            binding.checkPay.text = item.checkPay.toString()
            binding.comment.text = item.comment
            binding.advancePayment.text = item.advance_payment
            binding.startDate.text = getDate(item.startDate, "dd.MM.yyyy")
        }
    }

    object difUtil : DiffUtil.ItemCallback<ProductData>() {
        override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
            return false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind()
    }
    @SuppressLint("SimpleDateFormat")
    private fun getDate(milliSeconds: Long, dateFormat: String): String {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = milliSeconds
        }
        return formatter.format(calendar.time)
    }
    fun setOnClickListener(onClickListener : (ProductData) -> Unit) {
        this.onClickListener = onClickListener
    }
}