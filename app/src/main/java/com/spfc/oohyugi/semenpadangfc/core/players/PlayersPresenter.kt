package com.spfc.oohyugi.semenpadangfc.core.players

import com.spfc.oohyugi.semenpadangfc.base.BasePresenter
import com.spfc.oohyugi.semenpadangfc.data.model.PlayersModel
import com.spfc.oohyugi.semenpadangfc.data.repo.players.PlayersRequest
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import javax.inject.Inject

/**
 * Created by oohyugi on 8/16/17.
 */
class PlayersPresenter @Inject constructor(val repo:PlayersRequest): BasePresenter<PlayersView>(){

    override fun attachView(mView: PlayersView) {
        super.attachView(mView)
    }

    override fun detachView() {
        super.detachView()
    }
    private var compositeSub = CompositeDisposable()

    fun getPlayers() {
        mView?.displayLoading()
//        compositeSub.add(repo.getPlayers()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    result ->
//                    mView?.stopLoading()
//                    mView?.displayPlayers(result)
//                }, {
//                    error ->
//                    mView?.stopLoading()
//                    mView?.displayError(error.message)
//
//                }))
        doAsync {

            val htmlDocument = Jsoup.connect("http://semenpadangfc.co.id/").get()

            val rows = htmlDocument.select("li[class=gdlr-item gdlr-classic-player]")
            var players: PlayersModel
            val hasmap = HashMap<String, String>()

            val listHas: MutableList<PlayersModel> = mutableListOf()
            uiThread {
                if (rows != null) {


                    for (row: Element in rows) {

//
                        val src = row.select("div[class=gdlr-soccer-player-thumbnail]").first()
                        val imgSrc = src.select("img")
                        val absHref = imgSrc.attr("abs:src")
                        val eleNomor = row.select("div[class=gdlr-classic-player-item-content]").first()
                        val nomor = eleNomor.select("div[class=gdlr-soccer-player-squad gdlr-title-font gdlr-skin-info]")
                        val namefirst = eleNomor.select("div[class=gdlr-soccer-player-title-wrapper]")
                        val name = namefirst.select("h3[class=gdlr-soccer-player-title gdlr-skin-title]")
                        val posisi = namefirst.select("div[class=gdlr-soccer-player-position gdlr-skin-info]")
                        players = PlayersModel(name = name.text(),posisi = posisi.text(),nomor = nomor.text(),img = absHref)
                        listHas.add(players)
                        println(posisi.text())
                    }
                   mView?.stopLoading();
                    mView?.displayPlayers(listHas)

                } else {
                    mView?.stopLoading();
                }
            }
        }
    }
}