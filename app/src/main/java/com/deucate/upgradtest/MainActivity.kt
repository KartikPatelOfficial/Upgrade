package com.deucate.upgradtest

import android.content.Intent
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
import com.deucate.upgradtest.userInterest.UserInterestActivity
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent( this,UserInterestActivity::class.java))
    }
}
