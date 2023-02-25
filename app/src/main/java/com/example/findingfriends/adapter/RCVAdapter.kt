package com.example.findingfriends.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findingfriends.data.ContactModel

import com.example.findingfriends.databinding.ItemContactBinding

class RCVAdapter (
    val contactList: ArrayList<ContactModel>
) : RecyclerView.Adapter<RCVAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val firstName = binding.tvFirstName
        val lastName = binding.tvLastName
        val phoneNumber = binding.tvPhoneNumber
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
        holder.binding.tvFirstName.text = item.firstName
        holder.binding.tvLastName.text = item.lastName
      //  holder.binding.tvPhoneNumber.text = item.phoneNumber
    }

    override fun getItemCount(): Int = contactList.size
}