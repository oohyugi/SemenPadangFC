package com.spfc.oohyugi.semenpadangfc.core

import com.spfc.oohyugi.semenpadangfc.base.BasePresenter
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel
import com.spfc.oohyugi.semenpadangfc.data.repo.news.NewsRepoRequest
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

/**
 * Created by oohyugi on 8/11/17.
 */
class MainPresenter @Inject constructor(val repo: NewsRepoRequest) : BasePresenter<MainView>() {

    private var compositeSub = CompositeDisposable()
    var url: String = ""
    lateinit var rows: Elements
    lateinit var htmlDocument: Document

    lateinit var news: NewsModel
    lateinit var hasmap: HashMap<String, String>
    lateinit var listHas: MutableList<NewsModel>
    var htmlContentInStringFormat: String? = null

    override fun attachView(mView: MainView) {
        super.attachView(mView)
    }

    override fun detachView() {
        super.detachView()
    }



    fun getNews(pageNum: Int) {
        mView?.displayLoading()


//        compositeSub.add(repo.getNews(pageNum)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    result ->
//                    mView?.stopLoading()
//                    mView?.displayNews(result)
//                }, {
//                    error ->
//                    mView?.stopLoading()
//                    mView?.displayError(error.message)
//
//                }))
        doAsync {

            val url = "http://semenpadangfc.co.id/category/news-update/page/$pageNum"
            val urlNext = "http://semenpadangfc.co.id"
            val htmlDocument = Jsoup.connect(url).get()
            val htmlNext = Jsoup.connect(urlNext).get()
            val htmlContentInStringFormat = htmlDocument.title()

            val rows = htmlDocument.select("div[class=gdlr-ux gdlr-blog-grid-ux]")
            val netmatch = htmlNext.select("section#content-section-2").html()
            val css = htmlNext.select("head").html()
            var news: NewsModel
            val hasmap = HashMap<String, String>()

            uiThread {
                val listHas: MutableList<NewsModel> = mutableListOf()
                println(rows)
                if (rows != null) {
                    for (row: Element in rows) {
                        val titleContent = row.select("h3.gdlr-blog-title").text()
                        val src = row.select("div[class=gdlr-blog-thumbnail]").first()
                        val imgSrc = src.select("img")
                        val absHref = imgSrc.attr("abs:src")
                        val eleInfo = row.select("div[class=gdlr-blog-info gdlr-info]").first()
                        val eleDate = eleInfo.select("div[class=blog-info blog-date]").first()
                        val eleAuthor = eleInfo.select("div[class=blog-info blog-author]").first()
                        val eleContent = row.select("div[class=gdlr-blog-content]").first()
                        val date = eleDate.select("a[href]")
                        val author = eleAuthor.select("a[href]")
                        val url = row.select("div.gdlr-blog-content > a").attr("href")


                        news = NewsModel(titleContent, date.text(), eleContent.text(), author.text(), absHref, url)
                        listHas.add(news)

                    }
                    mView?.stopLoading()
                    mView?.displayNews(listHas,netmatch,css)


                }
            }

        }


    }


}