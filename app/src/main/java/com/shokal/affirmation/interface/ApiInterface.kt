package com.shokal.affirmation.`interface`

import com.shokal.affirmation.model.JsonPlaceholderItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("photos")
    fun getData() : Call<List<JsonPlaceholderItem>>
}