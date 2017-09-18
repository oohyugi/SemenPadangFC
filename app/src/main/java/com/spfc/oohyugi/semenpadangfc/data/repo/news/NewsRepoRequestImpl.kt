package com.spfc.oohyugi.semenpadangfc.data.repo.news

import com.spfc.oohyugi.semenpadangfc.api.ApiServices
import com.spfc.oohyugi.semenpadangfc.data.model.BaseModel
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel
import com.spfc.oohyugi.semenpadangfc.data.repo.news.NewsRepoRequest
import io.reactivex.Observable
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject

/**
 * Created by oohyugi on 8/16/17.
 */
class NewsRepoRequestImpl @Inject constructor(val service:ApiServices): NewsRepoRequest {

    override fun getNews(pageNum: Int): Observable<BaseModel<List<NewsModel>>> {


      return service.getNews(pageNum).map {
          BaseModel(code = it.code,
                  message = it.message,
                  data = it.data)

      }



    }

    private fun  loadData(pageNum: Int) {
//                    response.baseResponse(200, "success","")
        val url = "http://semenpadangfc.co.id/category/news-update/page/$pageNum"
        val htmlDocument = Jsoup.connect(url).get()
        val htmlContentInStringFormat = htmlDocument.title()

        val rows = htmlDocument.select("div[class=gdlr-ux gdlr-blog-grid-ux]")
        var news: NewsModel
        val hasmap = HashMap<String, String>()

        val listHas: MutableList<NewsModel> = mutableListOf()
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


        }
    }
}