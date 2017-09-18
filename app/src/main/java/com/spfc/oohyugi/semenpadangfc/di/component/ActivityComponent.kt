package com.spfc.oohyugi.semenpadangfc.di.component


import android.app.DialogFragment
import com.spfc.oohyugi.semenpadangfc.core.MainActivity
import com.spfc.oohyugi.semenpadangfc.core.detail.DetailActivity
import com.spfc.oohyugi.semenpadangfc.core.news.NewsFragment
import com.spfc.oohyugi.semenpadangfc.core.players.PlayersFragment
import com.spfc.oohyugi.semenpadangfc.di.ActivityScope
import com.spfc.oohyugi.semenpadangfc.di.modul.ActivityModule
import dagger.Subcomponent

/**
 * Created by oohyugi on 8/11/17.
 */
@ActivityScope
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        fun activityModule(activityModule: ActivityModule): Builder

        fun build(): ActivityComponent
    }

    fun inject(fragment: NewsFragment)
    fun inject(detailActivity: DetailActivity)
    fun inject(fragment: PlayersFragment)

}