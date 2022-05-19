package ir.zhiran2021.snamall.feature.category.brandproduct.repo

import io.reactivex.Single
import ir.zhiran2021.snamall.data.ResponseBrandBanner
import ir.zhiran2021.snamall.data.ResponseBrandProduct
import ir.zhiran2021.snamall.feature.category.brandproduct.source.BrandBannerDataSource

class BrandBannerRepositoryImpl(val remoteBrandBannerDataSource: BrandBannerDataSource):BrandBannerRepository {
    override fun getBrandBanner(brandName: String): Single<ResponseBrandBanner> = remoteBrandBannerDataSource.getBrandBanner(brandName)
    override fun getBrandProduct(brandName: String): Single<List<ResponseBrandProduct>> = remoteBrandBannerDataSource.getBrandProduct(brandName)
}