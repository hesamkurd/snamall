package ir.mamhesam.snamall.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity: AppCompatActivity(),BaseView{
    override fun setProgressBar(progress: Boolean) {

    }

}

abstract class BaseFragment:Fragment(),BaseView{
    override fun setProgressBar(progress: Boolean) {

    }

}

interface BaseView{
    fun setProgressBar(progress:Boolean)
}

abstract class BaseViewModel: ViewModel(){

    val compositeDisposable = CompositeDisposable()
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}