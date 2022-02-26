package ir.mamhesam.snamall.utils

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.api.retrofitApi
import ir.mamhesam.snamall.data.ResponseBanners
import ir.mamhesam.snamall.data.ResponseGeneralCategory
import ir.mamhesam.snamall.feature.home.adapter.BannersAdapter
import ir.mamhesam.snamall.feature.home.adapter.GeneralCategoryAdapter
import ir.mamhesam.snamall.feature.home.repo.BannersRepository
import ir.mamhesam.snamall.feature.home.repo.BannersRepositoryImpl
import ir.mamhesam.snamall.feature.home.repo.GeneralCategoryRepository
import ir.mamhesam.snamall.feature.home.repo.GeneralCategoryRepositoryImpl
import ir.mamhesam.snamall.feature.home.source.RemoteBannersDataSource
import ir.mamhesam.snamall.feature.home.source.RemoteGeneralCategoryDataSource
import ir.mamhesam.snamall.feature.home.viewmodel.HomeViewModel
import ir.mamhesam.snamall.services.ImageLoadImpl
import ir.mamhesam.snamall.services.ImageLoadService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
        Timber.plant(Timber.DebugTree())

        val myModule= module {

            single<ApiService> { retrofitApi() }
            single<ImageLoadService> { ImageLoadImpl() }

            //banners Home
            factory<BannersRepository> { BannersRepositoryImpl(RemoteBannersDataSource(get())) }
            factory<GeneralCategoryRepository> { GeneralCategoryRepositoryImpl(RemoteGeneralCategoryDataSource(get())) }
            viewModel { HomeViewModel(get(),get()) }
            factory { (banners: List<ResponseBanners>)-> BannersAdapter(banners,get())}
            factory { (categories: List<ResponseGeneralCategory>)-> GeneralCategoryAdapter(categories,get())}
        }
        startKoin {
            androidContext(this@App)
            modules(myModule)
        }

    }



}