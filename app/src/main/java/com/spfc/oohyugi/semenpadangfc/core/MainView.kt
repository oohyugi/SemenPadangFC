package com.spfc.oohyugi.semenpadangfc.core

import com.spfc.oohyugi.semenpadangfc.base.BaseView
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel

/**
 * Created by oohyugi on 8/11/17.
 */
interface MainView: BaseView {
    fun  displayNews(result: MutableList<NewsModel>, netmatch: String, css: String)
    fun  displayError(message: String?)
    fun displayLoading()
    fun stopLoading()
}