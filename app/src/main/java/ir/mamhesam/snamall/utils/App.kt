package ir.mamhesam.snamall.utils

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.android.gms.auth.api.Auth
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.api.retrofitApi
import ir.mamhesam.snamall.data.*
import ir.mamhesam.snamall.feature.home.adapter.*
import ir.mamhesam.snamall.feature.home.detailproduct.adapter.*
import ir.mamhesam.snamall.feature.home.detailproduct.property.PropertyViewModel
import ir.mamhesam.snamall.feature.home.detailproduct.property.adapter.TechnicalPropertyAdapter
import ir.mamhesam.snamall.feature.home.detailproduct.property.repo.TechnicalRepository
import ir.mamhesam.snamall.feature.home.detailproduct.property.repo.TechnicalRepositoryImpl
import ir.mamhesam.snamall.feature.home.detailproduct.property.source.RemoteTechnicalDataSource
import ir.mamhesam.snamall.feature.home.detailproduct.repo.DetailProductRepository
import ir.mamhesam.snamall.feature.home.detailproduct.repo.DetailProductRepositoryImpl
import ir.mamhesam.snamall.feature.home.detailproduct.source.RemoteDetailProductDataSource
import ir.mamhesam.snamall.feature.home.detailproduct.viewmodel.DetailProductViewModel
import ir.mamhesam.snamall.feature.home.repo.*
import ir.mamhesam.snamall.feature.home.source.*
import ir.mamhesam.snamall.feature.home.viewmodel.HomeViewModel
import ir.mamhesam.snamall.feature.profile.auoth.AuthViewModel
import ir.mamhesam.snamall.feature.profile.auoth.repo.AuthRepository
import ir.mamhesam.snamall.feature.profile.auoth.repo.AuthRepositoryImpl
import ir.mamhesam.snamall.feature.profile.auoth.source.AuthLocalDataSource
import ir.mamhesam.snamall.feature.profile.auoth.source.RemoteAuthDataSource
import ir.mamhesam.snamall.services.ImageLoadImpl
import ir.mamhesam.snamall.services.ImageLoadService
import org.koin.android.ext.android.get
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

        val appSignatureHashHelper = AppSignatureHashHelper(this)
        Log.i("RE", "HashKey: "+appSignatureHashHelper.appSignatures[0])

        val myModule= module {

            single<ApiService> { retrofitApi() }
            single<ImageLoadService> { ImageLoadImpl() }

            /////////// Home ///////////
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
            ////// Detail Product /////////
            factory<DetailProductRepository> { DetailProductRepositoryImpl(RemoteDetailProductDataSource(get())) }
            viewModel {(productId: Int)-> DetailProductViewModel(productId,get()) }
            factory { (images: List<ImagesItem>)-> GalleryAdapter(images,get()) }
            factory { (ima: List<String>)-> CatAdapter(ima) }
            factory { (colors: List<ProductColorsItem>)-> ColorAdapter(colors) }
            factory { (sizes: List<ProductSizesItem>)-> SizeAdapter(sizes) }
            factory { (similar: List<SimilarProductItem>)-> SimilarAdapter(similar,get()) }
            factory { (property: List<PropertiesItem>)-> PropertiesAdapter(property) }
            factory { (comment: List<CommentsItem>)-> CommentAdapter(comment)}
            factory<TechnicalRepository> { TechnicalRepositoryImpl(RemoteTechnicalDataSource(get())) }
            viewModel { (productId:Int)-> PropertyViewModel(productId,get()) }
            factory { (property:List<ResponseTechnicalProperty>)-> TechnicalPropertyAdapter(property) }

            ////// Auth ////////
            single<SharedPreferences> {this@App.getSharedPreferences("user", MODE_PRIVATE)  }
            factory<AuthRepository> {AuthRepositoryImpl(RemoteAuthDataSource(get()), AuthLocalDataSource(get()))  }
            viewModel { AuthViewModel(get()) }



        }
        startKoin {
            androidContext(this@App)
            modules(myModule)
        }

        val authRepository: AuthRepository = get()
        authRepository.loadToken()

    }



}