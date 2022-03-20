package ir.mamhesam.snamall.api

import io.reactivex.Single
import ir.mamhesam.snamall.data.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /////// HOME //////////

    @GET("home/banners.php")
    fun getBanners(): Single<List<ResponseBanners>>

    @GET("home/general_category.php")
    fun getGeneralCategory(): Single<List<ResponseGeneralCategory>>

    @GET("home/amazing_products.php")
    fun getAmazingProducts():Single<List<ResponseAmazingProducts>>

    @GET("home/popular_products.php")
    fun getPopularProduct(): Single<List<ResponsePopularProduct>>

    @GET("home/banner_2.php")
    fun getBannerType2(): Single<List<ResponseBannerType2>>

    ////////// Detail Product ////////
    @GET("product/product.php")
    fun getDetailProduct(@Query("id")product_id:Int):Single<ResponseDetailsProduct>

}

fun retrofitApi():ApiService{

    val retrofit= Retrofit.Builder()
        .baseUrl("http://snamall1.mamhesam.ir/v1/api/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}