package com.spfc.oohyugi.semenpadangfc.core.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.spfc.oohyugi.semenpadangfc.R
import com.spfc.oohyugi.semenpadangfc.commons.inflate
import com.spfc.oohyugi.semenpadangfc.commons.loadImg
import com.spfc.oohyugi.semenpadangfc.data.model.PlayersModel
import kotlinx.android.synthetic.main.players_item.view.*

/**
 * Created by oohyugi on 8/16/17.
 */
class PlayerAdapter(val context: Context, val news: List<PlayersModel>) : RecyclerView.Adapter<PlayerAdapter.PlayerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlayerHolder {

        return PlayerHolder(parent?.inflate(R.layout.players_item) as View)
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.bindItem(context,news[position])

    }

    override fun getItemCount(): Int = news.size
//
    class PlayerHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.tvName
        val imgPoster: ImageView = view.imgThumb
        val nomor : TextView = view.tvNomor
        val posisi : TextView = view.tvPosis

        fun bindItem(context: Context,player: PlayersModel) {
            name.text = player.name
            nomor.text = player.nomor
            posisi.text = player.posisi
            imgPoster.loadImg(player.img)
//            view.setOnClickListener({
//
//              DetailActivity().startThisActivity(context,news)
//            })




        }
    }



}