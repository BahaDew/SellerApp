package com.example.sellerapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ClientInfoData
import com.example.sellerapp.databinding.ItemTimePieceBinding

class ClientInfoAdapter(private val data: ArrayList<ClientInfoData>) :
    Adapter<ClientInfoAdapter.ClientInfoViewHolder>() {
    private var longClickListener: ((position: Int) -> Unit)? = null

    inner class ClientInfoViewHolder(private val binding: ItemTimePieceBinding) :
        ViewHolder(binding.root) {

            init {
                binding.item.setOnLongClickListener {
                    longClickListener?.invoke(absoluteAdapterPosition)
                    return@setOnLongClickListener true
                }
            }
        fun bind(position: Int) {
            binding.summa.text = data[position].summa.toString()
            binding.muddat.text = data[position].muddat
            if(data[position].tulangan == 1) {
                binding.tolandi.setText(R.string.checked_tulov)
                binding.item.setBackgroundResource(R.color.checked_tulov)
            } else if(data[position].tulangan == 0) {
                binding.tolandi.setText(R.string.unchecked_tulov)
                binding.item.setBackgroundResource(R.color.unchecked_tulov)
            } else {
                binding.tolandi.setText(R.string.not_tulov)
                binding.item.setBackgroundResource(R.color.not_tulov)
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

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ClientInfoViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setLongClickListener(longClickListener: ((position: Int) -> Unit)) {
        this.longClickListener = longClickListener
    }
}