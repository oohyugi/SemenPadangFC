package com.spfc.oohyugi.semenpadangfc.core.detail

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View

import com.spfc.oohyugi.semenpadangfc.R
import com.spfc.oohyugi.semenpadangfc.base.BaseActivity
import com.spfc.oohyugi.semenpadangfc.commons.loadImgDetail
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel
import com.spfc.oohyugi.semenpadangfc.di.component.ActivityComponent
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(),DetailView {


    override fun injectModule(activityComponent: ActivityComponent) {
        activityComponent.inject(this)

    }

    val  TAG_DATA = "data"
    @Inject
    lateinit var presenter : DetailPresenter
    lateinit var progress: ProgressDialog
    lateinit var mdl:NewsModel

    fun startThisActivity(mContex: Context, item: NewsModel) {
        val intent = Intent(mContex, DetailActivity::class.java)
        intent.putExtra(TAG_DATA,item)
        mContex.startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter.attachView(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        mdl = intent.getParcelableExtra(TAG_DATA)
        img_detail.loadImgDetail(mdl.img)
        presenter.getDetailNews(mdl.url)
    }

    override fun displayLoading() {
       progressbar.visibility=View.VISIBLE


    }

    override fun stopLoading() {
        progressbar.visibility=View.GONE
    }

    override fun displayDetailNews(result: NewsModel) {

        setupContent(result)
    }

    private fun  setupContent(data: NewsModel) {
        tvTitle.text = data.title
        tvDesc.text = Html.fromHtml(data.description)
        tvAuthor.text = mdl.author+" "+resources.getString(R.string.circle_bullet)+" "+mdl.date

    }

    override fun displayError(message: String?) {
        progressbar.visibility=View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        if(id == android.R.id.home){
            onBackPressed()
        }



        return super.onOptionsItemSelected(item)
    }
}
