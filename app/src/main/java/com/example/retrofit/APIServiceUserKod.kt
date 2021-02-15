package com.example.retrofit

import com.example.Model.SitecUsers
import com.example.Model.UserCodePass
import retrofit2.Call
import retrofit2.http.*


interface APIServiceUserKod {


    @GET("UKA_TRADE/hs/MobileClient/111111111111111/authentication/")    //End Url


    fun fetchQuestions(): Call<UserCodePass>
}


object ApiUtilsUser {

    private const val BASE_URL = "https://dev.sitec24.ru/"

    val apiServiceUser: APIServiceUserKod
        get() = RestClient.getClient(BASE_URL, "http", "http").create(APIServiceUserKod::class.java)

}