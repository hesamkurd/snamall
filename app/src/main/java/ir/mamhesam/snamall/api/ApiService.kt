package ir.mamhesam.snamall.api

import com.google.gson.JsonObject
import io.reactivex.Single
import ir.mamhesam.snamall.data.*
import ir.mamhesam.snamall.feature.profile.auoth.TokenContainer
import okhttp3.OkHttpClient
import org.json.JSONObject
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
    fun getAmazingProducts(): Single<List<ResponseAmazingProducts>>

    @GET("home/popular_products.php")
    fun getPopularProduct(): Single<List<ResponsePopularProduct>>

    @GET("home/banner_2.php")
    fun getBannerType2(): Single<List<ResponseBannerType2>>

    @GET("home/sub_categoy_1.php")
    fun subCatLevel1(@Query("general_cat") general_cat: Int): Single<List<ResponseSubCatLevel1>>

    @GET("home/all_amazing_products.php")
    fun allAmazing(@Query("sort") sort: Int): Single<List<ResponseAllAmazing>>

    ////////// Detail Product ////////
    @GET("product/products.php")
    fun getDetailProduct(@Query("id") product_id: Int): Single<ResponseDetailsProduct>

    @GET("product/product_technical_property.php")
    fun getTechnicalProperty(@Query("product_id") product_id: Int): Single<List<ResponseTechnicalProperty>>

    @FormUrlEncoded
    @POST("product/add_bokmark.php")
    fun addToFavorite(@Field("product_id") product_id: Int): Single<ResponseAddFavorite>

    //////// auth ///////

    @FormUrlEncoded
    @POST("auoth/check_user.php")
    fun checkUser(@Field("mobile_phone") phone: String): Single<ResponseCheckUser>

    @FormUrlEncoded
    @POST("auoth/register.php")
    fun register(
        @Field("mobile_phone") phone: String,
        @Field("name_family") name: String
    ): Single<ResponseRegister>

    @FormUrlEncoded
    @POST("auoth/login.php")
    fun login(@Field("mobile_phone") phone: String): Single<ResponseLogin>

    //// Comment ////

    @GET("product/scores.php")
    fun showRatingComment(@Query("product_id") product_id: Int): Single<List<ResponseRatingComment>>

    @GET("product/show_comments.php")
    fun showComment(@Query("product_id") product_id: Int): Single<List<ResponseShowComment>>

    @FormUrlEncoded
    @POST("product/add_comment.php")
    fun insertComment(
        @Field("product_id") product_id: Int,
        @Field("content") content: String,
        @Field("title") title: String,
        @Field("positive") positive: String,
        @Field("negative") negative: String,
        @Field("Advice") Advice: String
    ): Single<ResponseInsertComment>

    @FormUrlEncoded
    @POST("product/add_profesional_comment.php")
    fun insertCommentPro(
        @Field("product_id") product_id: Int,
        @Field("content") content: String,
        @Field("title") title: String,
        @Field("positive") positive: String,
        @Field("negative") negative: String,
        @Field("Advice") Advice: String,
        @Field("score") score: String
    ): Single<ResponseInsertComment>

    @GET("product/comment_type.php")
    fun showScore(@Query("product_id") product_id: Int): Single<ResponseShowScore>

    /////// Cart //////////

    @FormUrlEncoded
    @POST("cart/add_to_cart_1.php")
    fun addToCart(
        @Field("product_id") product_id: Int,
        @Field("color_id") color_id: Int,
        @Field("size_id") size_id: Int
    ): Single<ResponseInsertComment>

    @GET("cart/cart_list.php")
    fun getCartList(): Single<ResponseCartList>

    @GET("home/basket_count.php")
    fun getCountCart(): Single<ResponseCountCart>

    @FormUrlEncoded
    @POST("cart/remove_from_basket.php")
    fun removeFromCart(@Field("cart_item_id") cart_item_id: Int): Single<ResponseInsertComment>

    @FormUrlEncoded
    @POST("cart/change_count_item.php")
    fun changeCountItem(
        @Field("cart_item_id") cart_item_id: Int,
        @Field("count") count: Int
    ): Single<ResponseChangeCountItem>

    @GET("cart/checkout_list.php")
    fun checkOutList(): Single<ResponseCheckOutList>

    @GET("cart/show_address.php")
    fun getAddress(): Single<List<ResponseShowAddress>>

    @POST("cart/add_new_address.php")
    fun addAddress(@Body address: JsonObject): Single<ResponseAddAddress>

    @GET("cart/wallet_chackout.php")
    fun getTransaction(
        @Query("reciver_id") reciver_id: String,
        @Query("shipping_price") shipping_price: String,
        @Query("payable_price") payable_price: String
    ): Single<ResponseTransaction>

    @GET("profile/get_order_history.php")
    fun getOrderHistory(): Single<List<ResponseOrderHistory>>

    @FormUrlEncoded
    @POST("profile/get_order_detaile.php")
    fun getOrderDetal(@Field("ref_id") ref_id: String): Single<ResponseOrderDetail>

    ////////// Category //////////
    @GET("category/categories.php")
    fun getCategories(): Single<List<ResponseCategories>>

    @GET("category/sub_category.php")
    fun getSubCat(@Query("sub_id") sub_id: Int): Single<List<ResponseSubCat1>>

    @GET("category/brands.php")
    fun getPopularBrand(@Query("subcat_1_id") sub_id: Int): Single<List<ResponsePopularBrand>>

    @GET("category/popular_product.php")
    fun getSubCatProduct(@Query("subcat1_id") sub_id: Int): Single<List<ResponseSubCatProduct>>

    @GET("product/banner_brand.php")
    fun getBrandBanner(@Query("brand_name") brand_name: String): Single<ResponseBrandBanner>

    @GET("product/brand_products.php")
    fun getBrandProduct(@Query("brand_name") brand_name: String): Single<List<ResponseBrandProduct>>

    /////////// Profile ///////
    @GET("profile/show_bookmarks.php")
    fun getFavorite(): Single<List<ResponseFavorite>>

    @GET("profile/show_profile.php")
    fun getInfoUser(): Single<ResponseInfoUser>

    /////// Search //////

    @FormUrlEncoded
    @POST("search/search_resualt.php")
    fun search(@Field("search") search: String): Single<ResponseSearch>
}

fun retrofitApi(): ApiService {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val oldRequest = it.request()
            val newRequest = oldRequest.newBuilder()
            if (TokenContainer.token != null) {
                newRequest.addHeader("Authorization", TokenContainer.token!!)
            }
            newRequest.method(oldRequest.method(), oldRequest.body())
            return@addInterceptor it.proceed(newRequest.build())
        }.build()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://snamall1.mamhesam.ir/v1/api/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)
}