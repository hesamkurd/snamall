package ir.mamhesam.snamall.utils

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import ir.mamhesam.snamall.MainViewModel
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.api.retrofitApi
import ir.mamhesam.snamall.data.*
import ir.mamhesam.snamall.feature.cart.adapter.CartListAdapter
import ir.mamhesam.snamall.feature.cart.repo.CartListRepository
import ir.mamhesam.snamall.feature.cart.repo.CartListRepositoryImpl
import ir.mamhesam.snamall.feature.cart.source.RemoteCartListDataSource
import ir.mamhesam.snamall.feature.cart.viewmodel.CartListViewModel
import ir.mamhesam.snamall.feature.category.adapter.CategoryAdapter
import ir.mamhesam.snamall.feature.category.brandproduct.adapter.BrandProductAdapter
import ir.mamhesam.snamall.feature.category.brandproduct.repo.BrandBannerRepository
import ir.mamhesam.snamall.feature.category.brandproduct.repo.BrandBannerRepositoryImpl
import ir.mamhesam.snamall.feature.category.brandproduct.source.RemoteBrandBannerDataSource
import ir.mamhesam.snamall.feature.category.brandproduct.viewmodel.BrandBannerViewmodel
import ir.mamhesam.snamall.feature.category.repo.CategoriesRepository
import ir.mamhesam.snamall.feature.category.repo.CategoriesRepositoryImpl
import ir.mamhesam.snamall.feature.category.source.RemoteCategoriesDataSource
import ir.mamhesam.snamall.feature.category.subcat.adapter.PopularBrandAdapter
import ir.mamhesam.snamall.feature.category.subcat.adapter.SubCatProductAdapter
import ir.mamhesam.snamall.feature.category.subcat.adapter.SubCategoryAdapter
import ir.mamhesam.snamall.feature.category.subcat.repo.SubCatRepository
import ir.mamhesam.snamall.feature.category.subcat.repo.SubCatRepositoryImpl
import ir.mamhesam.snamall.feature.category.subcat.source.RemoteSubCatDataSource
import ir.mamhesam.snamall.feature.category.subcat.viewmodel.SubCatViewModel
import ir.mamhesam.snamall.feature.category.viewmodel.CategoriesViewModel
import ir.mamhesam.snamall.feature.home.adapter.*
import ir.mamhesam.snamall.feature.home.detailproduct.adapter.*
import ir.mamhesam.snamall.feature.home.detailproduct.comment.adapter.ShowCommentAdapter
import ir.mamhesam.snamall.feature.home.detailproduct.comment.adapter.ShowRatingCommentAdapter
import ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.repo.InsertCommentRepository
import ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.repo.InsertCommentRepositoryImpl
import ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.source.RemoteInsertCommentDataSource
import ir.mamhesam.snamall.feature.home.detailproduct.comment.insertcomment.viewmodel.InsertCommentViewModel
import ir.mamhesam.snamall.feature.home.detailproduct.comment.repo.CommentRepository
import ir.mamhesam.snamall.feature.home.detailproduct.comment.repo.CommentRepositoryImpl
import ir.mamhesam.snamall.feature.home.detailproduct.comment.source.RemoteCommentDataSource
import ir.mamhesam.snamall.feature.home.detailproduct.comment.viewmodel.CommentViewModel
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
import ir.mamhesam.snamall.feature.home.subcatlevel1.adapter.SubCatLevel1Adapter
import ir.mamhesam.snamall.feature.home.subcatlevel1.repo.SubCatLevel1Repository
import ir.mamhesam.snamall.feature.home.subcatlevel1.repo.SubCatLevel1RepositoryImpl
import ir.mamhesam.snamall.feature.home.subcatlevel1.source.RemoteSubCatLevel1DataSource
import ir.mamhesam.snamall.feature.home.subcatlevel1.viewmodel.SubCatLevel1ViewModel
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
            ////// Sub Category Level 1 /////////
            factory<SubCatLevel1Repository> { SubCatLevel1RepositoryImpl(RemoteSubCatLevel1DataSource(get())) }
            viewModel { (generalCatId:Int)->SubCatLevel1ViewModel(generalCatId,get()) }
            factory { (subCatList:List<ResponseSubCatLevel1>)->SubCatLevel1Adapter(subCatList,get()) }
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

            //// Comment ////
            factory<CommentRepository> { CommentRepositoryImpl(RemoteCommentDataSource(get())) }
            viewModel { (productId:Int)-> CommentViewModel(productId,get()) }
            factory { (ratingComment: List<ResponseRatingComment>)-> ShowRatingCommentAdapter(ratingComment) }
            factory { (showComment: List<ResponseShowComment>)-> ShowCommentAdapter(showComment) }
            single<InsertCommentRepository> { InsertCommentRepositoryImpl(RemoteInsertCommentDataSource(get())) }
            viewModel { (productId:Int)->InsertCommentViewModel(productId,get()) }

            //// Categories ///
            factory<CategoriesRepository> { CategoriesRepositoryImpl(RemoteCategoriesDataSource(get())) }
            viewModel { CategoriesViewModel(get()) }
            factory { (categories: List<SubcatItem>)->CategoryAdapter(categories,get()) }

            factory<SubCatRepository> { SubCatRepositoryImpl(RemoteSubCatDataSource(get())) }
            viewModel { (catId:Int)-> SubCatViewModel(catId,get())}
            factory { (subCat:List<ResponseSubCat1>)-> SubCategoryAdapter(subCat,get())}
            factory { (brand:List<ResponsePopularBrand>)->PopularBrandAdapter(brand,get()) }
            factory { (product:List<ResponseSubCatProduct>)->SubCatProductAdapter(product,get()) }

            factory<BrandBannerRepository> { BrandBannerRepositoryImpl(RemoteBrandBannerDataSource(get())) }
            viewModel { (brand:String)->BrandBannerViewmodel(brand,get()) }
            factory { (product:List<ResponseBrandProduct>)-> BrandProductAdapter(product,get())}

            ////// Cart ////////
            factory<CartListRepository> { CartListRepositoryImpl(RemoteCartListDataSource(get())) }
            viewModel { CartListViewModel(get()) }
            factory { (cartList: ResponseCartList)->CartListAdapter(cartList,get()) }
            viewModel { MainViewModel(get()) }


        }
        startKoin {
            androidContext(this@App)
            modules(myModule)
        }

        val authRepository: AuthRepository = get()
        authRepository.loadToken()

    }



}