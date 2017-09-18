package com.spfc.oohyugi.semenpadangfc.base

import io.reactivex.disposables.CompositeDisposable

/**
 * Created by oohyugi on 8/11/17.
 */
open class BasePresenter<T : BaseView> : Presenter<T> {

    var mView: T? = null
    lateinit var compositeDisposable: CompositeDisposable

    override fun attachView(mView: T) {

        this.mView = mView
        compositeDisposable = CompositeDisposable()
    }

    override fun detachView() {
        mView = null
        compositeDisposable.dispose()
    }

    val isViewAttached: Boolean
        get() = mView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before"
            + " requesting data to the Presenter")
}