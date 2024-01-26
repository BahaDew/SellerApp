package com.example.sellerapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.sellerapp.R

class ClientInfoAdapter(var count: Int): Adapter<ClientInfoAdapter.ClientInfoViewHolder>() {
    inner class ClientInfoViewHolder(view :View): ViewHolder(view){
        fun bind() {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientInfoViewHolder {
        return ClientInfoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_time_piece, parent, false))
    }

    override fun getItemCount(): Int {
        return count
    }

    override fun onBindViewHolder(holder: ClientInfoViewHolder, position: Int) {
        holder.bind()
    }
}