package com.spfc.oohyugi.semenpadangfc.base

/**
 * Created by oohyugi on 8/11/17.
 */
interface Presenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}