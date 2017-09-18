package com.spfc.oohyugi.semenpadangfc.di.component


import com.spfc.oohyugi.semenpadangfc.di.modul.AppModule
import com.spfc.oohyugi.semenpadangfc.di.modul.DataModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by oohyugi on 8/11/17.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        DataModule::class
))
interface AppComponent {
    fun activityComponent(): ActivityComponent.Builder
}