package com.example.sellerapp.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.databinding.ItemProductBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ProductListAdapter : ListAdapter<ProductData, ProductListAdapter.MyHolder>(MyDifUtil) {
    private var onClickListener : ((ProductData) -> Unit) = {}
    private var longClickListener : ((ProductData) -> Unit) = {}
    inner class MyHolder(private val binding: ItemProductBinding) : ViewHolder(binding.root) {
        init {
            binding.item.setOnClickListener {
                onClickListener.invoke(getItem(absoluteAdapterPosition))
            }
            binding.item.setOnLongClickListener {
                longClickListener.invoke(getItem(absoluteAdapterPosition))
                return@setOnLongClickListener true
            }
        }
        @SuppressLint("SetTextI18n")
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.productName.text = item.productName
            binding.priceProduct.text = formatterAmount(item.priceProduct) + " so'm"
            binding.monthOfRent.text = item.monthOfRent.toString()
            binding.checkPay.text = formatterAmount(item.checkPay) + " so'm"
            binding.comment.text = item.comment
            binding.advancePayment.text = formatterAmount(item.advancePayment) + " so'm"
            binding.startDate.text = getDate(item.startDate, "dd.MM.yyyy")
        }
    }

    object MyDifUtil : DiffUtil.ItemCallback<ProductData>() {
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
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
    fun setOnClickListener(onClickListener : (ProductData) -> Unit) {
        this.onClickListener = onClickListener
    }
    fun formatterAmount(amount: Long) : String{
        return String.format("%,d", amount).replace(",", " ")
    }
    fun formatterAmount(amount: Double) : String{
        val formatter = NumberFormat.getInstance(Locale.getDefault())
        return formatter.format(amount)
    }
    fun setLongClickListener(longClickListener : (ProductData) -> Unit) {
        this.longClickListener = longClickListener
    }
}