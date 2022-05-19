package ir.zhiran2021.snamall.feature.category.brandproduct.source

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseBrandBanner
import ir.zhiran2021.snamall.data.ResponseBrandProduct

interface BrandBannerDataSource {
    fun getBrandBanner(brandName:String):Single<ResponseBrandBanner>

    fun getBrandProduct(brandName:String): Single<List<ResponseBrandProduct>>

}