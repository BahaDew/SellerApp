package com.example.sellerapp.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.ItemAddClientBinding
import java.text.SimpleDateFormat
import java.util.Calendar

class PageFirstAdapter: ListAdapter<UserData, PageFirstAdapter.ClientViewHolder>(GroupDiffUtil) {
    private var longSelectListener: ((UserData) -> Unit)?=null
    private var selectedItemListener: ((UserData) -> Unit)?=null

    object GroupDiffUtil : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    inner class ClientViewHolder(private var binding: ItemAddClientBinding) : ViewHolder(binding.root) {
        var time = System.currentTimeMillis()

        init {
            binding.root.setOnClickListener {
                selectedItemListener?.invoke(currentList[absoluteAdapterPosition])
            }
            binding.root.setOnLongClickListener {
                longSelectListener?.invoke(currentList[absoluteAdapterPosition])
                return@setOnLongClickListener true
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            binding.fullName.text = "${item.firstName} ${item.secondName}"
            binding.number.text = item.phoneNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder(ItemAddClientBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bind()
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(milliSeconds: Long, dateFormat: String): String {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = milliSeconds
        }
        return formatter.format(calendar.time)
    }


    fun setLongSelectListener(block : (UserData) -> Unit) {
        this.longSelectListener = block
    }
    fun setSelectedItemListener(block : (UserData) -> Unit) {
        this.selectedItemListener = block
    }
}