package com.example.Model


import com.google.gson.annotations.SerializedName

data class UserCodePass(
    @SerializedName("Code")
    val code: Int
)