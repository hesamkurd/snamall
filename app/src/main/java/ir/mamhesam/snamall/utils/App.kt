package ir.mamhesam.snamall.utils

import android.app.Application
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.api.retrofitApi
import ir.mamhesam.snamall.feature.home.repo.BannersRepository
import ir.mamhesam.snamall.feature.home.repo.BannersRepositoryImpl
import ir.mamhesam.snamall.feature.home.source.RemoteBannersDataSource
import ir.mamhesam.snamall.feature.home.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        val myModule= module {

            single<ApiService> { retrofitApi() }

            factory<BannersRepository> { BannersRepositoryImpl(RemoteBannersDataSource(get())) }
            viewModel { HomeViewModel(get()) }
        }
        startKoin {
            androidContext(this@App)
            modules(myModule)
        }

    }



}