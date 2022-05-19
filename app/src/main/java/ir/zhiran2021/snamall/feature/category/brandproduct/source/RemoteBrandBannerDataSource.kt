package ir.zhiran2021.snamall.feature.category.brandproduct.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseBrandBanner
import ir.zhiran2021.snamall.data.ResponseBrandProduct

class RemoteBrandBannerDataSource(val apiService: ApiService): BrandBannerDataSource {
    override fun getBrandBanner(brandName: String): Single<ResponseBrandBanner> = apiService.getBrandBanner(brandName)
    override fun getBrandProduct(brandName: String): Single<List<ResponseBrandProduct>> = apiService.getBrandProduct(brandName)
}