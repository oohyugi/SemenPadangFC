package com.spfc.oohyugi.semenpadangfc.core.players

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spfc.oohyugi.semenpadangfc.R
import com.spfc.oohyugi.semenpadangfc.base.BaseFragment
import com.spfc.oohyugi.semenpadangfc.commons.inflate
import com.spfc.oohyugi.semenpadangfc.core.adapter.PlayerAdapter
import com.spfc.oohyugi.semenpadangfc.data.model.PlayersModel
import com.spfc.oohyugi.semenpadangfc.di.component.ActivityComponent
import kotlinx.android.synthetic.main.players_fragment.*
import javax.inject.Inject

/**
 * Created by oohyugi on 8/16/17.
 */
class PlayersFragment:BaseFragment(),PlayersView{

    fun newsInstance(): PlayersFragment {
        val bundle = Bundle()
        val fragment = PlayersFragment()
        fragment.setArguments(bundle)
        return fragment
    }

    override fun injectModule(activityComponent: ActivityComponent) {
        activityComponent.inject(this)

    }

    @Inject
    lateinit var presenter: PlayersPresenter
    lateinit var adapter: PlayerAdapter
    private var players: MutableList<PlayersModel> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.players_fragment)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.getPlayers()
        adapter = PlayerAdapter(activity, players)
        val gridLayout = GridLayoutManager(activity,2)
        rvPlayers.layoutManager = gridLayout
        rvPlayers.adapter = adapter


        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar!!.setTitle("Team")
        presenter.attachView(this)
        swipeRefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
               players.clear()
                presenter.getPlayers()
            }
        })
    }


    override fun displayLoading() {
        swipeRefresh.isRefreshing=true

    }

    override fun stopLoading() {
        swipeRefresh.isRefreshing=false

    }

    override fun displayPlayers(result: MutableList<PlayersModel>) {
        players.addAll(result)
        adapter.notifyDataSetChanged()


    }

    override fun displayError(message: String?) {
        Log.e("error",message)
    }
}