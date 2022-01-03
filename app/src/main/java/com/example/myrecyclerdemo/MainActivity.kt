package com.example.myrecyclerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.withStyledAttributes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerdemo.NewsInterface.NewsService.newsInstance

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
lateinit var adapter: NewsAdapter
lateinit var recyler:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyler=findViewById(R.id.myRecycler)

        getNews()

    }

    private fun getNews() {
        val  news =newsInstance.getHeadLines("ae",1)
        news.enqueue(object:Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
            val news=response.body()

                if (news != null) {
                     adapter = NewsAdapter(this@MainActivity, news.articles)
                    recyler.adapter=adapter
                    recyler.layoutManager=LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(application,"Error in fetching the news ",Toast.LENGTH_LONG).show()
                
            }
        })
    }
}