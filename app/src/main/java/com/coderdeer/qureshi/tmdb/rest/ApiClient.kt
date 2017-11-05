package com.coderdeer.qureshi.tmdb.rest

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


object ApiClient {

    val BASE_URL = "http://api.themoviedb.org/3/"
    private var retrofit: Retrofit? = null


    fun client(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit!!
    }
}