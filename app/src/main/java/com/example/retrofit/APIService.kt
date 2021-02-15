package com.example.retrofit

import com.example.Model.SitecUsers
import retrofit2.Call
import retrofit2.http.*


interface APIService {


    @GET("UKA_TRADE/hs/MobileClient/111111111111111/form/users?")    //End Url


    fun fetchQuestions(): Call<SitecUsers>
}


object ApiUtils {

    private const val BASE_URL = "https://dev.sitec24.ru/"

    val apiService: APIService
        get() = RestClient.getClient(BASE_URL, "http", "http").create(APIService::class.java)

}