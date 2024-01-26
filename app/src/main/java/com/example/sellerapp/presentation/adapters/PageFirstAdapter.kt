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
import java.util.Timer

class PageFirstAdapter: ListAdapter<UserData, PageFirstAdapter.ClientViewHolder>(GroupDiffUtil) {
    private var LongSelectListener: ((UserData) -> Unit)?=null
    private var selectedItemListener: ((UserData) -> Unit)?=null

    object GroupDiffUtil : DiffUtil.ItemCallback<UserData>() {
        override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
            return oldItem.firstName == newItem.firstName  && oldItem.phoneNumber == newItem.phoneNumber
        }
    }

    inner class ClientViewHolder(private var binding: ItemAddClientBinding) : ViewHolder(binding.root) {
        private val firstname = binding.firstname
        private val date = binding.date
        private val lastname = binding.lastname
        private val number = binding.number
        var time = System.currentTimeMillis()

        init {
            binding.root.setOnClickListener {
                selectedItemListener?.invoke(currentList[absoluteAdapterPosition])
            }

            binding.root.setOnLongClickListener {
                LongSelectListener?.invoke(currentList[absoluteAdapterPosition])

                return@setOnLongClickListener true
            }
        }

        fun bind() {
            firstname.text = currentList[absoluteAdapterPosition].firstName
            lastname.text = currentList[absoluteAdapterPosition].secondName
            number.text = currentList[absoluteAdapterPosition].phoneNumber
            date.text = getDate(System.currentTimeMillis(), "dd/MM/yyyy")
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
        this.LongSelectListener = block
    }
    fun setSelectedItemListener(block : (UserData) -> Unit) {
        this.selectedItemListener = block
    }
}