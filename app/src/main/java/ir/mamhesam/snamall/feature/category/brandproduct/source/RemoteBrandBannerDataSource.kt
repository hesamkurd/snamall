package ir.mamhesam.snamall.feature.category.brandproduct.source

import io.reactivex.Single
import ir.mamhesam.snamall.api.ApiService
import ir.mamhesam.snamall.data.ResponseBrandBanner
import ir.mamhesam.snamall.data.ResponseBrandProduct

class RemoteBrandBannerDataSource(val apiService: ApiService): BrandBannerDataSource {
    override fun getBrandBanner(brandName: String): Single<ResponseBrandBanner> = apiService.getBrandBanner(brandName)
    override fun getBrandProduct(brandName: String): Single<List<ResponseBrandProduct>> = apiService.getBrandProduct(brandName)
}