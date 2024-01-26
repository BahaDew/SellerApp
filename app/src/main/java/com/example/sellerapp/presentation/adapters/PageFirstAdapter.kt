package com.example.sellerapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.ItemAddClientBinding

class PageFirstAdapter: ListAdapter<UserData, PageFirstAdapter.ClientViewHolder>(GroupDiffUtil) {
    private var selectStudentIDListener: ((UserData) -> Unit)?=null
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
        private val firstName = binding.firstname
        private val lastName = binding.lastname

        init {
            binding.root.setOnClickListener {
                selectedItemListener?.invoke(currentList[absoluteAdapterPosition])
            }

            binding.root.setOnLongClickListener {
                selectStudentIDListener?.invoke(currentList[absoluteAdapterPosition])

                return@setOnLongClickListener true
            }
        }

        fun bind() {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        return ClientViewHolder(ItemAddClientBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bind()
    }


    fun setSelectStudentIDListener(block : (UserData) -> Unit) {
        this.selectStudentIDListener = block
    }
    fun setSelectedItemListener(block : (UserData) -> Unit) {
        this.selectedItemListener = block
    }
}