package com.example.Model


import com.google.gson.annotations.SerializedName

data class UsersX(
    @SerializedName("User")
    val user: String,
    @SerializedName("Uid")
    val uid: String

)