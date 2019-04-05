package com.deucate.upgradtest.data

import com.deucate.upgradtest.model.Tag
import retrofit2.http.GET
import retrofit2.Call

interface StackOverflowAPI {

    companion object {
        const val BASE_URL = "https://api.stackexchange.com"
    }

    @GET("/2.2/tags?order=desc&sort=popular&site=stackoverflow")
    fun getTags(): Call<ListWrapper<Tag>>
}