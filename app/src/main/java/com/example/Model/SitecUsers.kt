package com.example.Model


import com.google.gson.annotations.SerializedName

data class SitecUsers(
    @SerializedName("Users")
    val users: Users
)