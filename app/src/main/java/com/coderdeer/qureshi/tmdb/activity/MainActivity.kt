package com.coderdeer.qureshi.tmdb.activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.coderdeer.qureshi.tmdb.R
import com.coderdeer.qureshi.tmdb.model.MoviesResponse
import com.coderdeer.qureshi.tmdb.rest.ApiClient
import android.widget.Toast
import com.coderdeer.qureshi.tmdb.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.coderdeer.qureshi.tmdb.adapter.MoviesAdapter







class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private val API_KEY = "960236efe198673b52621287423d1042"

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (API_KEY.isEmpty()) {
            Toast.makeText(applicationContext, "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show()
            return
        }

        recyclerView = findViewById<RecyclerView>(R.id.movies_recycler_view) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this)

        val apiService = ApiClient.client().create(ApiInterface::class.java)


        val call = apiService.getTopRatedMovies(API_KEY)
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                val movies = response.body()!!.results
                recyclerView.adapter = MoviesAdapter(movies, R.layout.list_item_movie, applicationContext)
                Log.d(TAG, "Number of movies received: " + movies.size)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                // Log error here since request failed
                Log.e(TAG, t.toString())
            }
        })
    }
}

