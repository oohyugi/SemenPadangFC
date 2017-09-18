package com.spfc.oohyugi.semenpadangfc.core.news

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spfc.oohyugi.semenpadangfc.R
import com.spfc.oohyugi.semenpadangfc.base.BaseFragment
import com.spfc.oohyugi.semenpadangfc.commons.InfiniteNestedScrollListener
import com.spfc.oohyugi.semenpadangfc.commons.InfiniteScrollListener
import com.spfc.oohyugi.semenpadangfc.commons.inflate
import com.spfc.oohyugi.semenpadangfc.core.MainPresenter
import com.spfc.oohyugi.semenpadangfc.core.MainView
import com.spfc.oohyugi.semenpadangfc.core.adapter.MainAdapter
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel
import com.spfc.oohyugi.semenpadangfc.di.component.ActivityComponent
import kotlinx.android.synthetic.main.news_fragment.*
import javax.inject.Inject

/**
 * Created by oohyugi on 8/16/17.
 */
class NewsFragment : BaseFragment(), MainView{


    @Inject
    lateinit var presenter: MainPresenter
    lateinit var adapter: MainAdapter
    private var news: MutableList<NewsModel> = mutableListOf()
    private var page: Int = 1


    fun newsInstance(): NewsFragment {
        val bundle = Bundle()
        val fragment = NewsFragment()
        fragment.setArguments(bundle)
        return fragment
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }




    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



       // news.add(null)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setTitle("Semen Padang FC Unofficial")
        initRV()
        presenter.attachView(this)
        presenter.getNews(page)
        swipeRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                news.clear()
                presenter.getNews(1)
            }
        })
    }

    private fun initRV() {
        adapter = MainAdapter(activity, news)
        val linearLayout = LinearLayoutManager(activity)
        rvNews.layoutManager = linearLayout
        rvNews.adapter = adapter


        nested.setOnScrollChangeListener(object : InfiniteNestedScrollListener(linearLayout) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                var page = page
                page++
              presenter.getNews(page)

            }
        })
//        rvNews.addOnScrollListener(
//                InfiniteScrollListener(
//                        {
//                            page++
//                            presenter.getNews(page)
//                        },
//                        linearLayout)
//        )
       // adapter.notifyDataSetChanged()
    }


    override fun injectModule(activityComponent: ActivityComponent) {
        activityComponent.inject(this)

    }
    override fun displayNews(result: MutableList<NewsModel>, netmatch: String, css: String) {
        val settings = webview.getSettings()
        settings.setDefaultTextEncodingName("utf-8")
        settings.javaScriptEnabled
        webview.settings.javaScriptEnabled
        webview.loadDataWithBaseURL(null,"<html>$css<body>$netmatch</body></html>", "text/html; charset=utf-8",null,null)
        news.addAll(result)
        println(netmatch)
        adapter.notifyDataSetChanged()
//        initRV()

    }

    override fun displayError(message: String?) {
        Snackbar.make(rvNews, message.toString(), Snackbar.LENGTH_LONG)
                .setAction("Retry", null).show()
//        println("data $message")

    }

    override fun displayLoading() {
        swipeRefresh.isRefreshing=true

    }

    override fun stopLoading() {
        swipeRefresh.isRefreshing=false

    }



}