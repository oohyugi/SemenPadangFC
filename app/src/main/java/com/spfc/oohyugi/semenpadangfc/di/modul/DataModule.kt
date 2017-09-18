package com.spfc.oohyugi.semenpadangfc.di.modul

import com.spfc.oohyugi.semenpadangfc.api.ApiServices
import com.spfc.oohyugi.semenpadangfc.data.repo.news.DetailRepoRequest
import com.spfc.oohyugi.semenpadangfc.data.repo.news.DetailRepoRequestImpl
import com.spfc.oohyugi.semenpadangfc.data.repo.news.NewsRepoRequest
import com.spfc.oohyugi.semenpadangfc.data.repo.news.NewsRepoRequestImpl
import com.spfc.oohyugi.semenpadangfc.data.repo.players.PlayersRequest
import com.spfc.oohyugi.semenpadangfc.data.repo.players.PlayersRequestImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by oohyugi on 8/11/17.
 */
@Module
class DataModule {

    @Provides
    @Singleton fun provideApiServices(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)


    @Provides
    @Singleton fun provideNewsRepo(newsRepoRequestImpl: NewsRepoRequestImpl) : NewsRepoRequest = newsRepoRequestImpl

    @Provides
    @Singleton fun provideDetailRepo(detailRepoRequestImpl: DetailRepoRequestImpl) : DetailRepoRequest = detailRepoRequestImpl

    @Provides
    @Singleton fun providePlayersRepo(playersRequestImpl: PlayersRequestImpl) : PlayersRequest = playersRequestImpl

}