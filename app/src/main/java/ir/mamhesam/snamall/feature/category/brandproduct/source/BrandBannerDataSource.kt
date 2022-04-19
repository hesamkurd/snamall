package ir.mamhesam.snamall.feature.category.brandproduct.source

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseBrandBanner

interface BrandBannerDataSource {
    fun getBrandBanner(brandName:String):Single<ResponseBrandBanner>
}