package com.example.myrecyclerdemo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerdemo.NewsInterface.NewsService.newsInstance
import com.littlemango.stacklayoutmanager.StackLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {
lateinit var adapter: NewsAdapter
lateinit var recyler:RecyclerView
var articles= mutableListOf<Articles>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyler=findViewById(R.id.myRecycler)
        adapter = NewsAdapter(this@MainActivity, articles)
        recyler.adapter=adapter
//        recyler.layoutManager=LinearLayoutManager(this@MainActivity)
        val stackLayoutManager=StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        stackLayoutManager.setPagerMode(true)
        stackLayoutManager.setPagerFlingVelocity(3000)
        stackLayoutManager.setItemChangedListener(object :StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
            }
        })
        recyler.layoutManager=stackLayoutManager

        getNews()
    }

    private fun getNews() {
        val  news =newsInstance.getHeadLines("in",1)
        news.enqueue(object:Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
            val news=response.body()

                if (news != null) {
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(application,"Error in fetching the news ",Toast.LENGTH_LONG).show()

            }
        })
    }
}