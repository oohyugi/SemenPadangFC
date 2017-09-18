package com.spfc.oohyugi.semenpadangfc.data.repo.players

import com.spfc.oohyugi.semenpadangfc.api.ApiServices
import com.spfc.oohyugi.semenpadangfc.data.model.BaseModel
import com.spfc.oohyugi.semenpadangfc.data.model.PlayersModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by oohyugi on 8/16/17.
 */
class PlayersRequestImpl @Inject constructor(val service:ApiServices) : PlayersRequest{
    override fun getPlayers(): Observable<BaseModel<List<PlayersModel>>> {
       return service.getPlayers().map {
           BaseModel(
                   code = it.code,
                   message = it.message,
                   data = it.data
           )
       }
    }

}