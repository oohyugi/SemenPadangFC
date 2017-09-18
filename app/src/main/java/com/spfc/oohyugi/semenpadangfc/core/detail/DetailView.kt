package com.spfc.oohyugi.semenpadangfc.core.detail

import com.spfc.oohyugi.semenpadangfc.base.BaseView
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel

/**
 * Created by oohyugi on 8/16/17.
 */
interface DetailView: BaseView{
    fun displayLoading()
    fun stopLoading()
    fun  displayDetailNews(result: NewsModel)
    fun  displayError(message: String?)

}