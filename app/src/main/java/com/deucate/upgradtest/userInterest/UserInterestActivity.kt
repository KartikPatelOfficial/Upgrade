package com.deucate.upgradtest.userInterest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.deucate.upgradtest.R
import com.deucate.upgradtest.data.ListWrapper
import com.deucate.upgradtest.data.StackOverflowAPI
import com.deucate.upgradtest.model.Tag
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_user_interest.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserInterestActivity : AppCompatActivity() {

    private lateinit var stackOverflowAPI: StackOverflowAPI
    //    private lateinit var token: String
    private val data = ArrayList<Tag>()

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_interest)

        createStackOverflowAPI()
        stackOverflowAPI.getTags().enqueue(tagsCallback)

        adapter = Adapter(data) {
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        }

        val recyclerView = interestRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun createStackOverflowAPI() {
        val gson = GsonBuilder().create()
        val retrofit =
            Retrofit.Builder().baseUrl(StackOverflowAPI.BASE_URL).addConverterFactory(
                GsonConverterFactory.create(gson)
            )
                .build()
        stackOverflowAPI = retrofit.create(StackOverflowAPI::class.java)
    }

    private var tagsCallback: Callback<ListWrapper<Tag>> = object : Callback<ListWrapper<Tag>> {
        override fun onResponse(
            call: Call<ListWrapper<Tag>>,
            response: Response<ListWrapper<Tag>>
        ) {
            if (response.isSuccessful) {
                response.body()!!.items!!.forEach {
                    data.add(it)
                }
                adapter.notifyDataSetChanged()
                Log.d("QuestionsCallback", data.toString())
            } else {
                Log.d(
                    "QuestionsCallback",
                    "Code: " + response.code() + " Message: " + response.message()
                )
            }
        }

        override fun onFailure(call: Call<ListWrapper<Tag>>, t: Throwable) {
            t.printStackTrace()
        }
    }
}
