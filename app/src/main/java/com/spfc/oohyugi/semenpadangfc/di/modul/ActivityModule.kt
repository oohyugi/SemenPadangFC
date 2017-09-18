package com.spfc.oohyugi.semenpadangfc.di.modul

import android.app.Activity
import android.content.Context
import com.spfc.oohyugi.semenpadangfc.di.ActivityContext
import com.spfc.oohyugi.semenpadangfc.di.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by oohyugi on 8/11/17.
 */
@Module
class ActivityModule(val activity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivity(): Activity = activity

    @Provides
    @ActivityContext
    fun provideActivityContext(): Context = activity

}