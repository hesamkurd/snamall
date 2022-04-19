package ir.mamhesam.snamall.api

import io.reactivex.Single
import ir.mamhesam.snamall.data.*
import ir.mamhesam.snamall.feature.profile.auoth.TokenContainer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

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

    @GET("home/sub_categoy_1.php")
    fun subCatLevel1(@Query("general_cat")general_cat:Int):Single<List<ResponseSubCatLevel1>>

    ////////// Detail Product ////////
    @GET("product/products.php")
    fun getDetailProduct(@Query("id")product_id:Int):Single<ResponseDetailsProduct>

    @GET("product/product_technical_property.php")
    fun getTechnicalProperty(@Query("product_id")product_id:Int):Single<List<ResponseTechnicalProperty>>

    @FormUrlEncoded
    @POST("product/add_bokmark.php")
    fun addToFavorite(@Field("product_id")product_id: Int): Single<ResponseAddFavorite>

    //////// auth ///////

    @FormUrlEncoded
    @POST("auoth/check_user.php")
    fun checkUser(@Field("mobile_phone")phone: String): Single<ResponseCheckUser>

    @FormUrlEncoded
    @POST("auoth/register.php")
    fun register(@Field("mobile_phone")phone: String,@Field("name_family")name: String): Single<ResponseRegister>

    @FormUrlEncoded
    @POST("auoth/login.php")
    fun login(@Field("mobile_phone")phone: String):Single<ResponseLogin>

    //// Comment ////

    @GET("product/scores.php")
    fun showRatingComment(@Query("product_id")product_id: Int):Single<List<ResponseRatingComment>>

    @GET("product/show_comments.php")
    fun showComment(@Query("product_id")product_id: Int): Single<List<ResponseShowComment>>

    @FormUrlEncoded
    @POST("product/add_comment.php")
    fun insertComment(@Field("product_id")product_id: Int,
                      @Field("content")content:String,
                      @Field("title")title:String,
                      @Field("positive")positive:String,
                      @Field("negative")negative:String,
                      @Field("Advice")Advice:String):Single<ResponseInsertComment>

    /////// Cart //////////

    @FormUrlEncoded
    @POST("cart/add_to_cart.php")
    fun addToCart(@Field("product_id")product_id: Int,
                  @Field("color_id")color_id: Int,
                  @Field("size_id")size_id: Int):Single<ResponseInsertComment>

    ////////// Category //////////
    @GET("category/categories.php")
    fun getCategories():Single<List<ResponseCategories>>

    @GET("category/sub_category.php")
    fun getSubCat(@Query("sub_id")sub_id:Int):Single<List<ResponseSubCat1>>

    @GET("category/brands.php")
    fun getPopularBrand(@Query("subcat_1_id")sub_id:Int): Single<List<ResponsePopularBrand>>

    @GET("category/popular_product.php")
    fun getSubCatProduct(@Query("subcat1_id")sub_id:Int):Single<List<ResponseSubCatProduct>>

    @GET("product/banner_brand.php")
    fun getBrandBanner(@Query("brand_name")brand_name:String):Single<ResponseBrandBanner>
}

fun retrofitApi():ApiService{

    val okHttpClient= OkHttpClient.Builder()
        .addInterceptor {
            val oldRequest = it.request()
            val newRequest = oldRequest.newBuilder()
            if (TokenContainer.token != null){
                newRequest.addHeader("Authorization", TokenContainer.token!!)
            }
            newRequest.method(oldRequest.method(), oldRequest.body())
            return@addInterceptor it.proceed(newRequest.build())
        }.build()

    val retrofit= Retrofit.Builder()
        .baseUrl("http://snamall1.mamhesam.ir/v1/api/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)
}