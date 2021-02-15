package com.example.Model


import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("ListUsers")
    val listUsers: List<UsersX>
)