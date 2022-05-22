package ir.zhiran2021.snamall.feature.category.subcat2.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseSubCat2

class RemoteSubCat2DataSource(val apiService: ApiService):SubCat2DataSource {
    override fun getSubCat2(subcat2_id: Int): Single<List<ResponseSubCat2>> = apiService.getSubCat2(subcat2_id)
}