package com.spfc.oohyugi.semenpadangfc

import android.app.Application
import android.content.Context
import com.spfc.oohyugi.semenpadangfc.di.modul.AppModule
import com.spfc.oohyugi.semenpadangfc.di.modul.DataModule
import com.spfc.oohyugi.semenpadangfc.di.component.AppComponent
import com.spfc.oohyugi.semenpadangfc.di.component.DaggerAppComponent

/**
 * Created by oohyugi on 8/11/17.
 */
class BaseApp : Application() {

    companion object {
        fun get(context: Context): BaseApp = context.applicationContext as BaseApp
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()


    }
}