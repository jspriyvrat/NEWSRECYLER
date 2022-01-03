package com.example.myrecyclerdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class NewsAdapter (val context: Context,val articles: List<Articles>): Adapter<NewsAdapter.Inner>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Inner {
        val inflater=LayoutInflater.from(parent.context)
        val  view= inflater.inflate(R.layout.rowcard,parent,false)
        return Inner(view)
    }

    override fun onBindViewHolder(holder: Inner, position: Int) {
        val article=articles[position]
        holder.title.text=article.title
        holder.descr.text=article.description
        Glide.with(context).load(article.urlToImage).into(holder.image)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class Inner(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var title:TextView=itemView.findViewById(R.id.title)
        var descr:TextView=itemView.findViewById(R.id.description)
        var image:ImageView=itemView.findViewById(R.id.newsImg)
    }
}