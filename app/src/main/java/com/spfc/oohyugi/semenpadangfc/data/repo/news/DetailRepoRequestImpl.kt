package com.spfc.oohyugi.semenpadangfc.data.repo.news

import com.spfc.oohyugi.semenpadangfc.api.ApiServices
import com.spfc.oohyugi.semenpadangfc.data.model.BaseModel
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel
import com.spfc.oohyugi.semenpadangfc.data.repo.news.DetailRepoRequest
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by oohyugi on 8/16/17.
 */
class DetailRepoRequestImpl @Inject constructor(val service:ApiServices) : DetailRepoRequest {
    override fun getDetail(url: String): Observable<BaseModel<NewsModel>> {

        return service.getDetailNews(url).map {
            BaseModel(code = it.code,
                    message = it.message,
                    data = it.data)
        }
    }

}