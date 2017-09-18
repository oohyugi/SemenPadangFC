package com.spfc.oohyugi.semenpadangfc.core.players

import com.spfc.oohyugi.semenpadangfc.base.BaseView
import com.spfc.oohyugi.semenpadangfc.data.model.PlayersModel

/**
 * Created by oohyugi on 8/16/17.
 */
interface PlayersView: BaseView{
    fun displayLoading()
    fun stopLoading()
    fun  displayPlayers(result: MutableList<PlayersModel>)
    fun  displayError(message: String?)

}