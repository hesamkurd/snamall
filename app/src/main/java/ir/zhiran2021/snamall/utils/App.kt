package ir.zhiran2021.snamall.utils

import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import ir.zhiran2021.snamall.MainViewModel
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.api.retrofitApi
import ir.zhiran2021.snamall.data.*
import ir.zhiran2021.snamall.feature.cart.adapter.CartListAdapter
import ir.zhiran2021.snamall.feature.cart.nextlevel.adapter.CheckOutAdapter
import ir.zhiran2021.snamall.feature.cart.nextlevel.repo.CheckOutListRepository
import ir.zhiran2021.snamall.feature.cart.nextlevel.repo.CheckOutListRepositoryImpl
import ir.zhiran2021.snamall.feature.cart.nextlevel.source.RemoteCheckOutListDataSource
import ir.zhiran2021.snamall.feature.cart.nextlevel.viewmodel.CheckOutListViewModel
import ir.zhiran2021.snamall.feature.cart.repo.CartListRepository
import ir.zhiran2021.snamall.feature.cart.repo.CartListRepositoryImpl
import ir.zhiran2021.snamall.feature.cart.source.RemoteCartListDataSource
import ir.zhiran2021.snamall.feature.cart.viewmodel.CartListViewModel
import ir.zhiran2021.snamall.feature.category.adapter.CategoryAdapter
import ir.zhiran2021.snamall.feature.category.adapter.CategoryChildAdapter
import ir.zhiran2021.snamall.feature.category.brandproduct.adapter.BrandProductAdapter
import ir.zhiran2021.snamall.feature.category.brandproduct.repo.BrandBannerRepository
import ir.zhiran2021.snamall.feature.category.brandproduct.repo.BrandBannerRepositoryImpl
import ir.zhiran2021.snamall.feature.category.brandproduct.source.RemoteBrandBannerDataSource
import ir.zhiran2021.snamall.feature.category.brandproduct.viewmodel.BrandBannerViewmodel
import ir.zhiran2021.snamall.feature.category.generalcat.adapter.ProductGeneralCatAdapter
import ir.zhiran2021.snamall.feature.category.generalcat.repo.ProductGeneralCatRepository
import ir.zhiran2021.snamall.feature.category.generalcat.repo.ProductGeneralCatRepositoryImpl
import ir.zhiran2021.snamall.feature.category.generalcat.source.RemoteProductGeneralCatDataSource
import ir.zhiran2021.snamall.feature.category.generalcat.viewmodel.ProductGeneralCatViewModel
import ir.zhiran2021.snamall.feature.category.repo.CategoriesRepository
import ir.zhiran2021.snamall.feature.category.repo.CategoriesRepositoryImpl
import ir.zhiran2021.snamall.feature.category.source.RemoteCategoriesDataSource
import ir.zhiran2021.snamall.feature.category.subcat.adapter.PopularBrandAdapter
import ir.zhiran2021.snamall.feature.category.subcat.adapter.SubCatProductAdapter
import ir.zhiran2021.snamall.feature.category.subcat.adapter.SubCategoryAdapter
import ir.zhiran2021.snamall.feature.category.subcat.repo.SubCatRepository
import ir.zhiran2021.snamall.feature.category.subcat.repo.SubCatRepositoryImpl
import ir.zhiran2021.snamall.feature.category.subcat.source.RemoteSubCatDataSource
import ir.zhiran2021.snamall.feature.category.subcat.viewmodel.SubCatViewModel
import ir.zhiran2021.snamall.feature.category.subcat1.adapter.ProductSubCat1Adapter
import ir.zhiran2021.snamall.feature.category.subcat1.repo.ProductSubCat1Repository
import ir.zhiran2021.snamall.feature.category.subcat1.repo.ProductSubCat1RepositoryImpl
import ir.zhiran2021.snamall.feature.category.subcat1.source.RemoteProductSubCat1DataSource
import ir.zhiran2021.snamall.feature.category.subcat1.viewmodel.ProductSubCat1ViewModel
import ir.zhiran2021.snamall.feature.category.subcat2.adapter.SubCat2Adapter
import ir.zhiran2021.snamall.feature.category.subcat2.repo.SubCat2Repository
import ir.zhiran2021.snamall.feature.category.subcat2.repo.SubCat2RepositoryImpl
import ir.zhiran2021.snamall.feature.category.subcat2.source.RemoteSubCat2DataSource
import ir.zhiran2021.snamall.feature.category.subcat2.viewmodel.SubCat2ViewModel
import ir.zhiran2021.snamall.feature.category.viewmodel.CategoriesViewModel
import ir.zhiran2021.snamall.feature.home.adapter.*
import ir.zhiran2021.snamall.feature.home.allamazing.adapter.AllAmazingAdapter
import ir.zhiran2021.snamall.feature.home.allamazing.repo.AllAmazingRepository
import ir.zhiran2021.snamall.feature.home.allamazing.repo.AllAmazingRepositoryImpl
import ir.zhiran2021.snamall.feature.home.allamazing.source.RemoteAllAmazingDataSource
import ir.zhiran2021.snamall.feature.home.allamazing.viewmodel.AllAmazingViewModel
import ir.zhiran2021.snamall.feature.home.allamazingmarket.adapter.AllAmazingMarketAdapter
import ir.zhiran2021.snamall.feature.home.allamazingmarket.repo.AllAmazingMarketRepository
import ir.zhiran2021.snamall.feature.home.allamazingmarket.repo.AllAmazingMarketRepositoryImpl
import ir.zhiran2021.snamall.feature.home.allamazingmarket.source.RemoteAllAmazingMarketDataSource
import ir.zhiran2021.snamall.feature.home.allamazingmarket.viewmodel.AllAmazingMarketViewModel
import ir.zhiran2021.snamall.feature.home.detailproduct.adapter.*
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.adapter.ShowCommentAdapter
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.adapter.ShowRatingCommentAdapter
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.adapter.ShowScoreAdapter
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.repo.InsertCommentRepository
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.repo.InsertCommentRepositoryImpl
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.source.RemoteInsertCommentDataSource
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.insertcomment.viewmodel.InsertCommentViewModel
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.repo.CommentRepository
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.repo.CommentRepositoryImpl
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.source.RemoteCommentDataSource
import ir.zhiran2021.snamall.feature.home.detailproduct.comment.viewmodel.CommentViewModel
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.repo.ChartRepository
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.repo.ChartRepositoryImpl
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.source.RemoteChartDataSource
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.chart.viewmodel.ChartViewModel
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.adapter.CompareCategoryAdapter
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.adapter.CompareParentAdapter
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.repo.CompareCatRepository
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.repo.CompareCatRepositoryImpl
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.repo.CompareProductRepository
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.repo.CompareProductRepositoryImpl
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.source.RemoteCompareCatDataSource
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.source.RemoteCompareProductDataSource
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.viewmodel.CompareCatViewModel
import ir.zhiran2021.snamall.feature.home.detailproduct.moredialog.compare.viewmodel.CompareProductViewModel
import ir.zhiran2021.snamall.feature.home.detailproduct.property.PropertyViewModel
import ir.zhiran2021.snamall.feature.home.detailproduct.property.adapter.TechnicalPropertyAdapter
import ir.zhiran2021.snamall.feature.home.detailproduct.property.repo.TechnicalRepository
import ir.zhiran2021.snamall.feature.home.detailproduct.property.repo.TechnicalRepositoryImpl
import ir.zhiran2021.snamall.feature.home.detailproduct.property.source.RemoteTechnicalDataSource
import ir.zhiran2021.snamall.feature.home.detailproduct.repo.DetailProductRepository
import ir.zhiran2021.snamall.feature.home.detailproduct.repo.DetailProductRepositoryImpl
import ir.zhiran2021.snamall.feature.home.detailproduct.source.RemoteDetailProductDataSource
import ir.zhiran2021.snamall.feature.home.detailproduct.viewmodel.DetailProductViewModel
import ir.zhiran2021.snamall.feature.home.repo.*
import ir.zhiran2021.snamall.feature.home.source.*
import ir.zhiran2021.snamall.feature.home.subcatlevel1.adapter.SubCatLevel1Adapter
import ir.zhiran2021.snamall.feature.home.subcatlevel1.repo.SubCatLevel1Repository
import ir.zhiran2021.snamall.feature.home.subcatlevel1.repo.SubCatLevel1RepositoryImpl
import ir.zhiran2021.snamall.feature.home.subcatlevel1.source.RemoteSubCatLevel1DataSource
import ir.zhiran2021.snamall.feature.home.subcatlevel1.viewmodel.SubCatLevel1ViewModel
import ir.zhiran2021.snamall.feature.home.viewmodel.HomeViewModel
import ir.zhiran2021.snamall.feature.profile.address.adapter.ShowAddressAdapter
import ir.zhiran2021.snamall.feature.profile.address.repo.AddressRepository
import ir.zhiran2021.snamall.feature.profile.address.repo.AddressRepositoryImpl
import ir.zhiran2021.snamall.feature.profile.address.source.RemoteAddressDataSource
import ir.zhiran2021.snamall.feature.profile.address.viewmodel.AddressViewModel
import ir.zhiran2021.snamall.feature.profile.auoth.AuthViewModel
import ir.zhiran2021.snamall.feature.profile.auoth.privacy.adapter.PrivacyAdapter
import ir.zhiran2021.snamall.feature.profile.auoth.privacy.repo.PrivacyRepository
import ir.zhiran2021.snamall.feature.profile.auoth.privacy.repo.PrivacyRepositoryImpl
import ir.zhiran2021.snamall.feature.profile.auoth.privacy.source.RemotePrivacyDataSource
import ir.zhiran2021.snamall.feature.profile.auoth.privacy.viewmodel.PrivacyViewModel
import ir.zhiran2021.snamall.feature.profile.auoth.repo.AuthRepository
import ir.zhiran2021.snamall.feature.profile.auoth.repo.AuthRepositoryImpl
import ir.zhiran2021.snamall.feature.profile.auoth.rules.adapter.RulesAdapter
import ir.zhiran2021.snamall.feature.profile.auoth.rules.repo.RulesRepository
import ir.zhiran2021.snamall.feature.profile.auoth.rules.repo.RulesRepositoryImpl
import ir.zhiran2021.snamall.feature.profile.auoth.rules.source.RemoteRulesDataSource
import ir.zhiran2021.snamall.feature.profile.auoth.rules.viewmodel.RulesViewModel
import ir.zhiran2021.snamall.feature.profile.auoth.source.AuthLocalDataSource
import ir.zhiran2021.snamall.feature.profile.auoth.source.RemoteAuthDataSource
import ir.zhiran2021.snamall.feature.profile.favorite.adapter.FavoriteAdapter
import ir.zhiran2021.snamall.feature.profile.favorite.repo.FavoriteRepository
import ir.zhiran2021.snamall.feature.profile.favorite.repo.FavoriteRepositoryImpl
import ir.zhiran2021.snamall.feature.profile.favorite.source.RemoteFavoriteDataSource
import ir.zhiran2021.snamall.feature.profile.favorite.viewmodel.FavoriteViewModel
import ir.zhiran2021.snamall.feature.profile.infouser.repo.InfoUserRepository
import ir.zhiran2021.snamall.feature.profile.infouser.repo.InfoUserRepositoryImpl
import ir.zhiran2021.snamall.feature.profile.infouser.source.RemoteInfoUserDataSource
import ir.zhiran2021.snamall.feature.profile.infouser.viewmodel.InfoUserViewModel
import ir.zhiran2021.snamall.feature.profile.order.adapter.OrderAdapter
import ir.zhiran2021.snamall.feature.profile.order.orderdetail.adapter.OrderDetailAdapter
import ir.zhiran2021.snamall.feature.profile.order.orderdetail.repo.OrderDetailRepository
import ir.zhiran2021.snamall.feature.profile.order.orderdetail.repo.OrderDetailRepositoryImpl
import ir.zhiran2021.snamall.feature.profile.order.orderdetail.source.RemoteOrderDetailDataSource
import ir.zhiran2021.snamall.feature.profile.order.orderdetail.viewmodel.OrderDetailViewModel
import ir.zhiran2021.snamall.feature.profile.order.repo.OrderHistoryRepository
import ir.zhiran2021.snamall.feature.profile.order.repo.OrderHistoryRepositoryImpl
import ir.zhiran2021.snamall.feature.profile.order.source.RemoteOrderHistoryDataSource
import ir.zhiran2021.snamall.feature.profile.order.viewmodel.OrderHistoryViewModel
import ir.zhiran2021.snamall.feature.profile.orderdelivery.adapter.OrderDeliveryAdapter
import ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.adapter.OrderDetailDeliveryAdapter
import ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.repo.OrderDetailDeliveryRepository
import ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.repo.OrderDetailDeliveryRepositoryImpl
import ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.source.RemoteOrderDetailDeliveryDataSource
import ir.zhiran2021.snamall.feature.profile.orderdelivery.orderdeliverydetail.viewmodel.OrderDetailDeliveryViewModel
import ir.zhiran2021.snamall.feature.profile.orderdelivery.repo.OrderDeliveryRepository
import ir.zhiran2021.snamall.feature.profile.orderdelivery.repo.OrderDeliveryRepositoryImpl
import ir.zhiran2021.snamall.feature.profile.orderdelivery.source.RemoteOrderDeliveryDataSource
import ir.zhiran2021.snamall.feature.profile.orderdelivery.viewmodel.OrderDeliveryViewModel
import ir.zhiran2021.snamall.feature.search.adapter.PartOneAdapter
import ir.zhiran2021.snamall.feature.search.adapter.PartTwoAdapter
import ir.zhiran2021.snamall.feature.search.repo.SearchRepository
import ir.zhiran2021.snamall.feature.search.repo.SearchRepositoryImpl
import ir.zhiran2021.snamall.feature.search.source.RemoteSearchDataSource
import ir.zhiran2021.snamall.feature.search.viewmodel.SearchViewModel
import ir.zhiran2021.snamall.services.ImageLoadImpl
import ir.zhiran2021.snamall.services.ImageLoadService
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
            factory<BestSellRepository> { BestSellRepositoryImpl(RemoteBestSellDataSource(get())) }
            factory<AmazingMarketRepository> { AmazingMarketRepositoryImpl(RemoteAmazingMarketDataSource(get())) }

            viewModel { HomeViewModel(get(),get(),get(),get(),get(),get(),get()) }
            factory { (banners: List<ResponseBanners>)-> BannersAdapter(banners,get())}
            factory { (categories: List<ResponseGeneralCategory>)-> GeneralCategoryAdapter(categories,get())}
            factory { (amazingProduct:List<ResponseAmazingProducts>)-> AmazingAdapter(amazingProduct,get())}
            factory { (populars:List<ResponsePopularProduct>)-> PopularAdapter(populars,get())}
            factory { (banners: List<ResponseBannerType2>)-> BannersType2Adapter(banners,get())}
            factory { (bestSell:List<ResponseBestSellProduct>)->BestSellAdapter(bestSell,get()) }
            factory { (amazingMarket:List<ResponseAmazingMarket>)-> AmazingMarketAdapter(amazingMarket,get())}

            factory<AllAmazingRepository> { AllAmazingRepositoryImpl(RemoteAllAmazingDataSource(get())) }
            viewModel { (sort:Int)-> AllAmazingViewModel(sort,get()) }
            factory { (allAmazing:List<ResponseAllAmazing>)->AllAmazingAdapter(allAmazing,get()) }

            factory<AllAmazingMarketRepository> { AllAmazingMarketRepositoryImpl(RemoteAllAmazingMarketDataSource(get())) }
            viewModel { (sort:Int)->AllAmazingMarketViewModel(sort,get()) }
            factory { (allAmazingMarket:List<ResponseAllAmazingMarket>)-> AllAmazingMarketAdapter(allAmazingMarket,get())}
            ////// Sub Category Level 1 /////////
            factory<SubCatLevel1Repository> { SubCatLevel1RepositoryImpl(RemoteSubCatLevel1DataSource(get())) }
            viewModel { (generalCatId:Int)->SubCatLevel1ViewModel(generalCatId,get()) }
            factory { (subCatList:List<ResponseSubCatLevel1>)->SubCatLevel1Adapter(subCatList,get()) }
            ////// Detail Product /////////
            factory<DetailProductRepository> { DetailProductRepositoryImpl(RemoteDetailProductDataSource(get())) }
            viewModel {(productId: Int)-> DetailProductViewModel(productId,get()) }
            factory { (imagesOrder: List<ImagesItem>)-> GalleryAdapter(imagesOrder,get()) }
            factory { (ima: List<ResponsCategory>)-> CatAdapter(ima) }
            factory { (colors: List<ProductColorsItem>)-> ColorAdapter(colors) }
            factory { (sizes: List<ProductSizesItem>)-> SizeAdapter(sizes) }
            factory { (similar: List<SimilarProductItem>)-> SimilarAdapter(similar,get()) }
            factory { (property: List<PropertiesItem>)-> PropertiesAdapter(property) }
            factory { (comment: List<CommentsItem>)-> CommentAdapter(comment)}
            factory<TechnicalRepository> { TechnicalRepositoryImpl(RemoteTechnicalDataSource(get())) }
            viewModel { (productId:Int)-> PropertyViewModel(productId,get()) }
            factory { (property:List<ResponseTechnicalProperty>)-> TechnicalPropertyAdapter(property) }

            factory<ChartRepository> { ChartRepositoryImpl(RemoteChartDataSource(get())) }
            viewModel { (productId:Int)-> ChartViewModel(productId,get()) }

            factory<CompareCatRepository> { CompareCatRepositoryImpl(RemoteCompareCatDataSource(get())) }
            viewModel { (productId:Int)-> CompareCatViewModel(productId,get())}
            factory { (compare:List<ResponseCompareCat>)->CompareCategoryAdapter(compare,get()) }

            factory<CompareProductRepository> { CompareProductRepositoryImpl(RemoteCompareProductDataSource(get())) }
            viewModel { (bundle:Bundle)-> CompareProductViewModel(bundle,get())}
            factory { (parent:List<PropertyItemCompare>)->CompareParentAdapter(parent) }

            ////// Auth ////////
            single<SharedPreferences> {this@App.getSharedPreferences("user", MODE_PRIVATE)  }
            factory<AuthRepository> {AuthRepositoryImpl(RemoteAuthDataSource(get()), AuthLocalDataSource(get()))  }
            viewModel { AuthViewModel(get()) }

            factory<PrivacyRepository> { PrivacyRepositoryImpl(RemotePrivacyDataSource(get())) }
            viewModel { PrivacyViewModel(get()) }
            factory { (lists:List<ResponsePrivacy>)-> PrivacyAdapter(lists)}

            factory<RulesRepository> { RulesRepositoryImpl(RemoteRulesDataSource(get())) }
            viewModel { RulesViewModel(get()) }
            factory { (lists:List<ResponseRules>)-> RulesAdapter(lists)}
            //// Comment ////
            factory<CommentRepository> { CommentRepositoryImpl(RemoteCommentDataSource(get())) }
            viewModel { (productId:Int)-> CommentViewModel(productId,get()) }
            factory { (ratingComment: List<ResponseRatingComment>)-> ShowRatingCommentAdapter(ratingComment) }
            factory { (showComment: List<ResponseShowComment>)-> ShowCommentAdapter(showComment) }
            single<InsertCommentRepository> { InsertCommentRepositoryImpl(RemoteInsertCommentDataSource(get())) }
            viewModel { (productId:Int)->InsertCommentViewModel(productId,get()) }
            factory { (score:List<ScoreItem>)-> ShowScoreAdapter(score)}

            //// Categories ///
            factory<CategoriesRepository> { CategoriesRepositoryImpl(RemoteCategoriesDataSource(get())) }
            viewModel { CategoriesViewModel(get()) }
            factory { (categories: List<ResponseCategories>)->CategoryAdapter(categories,get()) }
            factory { (categoryChile:List<SubcatItem>)-> CategoryChildAdapter(categoryChile,get())}

            factory<SubCat2Repository> { SubCat2RepositoryImpl(RemoteSubCat2DataSource(get())) }
            viewModel { (subCatId:Int)->SubCat2ViewModel(subCatId,get()) }
            factory { (subCats:List<ResponseSubCat2>)-> SubCat2Adapter(subCats,get())}

            factory<ProductSubCat1Repository> { ProductSubCat1RepositoryImpl(RemoteProductSubCat1DataSource(get())) }
            viewModel { (subCat1:Int)-> ProductSubCat1ViewModel(subCat1,get()) }
            factory { (subCats1:List<ResponseProductSubCat1>)-> ProductSubCat1Adapter(subCats1,get())}

            factory<ProductGeneralCatRepository> { ProductGeneralCatRepositoryImpl(RemoteProductGeneralCatDataSource(get())) }
            viewModel { (generalCat:Int)-> ProductGeneralCatViewModel(generalCat,get()) }
            factory { (generalCatList:List<ResponseProductGeneralCat>)-> ProductGeneralCatAdapter(generalCatList,get()) }

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
            factory<CheckOutListRepository> { CheckOutListRepositoryImpl(RemoteCheckOutListDataSource(get())) }
            viewModel { CheckOutListViewModel(get()) }
            factory { (checkOut:List<ProductItemDeliveriesItem> )->CheckOutAdapter(checkOut,get()) }
            factory<AddressRepository> { AddressRepositoryImpl(RemoteAddressDataSource(get())) }
            viewModel { AddressViewModel(get()) }
            factory { (address:List<ResponseShowAddress>)-> ShowAddressAdapter(address)}


            ////////// Profile //////

            factory<OrderHistoryRepository> {OrderHistoryRepositoryImpl(RemoteOrderHistoryDataSource(get()))  }
            viewModel { OrderHistoryViewModel(get()) }
            factory { (orders: List<ResponseOrderHistory>)-> OrderAdapter(orders,get())}

            factory<OrderDetailRepository> {OrderDetailRepositoryImpl(RemoteOrderDetailDataSource(get()))  }
            viewModel { (refId: String)-> OrderDetailViewModel(refId,get())}
            factory { (detailOrder:List<OrderDetailItem>)-> OrderDetailAdapter(detailOrder,get())}

            factory<FavoriteRepository> { FavoriteRepositoryImpl(RemoteFavoriteDataSource(get())) }
            viewModel { FavoriteViewModel(get()) }
            factory { (favorite:List<ResponseFavorite>)-> FavoriteAdapter(favorite,get())}

            factory<InfoUserRepository> {InfoUserRepositoryImpl(RemoteInfoUserDataSource(get()))  }
            viewModel { InfoUserViewModel(get()) }

            factory<OrderDeliveryRepository> { OrderDeliveryRepositoryImpl(RemoteOrderDeliveryDataSource(get())) }
            viewModel { OrderDeliveryViewModel(get()) }
            factory { (deliveries:List<ResponseOrderDelivery>)-> OrderDeliveryAdapter(deliveries,get()) }

            factory<OrderDetailDeliveryRepository> { OrderDetailDeliveryRepositoryImpl(RemoteOrderDetailDeliveryDataSource(get())) }
            viewModel { (refId:String)-> OrderDetailDeliveryViewModel(refId,get())}
            factory { (orders:List<OrderDeliveryDetailItem>)-> OrderDetailDeliveryAdapter(orders,get()) }

            /// Search ///////
            factory<SearchRepository> { SearchRepositoryImpl(RemoteSearchDataSource(get())) }
            viewModel { SearchViewModel(get()) }
            factory { (partOne:List<Part1Item>)-> PartOneAdapter(partOne)}
            factory { (partTwo:List<Part2Item>)->PartTwoAdapter(partTwo,get()) }




        }
        startKoin {
            androidContext(this@App)
            modules(myModule)
        }

        val authRepository: AuthRepository = get()
        authRepository.loadToken()

    }



}