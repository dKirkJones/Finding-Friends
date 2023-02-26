package com.example.findingfriends.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findingfriends.data.ContactModel

import com.example.findingfriends.databinding.ItemContactBinding

class RCVAdapter (
    val contactList: ArrayList<ContactModel>,
    val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RCVAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val firstName = binding.tvDisplayName
        val phoneNumber = binding.tvPhoneNumber
        val contactAddress = binding.tvAddress
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = contactList[position]
        holder.binding.tvDisplayName.text = item.displayName
        holder.binding.tvPhoneNumber.text = item.phoneNumber
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: ContactModel)
    }
}