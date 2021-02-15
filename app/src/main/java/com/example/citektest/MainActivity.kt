package com.example.citektest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.Model.SitecUsers
import com.example.Model.UserCodePass
import com.example.Model.UsersX
import com.example.retrofit.APIService
import com.example.retrofit.APIServiceUserKod
import com.example.retrofit.ApiUtils
import com.example.retrofit.ApiUtilsUser
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(),
    AdapterView.OnItemSelectedListener {
    private val dataList: MutableList<UsersX> = mutableListOf()
    private val dataListUser: MutableList<Int> = mutableListOf()
    private val newArrayName: MutableList<String> = mutableListOf()
    private var myAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mAPIService: APIService? = null
        var myAPIServiceUser: APIServiceUserKod? = null

        mAPIService = ApiUtils.apiService
        myAPIServiceUser = ApiUtilsUser.apiServiceUser

        val mySpinner = findViewById<Spinner>(R.id.spinnerEmployee)
        val myButton = findViewById<Button>(R.id.btn_login)


        val call = mAPIService.fetchQuestions()
        val callUserKod = myAPIServiceUser.fetchQuestions()
        call.enqueue(object : Callback<SitecUsers> {

            override fun onResponse(call: Call<SitecUsers>, response: Response<SitecUsers>) {
                val employee = response.body()


                if (employee != null) {
                    dataList.addAll(employee.users.listUsers)
                }

                for (i in 0 until dataList.size) {
                    val first = dataList[i]

                    newArrayName.add(first.user)

                }
                myAdapter = ArrayAdapter(
                    applicationContext,
                    android.R.layout.simple_spinner_dropdown_item,
                    newArrayName
                )
                mySpinner.adapter = myAdapter

            }

            override fun onFailure(call: Call<SitecUsers>, t: Throwable) {
                // Write code perform actions when request fails...
                //     Log.d("Tag", t.toString())
            }
        })
        callUserKod.enqueue(object : Callback<UserCodePass> {
            override fun onResponse(call: Call<UserCodePass>, response: Response<UserCodePass>) {
                val kod = response.body()
                if (kod != null) {
                    dataListUser.add(kod.code)



                }

                myButton.setOnClickListener {
                    val kodIsEt: String = "[${etPassword.text.toString()}]"
                    val password: String = dataListUser.toString()



                    if (kodIsEt == password) {
                        Toast.makeText(
                            baseContext,
                            "Авторизация прошла успешно",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {

                        Toast.makeText(
                            baseContext,
                            "Неверный пароль, проверьте вводимые данные",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<UserCodePass>, t: Throwable) {

            }
        })



        mySpinner.onItemSelectedListener = this
    }


    @SuppressLint("SetTextI18n")
    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

        val items: String = parent?.getItemAtPosition(position) as String
        val tvName = findViewById<TextView>(R.id.tv_spinner_result)

        tvName.text = "Имя пользователя: $items"
        Toast.makeText(this, "${p3 + 1}", Toast.LENGTH_SHORT).show()
    }
    override fun onNothingSelected(p0: AdapterView<*>?) {
    }


}


