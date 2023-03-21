package com.example.findingfriends.data

data class Contact (
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val phone : String = "",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val company: String = "",
    val note: String = "",
    val email: String = "",
    val zipcode: String="",
    val displayName:String,
    val phoneNumber:String,
    val photoURI: String
)