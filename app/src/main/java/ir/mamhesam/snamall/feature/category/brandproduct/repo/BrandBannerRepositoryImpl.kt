package ir.mamhesam.snamall.feature.category.brandproduct.repo

import io.reactivex.Single
import ir.mamhesam.snamall.data.ResponseBrandBanner
import ir.mamhesam.snamall.feature.category.brandproduct.source.BrandBannerDataSource

class BrandBannerRepositoryImpl(val remoteBrandBannerDataSource: BrandBannerDataSource):BrandBannerRepository {
    override fun getBrandBanner(brandName: String): Single<ResponseBrandBanner> = remoteBrandBannerDataSource.getBrandBanner(brandName)
}