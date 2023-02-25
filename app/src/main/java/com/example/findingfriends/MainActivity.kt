package com.example.findingfriends

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.findingfriends.data.Contact
import com.example.findingfriends.data.ContactResponse
import com.example.findingfriends.databinding.ActivityMainBinding
import com.example.findingfriends.ui.ContactListActivity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private  val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    val contactList: List<Contact> by lazy {
       parseContacts()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.getContactsButton.setOnClickListener {
            startActivity(Intent(this, ContactListActivity::class.java))  }

    }

    private fun parseContacts(): List<Contact> {
        val textFromFile = resources.openRawResource(R.raw.contact).bufferedReader().use { it.readText() }

       // val type = Types.newParameterizedType(List::class.java, Contact::class.java)
        val adapter: JsonAdapter<ContactResponse> = moshi.adapter(ContactResponse::class.java)
        return adapter.fromJson(textFromFile)!!.contacts
    }
}