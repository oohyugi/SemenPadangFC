package com.spfc.oohyugi.semenpadangfc.data.repo.news

import com.spfc.oohyugi.semenpadangfc.data.model.BaseModel
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel
import io.reactivex.Observable

/**
 * Created by oohyugi on 8/16/17.
 */
interface DetailRepoRequest{
    fun getDetail(url:String): Observable<BaseModel<NewsModel>>
}