package com.spfc.oohyugi.semenpadangfc.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spfc.oohyugi.semenpadangfc.BaseApp
import com.spfc.oohyugi.semenpadangfc.di.component.ActivityComponent
import com.spfc.oohyugi.semenpadangfc.di.modul.ActivityModule

/**
 * Created by oohyugi on 8/16/17.
 */
abstract class BaseFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityComponent = BaseApp.get(activity)
                .appComponent
                .activityComponent()
                .activityModule(ActivityModule(activity))
                .build()

        injectModule(activityComponent)
    }



    override fun onDestroyView() {
        super.onDestroyView()
    }

    abstract fun injectModule(activityComponent: ActivityComponent)
}