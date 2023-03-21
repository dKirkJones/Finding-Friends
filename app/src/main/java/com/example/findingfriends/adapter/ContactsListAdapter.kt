package com.example.findingfriends.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.findingfriends.R
import com.example.findingfriends.data.Contact
import com.example.findingfriends.data.ContactModel

class ContactsListAdapter(
   // val contactList: List<Contact> = emptyList(),
    val itemClickListener: OnItemClickListener

) : RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder>() {

    private val contactList = ArrayList<ContactModel>()

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var firstName: TextView
        var phoneNumber: TextView
        var contactImage: ImageView

        init {
            firstName = itemView.findViewById(R.id.tvDisplayName)
            phoneNumber = itemView.findViewById(R.id.tvPhoneNumber)
            contactImage = itemView.findViewById(R.id.contactImage)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(contactList[position])
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun setData(contactList: List<ContactModel>){
        this.contactList.clear()
        this.contactList.addAll(contactList)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: ContactModel)
    }
}