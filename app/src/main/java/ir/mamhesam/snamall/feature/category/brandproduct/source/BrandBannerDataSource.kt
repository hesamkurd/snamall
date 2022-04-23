package ir.mamhesam.snamall.feature.category.brandproduct.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseBrandBanner
import ir.mamhesam.snamall.data.ResponseBrandProduct

interface BrandBannerDataSource {
    fun getBrandBanner(brandName:String):Single<ResponseBrandBanner>

    fun getBrandProduct(brandName:String): Single<List<ResponseBrandProduct>>

}