package com.spfc.oohyugi.semenpadangfc.data.repo.players

import com.spfc.oohyugi.semenpadangfc.data.model.BaseModel
import com.spfc.oohyugi.semenpadangfc.data.model.PlayersModel
import io.reactivex.Observable

/**
 * Created by oohyugi on 8/16/17.
 */
interface PlayersRequest {

    fun getPlayers(): Observable<BaseModel<List<PlayersModel>>>
}