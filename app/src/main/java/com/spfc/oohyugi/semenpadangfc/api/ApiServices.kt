package com.spfc.oohyugi.semenpadangfc.api

import com.spfc.oohyugi.semenpadangfc.data.model.BaseModel
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel
import com.spfc.oohyugi.semenpadangfc.data.model.PlayersModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by oohyugi on 8/11/17.
 */
interface ApiServices {



    @GET("news/page/{pagenum}")
    fun getNews( @Path("pagenum")pageNum: Int) : Observable<BaseModel<List<NewsModel>>>

    @GET("news/detail/{url}")
    fun getDetailNews( @Path("url")url: String) : Observable<BaseModel<NewsModel>>

    @GET("players")
    fun getPlayers() : Observable<BaseModel<List<PlayersModel>>>
}