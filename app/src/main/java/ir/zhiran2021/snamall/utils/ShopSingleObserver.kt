package ir.zhiran2021.snamall.utils

import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class ShopSingleObserver<T>(val compositeDisposable: CompositeDisposable): SingleObserver<T> {

    override fun onSubscribe(d: Disposable) {
       compositeDisposable.add(d)
    }

    override fun onError(e: Throwable) {
        Timber.e(e)
    }
}