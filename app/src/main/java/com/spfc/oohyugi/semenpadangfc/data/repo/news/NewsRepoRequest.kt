package com.spfc.oohyugi.semenpadangfc.data.repo.news

import com.spfc.oohyugi.semenpadangfc.data.model.BaseModel
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel
import io.reactivex.Observable

/**
 * Created by oohyugi on 8/16/17.
 */
interface NewsRepoRequest{
   fun getNews(pageNum : Int) : Observable<BaseModel<List<NewsModel>>>
}