package ir.zhiran2021.snamall.feature.category.generalcat.source

import io.reactivex.Single
import ir.zhiran2021.snamall.api.ApiService
import ir.zhiran2021.snamall.data.ResponseProductGeneralCat

class RemoteProductGeneralCatDataSource(val apiService: ApiService):ProductGeneralCatDataSource {
    override fun getProductGeneralCat(general_cat_id: Int): Single<List<ResponseProductGeneralCat>> = apiService.getProductGeneralCat(general_cat_id)
}