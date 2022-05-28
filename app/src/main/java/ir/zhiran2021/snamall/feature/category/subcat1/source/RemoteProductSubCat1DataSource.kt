package ir.zhiran2021.snamall.feature.category.subcat1.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseProductSubCat1

class RemoteProductSubCat1DataSource(val apiService: ApiService):ProductSubCat1DataSource {
    override fun getProductSubCat1(subcat1_id: Int): Single<List<ResponseProductSubCat1>> = apiService.getProductSubCat1(subcat1_id)
}