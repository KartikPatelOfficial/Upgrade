package com.deucate.upgradtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deucate.upgradtest.data.StackOverflowAPI
import com.deucate.upgradtest.model.Tag
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import com.deucate.upgradtest.data.ListWrapper
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var stackOverflowAPI: StackOverflowAPI
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createStackOverflowAPI()

        stackOverflowAPI.getTags().enqueue(tagsCallback)
    }

    private fun createStackOverflowAPI() {
        val gson = GsonBuilder().create()
        val retrofit =
            Retrofit.Builder().baseUrl(StackOverflowAPI.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        stackOverflowAPI = retrofit.create(StackOverflowAPI::class.java)
    }

    private var tagsCallback: Callback<ListWrapper<Tag>> = object : Callback<ListWrapper<Tag>> {
        override fun onResponse(call: Call<ListWrapper<Tag>>, response: Response<ListWrapper<Tag>>) {
            if (response.isSuccessful) {
                val data = ArrayList<Tag>()
                response.body()!!.items!!.forEach {
                    data.add(it)
                }
                Log.d("QuestionsCallback",data.toString())
            } else {
                Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message())
            }
        }

        override fun onFailure(call: Call<ListWrapper<Tag>>, t: Throwable) {
            t.printStackTrace()
        }
    }
}
