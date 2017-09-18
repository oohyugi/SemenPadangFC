package com.spfc.oohyugi.semenpadangfc.core.detail

import com.spfc.oohyugi.semenpadangfc.base.BasePresenter
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel
import com.spfc.oohyugi.semenpadangfc.data.repo.news.DetailRepoRequest
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import javax.inject.Inject

/**
 * Created by oohyugi on 8/16/17.
 */
class DetailPresenter @Inject constructor(val repo: DetailRepoRequest): BasePresenter<DetailView>() {

    private var compositeSub = CompositeDisposable()
    override fun attachView(mView: DetailView) {
        super.attachView(mView)
    }

    override fun detachView() {
        super.detachView()
    }

    fun getDetailNews(url: String) {
            val  newUrl = url.replace("http://semenpadangfc.co.id/","")

        mView?.displayLoading()
//        compositeSub.add(repo.getDetail(newUrl)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    result ->
//                    mView?.stopLoading()
//                    mView?.displayDetailNews(result)
//                }, {
//                    error ->
//                    mView?.stopLoading()
//                    mView?.displayError(error.message)
//
//                }))
        doAsync {
            val htmlDocument = Jsoup.connect(url).get()
            val htmlContentInStringFormat = htmlDocument.title()

            val rows = htmlDocument.select("div[class=gdlr-standard-style]")
            var news: NewsModel
            val hasmap = HashMap<String, String>()

            val listHas: MutableList<NewsModel> = mutableListOf()

            uiThread {

                if (rows != null) {


                    val titleContent = rows.select("h1.gdlr-blog-title").text()
//                    val src = rows.select("div[class=gdlr-blog-thumbnail]").first()
//                    val imgSrc = src.select("img")
//                    val absHref = imgSrc.attr("abs:src")
//                    val eleInfo = row.select("div[class=gdlr-blog-info gdlr-info]").first()
//                    val eleDate = eleInfo.select("div[class=blog-info blog-date]").first()
//                    val eleAuthor = eleInfo.select("div[class=blog-info blog-author]").first()
                    val eleContent = rows.select("div[class=gdlr-blog-content]").first()
//                    val date = eleDate.select("a[href]")
//                    val author = eleAuthor.select("a[href]")
//                    val url = row.select("div.gdlr-blog-content > a").attr("href")


                    news = NewsModel(titleContent, "", eleContent.text(), "","", url)
                    mView?.stopLoading()
                    mView?.displayDetailNews(news)

                } else {
                    mView?.stopLoading()
                }
            }
        }

    }

}