package com.example.findingfriends.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.findingfriends.adapter.RCVAdapter
import com.example.findingfriends.data.ContactModel
import com.example.findingfriends.databinding.ActivityContactListBinding
import com.example.findingfriends.utils.PermissionTracking
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

class ContactListActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks, RCVAdapter.OnItemClickListener {
   private lateinit var binding: ActivityContactListBinding
   var arrayList: ArrayList<ContactModel> = arrayListOf()
   var rcvAdapter: RCVAdapter = RCVAdapter(arrayList, this)
   // private lateinit var rcvAdapter: RCVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (checkContactPermissions()){
            binding.apply {
                rcvContact.apply {
                    layoutManager = LinearLayoutManager(this@ContactListActivity)

                 adapter = RCVAdapter(arrayList, this@ContactListActivity)
                }
            }
            getContact()
        }
    }


    @SuppressLint("Range")
    private fun getContact() {
        arrayList.clear()
        val cursor = this.contentResolver
            .query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,

                    ),null,null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )
        while (cursor!!.moveToNext()){
            val contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val contactModel =  ContactModel(contactName,contactNumber)
            arrayList.add(contactModel)
        }
        rcvAdapter.notifyDataSetChanged()
        cursor.close()
    }

    private fun checkContactPermissions():Boolean{
        if (PermissionTracking.hasCOntactPermissions(this)){
            return true
        }else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O){
            EasyPermissions.requestPermissions(
                this,
                "You will need to accept the permission in order to run the application",
                100,
                android.Manifest.permission.READ_CONTACTS,
                android.Manifest.permission.WRITE_CONTACTS,
            )
            return true
        }else{
            return false
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(position: ContactModel) {
        val intent = Intent(this@ContactListActivity, ContactDetailsActivity::class.java)
        startActivity(intent)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        TODO("Not yet implemented")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }else{
            checkContactPermissions()
        }
    }

}