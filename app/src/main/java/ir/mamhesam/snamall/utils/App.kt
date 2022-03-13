package ir.mamhesam.snamall.utils

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.api.retrofitApi
import ir.mamhesam.snamall.data.*
import ir.mamhesam.snamall.feature.home.adapter.*
import ir.mamhesam.snamall.feature.home.repo.*
import ir.mamhesam.snamall.feature.home.source.*
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
            factory<AmazingProductsRepository> { AmazingProductsRepositoryImpl(RemoteAmazingProductsDataSource(get())) }
            factory<PopularProductRepository> { PopularProductRepositoryImpl(RemotePopularProductDataSource(get())) }
            factory<BannerType2Repository> { BannerType2RepositoryImpl(RemoteBannerType2DataSource(get())) }
            viewModel { HomeViewModel(get(),get(),get(),get(),get()) }
            factory { (banners: List<ResponseBanners>)-> BannersAdapter(banners,get())}
            factory { (categories: List<ResponseGeneralCategory>)-> GeneralCategoryAdapter(categories,get())}
            factory { (amazingProduct:List<ResponseAmazingProducts>)-> AmazingAdapter(amazingProduct,get())}
            factory { (populars:List<ResponsePopularProduct>)-> PopularAdapter(populars,get())}
            factory { (banners: List<ResponseBannerType2>)-> BannersType2Adapter(banners,get())}

        }
        startKoin {
            androidContext(this@App)
            modules(myModule)
        }

    }



}