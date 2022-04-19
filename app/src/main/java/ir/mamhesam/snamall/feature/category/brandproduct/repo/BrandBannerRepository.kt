package ir.mamhesam.snamall.feature.category.brandproduct.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseBrandBanner

interface BrandBannerRepository {
    fun getBrandBanner(brandName:String):Single<ResponseBrandBanner>
}