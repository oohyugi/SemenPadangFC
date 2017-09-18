package com.spfc.oohyugi.semenpadangfc.core

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.Menu
import android.view.MenuItem
import com.spfc.oohyugi.semenpadangfc.R
import com.spfc.oohyugi.semenpadangfc.base.BaseActivity
import com.spfc.oohyugi.semenpadangfc.commons.BottomNavigationHelper
import com.spfc.oohyugi.semenpadangfc.commons.FragmentHelper
import com.spfc.oohyugi.semenpadangfc.commons.InfiniteScrollListener
import com.spfc.oohyugi.semenpadangfc.core.adapter.MainAdapter
import com.spfc.oohyugi.semenpadangfc.core.news.LigaFragment
import com.spfc.oohyugi.semenpadangfc.core.news.NewsFragment
import com.spfc.oohyugi.semenpadangfc.core.players.PlayersFragment
import com.spfc.oohyugi.semenpadangfc.data.model.BaseModel
import com.spfc.oohyugi.semenpadangfc.data.model.NewsModel
import com.spfc.oohyugi.semenpadangfc.di.component.ActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {



    private var mSelectedItem = 0
    private val SELECTED_ITEM = "selected_item"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BottomNavigationHelper.disableShiftMode(bottom_navigation)
        initBottomNavigasi(savedInstanceState)
//        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)

        val fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SELECTED_ITEM, mSelectedItem)
        Log.e("onSaveInstanceState: ", mSelectedItem.toString())
        super.onSaveInstanceState(outState)
    }

    private fun initBottomNavigasi(savedInstanceState: Bundle?) {
        bottom_navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            selectedFragment(item)

            true
        })

        val selectedItem: MenuItem

        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0)
            selectedItem = bottom_navigation.getMenu().findItem(mSelectedItem)


        } else {
            selectedItem = bottom_navigation.getMenu().getItem(0)
        }
        selectedFragment(selectedItem)
    }

    private fun  selectedFragment(item: MenuItem) {
        var fragment : Fragment? = null
        when (item.itemId){
            R.id.action_home->
                fragment = NewsFragment().newsInstance()
            R.id.action_players ->
                    fragment = PlayersFragment().newsInstance()
            R.id.action_liga ->
                    fragment = LigaFragment().newsInstance()


        }
        mSelectedItem = item.itemId
        FragmentHelper.replaceFragment(this,fragment,R.id.container)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId



        return super.onOptionsItemSelected(item)
    }




    override fun onDestroy() {
        super.onDestroy()

    }

}
