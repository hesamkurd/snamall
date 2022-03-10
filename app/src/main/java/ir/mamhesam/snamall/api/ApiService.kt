package ir.mamhesam.snamall.api

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseAmazingProducts
import ir.mamhesam.snamall.data.ResponseBanners
import ir.mamhesam.snamall.data.ResponseGeneralCategory
import ir.mamhesam.snamall.data.ResponsePopularProduct
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("home/banners.php")
    fun getBanners(): Single<List<ResponseBanners>>

    @GET("home/general_category.php")
    fun getGeneralCategory(): Single<List<ResponseGeneralCategory>>

    @GET("home/amazing_products.php")
    fun getAmazingProducts():Single<List<ResponseAmazingProducts>>

    @GET("home/popular_products.php")
    fun getPopularProduct(): Single<List<ResponsePopularProduct>>
}

fun retrofitApi():ApiService{

    val retrofit= Retrofit.Builder()
        .baseUrl("http://snamall1.mamhesam.ir/v1/api/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}