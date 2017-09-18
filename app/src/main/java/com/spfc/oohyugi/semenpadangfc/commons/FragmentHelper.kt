package com.spfc.oohyugi.semenpadangfc.commons



import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by yogi on 07/02/17.
 */
object FragmentHelper {
    fun replaceFragment(activity: AppCompatActivity, fragment: Fragment?, idContainer: Int) {
        activity.supportFragmentManager.beginTransaction()
                .replace(idContainer, fragment)
                .commit()
    }

    fun addFragment(activity: AppCompatActivity, fragment: Fragment, idContainer: Int) {
        activity.supportFragmentManager.beginTransaction()
                .add(idContainer, fragment)
                .commit()
    }
}