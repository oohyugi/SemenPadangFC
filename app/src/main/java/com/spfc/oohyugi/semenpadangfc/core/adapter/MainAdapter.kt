package com.spfc.oohyugi.semenpadangfc.core.adapter

import android.content.Context
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.spfc.oohyugi.semenpadangfc.R
import com.spfc.oohyugi.semenpadangfc.commons.inflate
import com.spfc.oohyugi.semenpadangfc.commons.loadImg
import com.spfc.oohyugi.semenpadangfc.core.detail.DetailActivity
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by oohyugi on 8/16/17.
 */
class MainAdapter(val context: Context, val news: List<NewsModel>) : RecyclerView.Adapter<MainAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainHolder {

        return MainHolder(parent?.inflate(R.layout.news_item) as View)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bindItem(context,news[position])

    }

    override fun getItemCount(): Int = news.size

    class MainHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val title: TextView = view.tvTitle
        val imgPoster: ImageView = view.imgThumb
        val desc : TextView = view.tvAuthor

        fun bindItem(context: Context,news: NewsModel) {
            title.text = news.title
            desc.text = news.author+" "+context.resources.getString(R.string.circle_bullet)+" "+news.date
            imgPoster.loadImg(news.img)
            view.setOnClickListener({

              DetailActivity().startThisActivity(context,news)
            })




        }
    }

}