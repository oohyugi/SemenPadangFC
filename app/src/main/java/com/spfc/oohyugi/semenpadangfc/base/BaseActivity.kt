package com.spfc.oohyugi.semenpadangfc.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.spfc.oohyugi.semenpadangfc.BaseApp
import com.spfc.oohyugi.semenpadangfc.di.component.ActivityComponent
import com.spfc.oohyugi.semenpadangfc.di.modul.ActivityModule

/**
 * Created by oohyugi on 8/11/17.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityComponent = BaseApp.get(this)
                .appComponent
                .activityComponent()
                .activityModule(ActivityModule(this))
                .build()

        injectModule(activityComponent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun injectModule(activityComponent: ActivityComponent)
}