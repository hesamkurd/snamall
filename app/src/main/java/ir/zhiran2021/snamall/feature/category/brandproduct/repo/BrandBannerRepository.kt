package ir.zhiran2021.snamall.feature.category.brandproduct.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseBrandBanner
import ir.zhiran2021.snamall.data.ResponseBrandProduct

interface BrandBannerRepository {
    fun getBrandBanner(brandName:String):Single<ResponseBrandBanner>

    fun getBrandProduct(brandName:String): Single<List<ResponseBrandProduct>>
}