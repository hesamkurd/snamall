package ir.mamhesam.snamall.feature.category.brandproduct.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseBrandBanner
import ir.mamhesam.snamall.data.ResponseBrandProduct

interface BrandBannerRepository {
    fun getBrandBanner(brandName:String):Single<ResponseBrandBanner>

    fun getBrandProduct(brandName:String): Single<List<ResponseBrandProduct>>
}