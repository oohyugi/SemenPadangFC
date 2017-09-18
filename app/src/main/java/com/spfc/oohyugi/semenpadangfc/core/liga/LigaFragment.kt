package com.spfc.oohyugi.semenpadangfc.core.news

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spfc.oohyugi.semenpadangfc.R
import com.spfc.oohyugi.semenpadangfc.base.BaseFragment
import com.spfc.oohyugi.semenpadangfc.commons.inflate
import com.spfc.oohyugi.semenpadangfc.core.liga.LigaPagerAdapter
import com.spfc.oohyugi.semenpadangfc.core.liga.LigaPresenter
import com.spfc.oohyugi.semenpadangfc.core.liga.LigaView
import com.spfc.oohyugi.semenpadangfc.core.liga.WebViewFragment
import com.spfc.oohyugi.semenpadangfc.di.component.ActivityComponent
import kotlinx.android.synthetic.main.liga_fragment.*
import javax.inject.Inject

/**
 * Created by oohyugi on 8/16/17.
 */
class LigaFragment : BaseFragment(), LigaView{


    @Inject
    lateinit var presenter: LigaPresenter

    lateinit var pageAdapter : LigaPagerAdapter

    fun newsInstance(): LigaFragment {
        val bundle = Bundle()
        val fragment =LigaFragment()
        fragment.setArguments(bundle)
        return fragment
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.liga_fragment)
    }




    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setTitle("League")


       // news.add(null)
//        presenter.attachView(this)
//        val viewPager = view?.findViewById<View>(R.id.viewPager) as ViewPager?
//        val tabs = view?.findViewById<View>(R.id.tabs) as TabLayout?
        tabs.setupWithViewPager(viewPager)
        pageAdapter = LigaPagerAdapter(childFragmentManager)
        pageAdapter.addFragment(WebViewFragment().newsInstance(0 ),"Fixtures-results")
        pageAdapter.addFragment(WebViewFragment().newsInstance(1 ),"League Table")
        viewPager.adapter = pageAdapter


    }



    override fun injectModule(activityComponent: ActivityComponent) {
//        activityComponent.inject(this)

    }




}