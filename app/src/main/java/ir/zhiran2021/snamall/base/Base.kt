package ir.zhiran2021.snamall.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.zhiran2021.snamall.R

abstract class BaseActivity : AppCompatActivity(), BaseView {
    override val rootView: CoordinatorLayout?
        get() {

        val parent = window.decorView.findViewById<ViewGroup>(android.R.id.content)
        if (parent !is CoordinatorLayout) {
            parent.children.forEach {
                if (it is CoordinatorLayout) {
                    return it
                }
            }
            throw Exception("rootView must be Coordinator Layout")

        } else {
            return parent
        }
    }

    override val myContext: Context?
        get() = this
}

abstract class BaseFragment : Fragment(), BaseView {

    override val rootView: CoordinatorLayout?
        get() = view as CoordinatorLayout

    override val myContext: Context?
        get() = context
}

interface BaseView {
    val myContext: Context?
    val rootView: CoordinatorLayout?
    fun setProgressBar(progress: Boolean) {
        rootView?.let { rootView ->
            myContext?.let {
                var progressView = rootView.findViewById<View>(R.id.frame_progress)
                if (progressView == null && progress) {
                    progressView = LayoutInflater.from(myContext)
                        .inflate(R.layout.progress_view, rootView, false)
                    rootView.addView(progressView)
                }
                progressView?.visibility = if (progress) View.VISIBLE else View.GONE
            }

        }
    }
    fun showEmptyState(layout: Int): View?{
        rootView?.let {rootView->
            myContext?.let {
                var emptyState = rootView.findViewById<View>(R.id.lnr_empty)
                if (emptyState == null){

                    emptyState = LayoutInflater.from(myContext).inflate(layout,rootView,false)
                    rootView.addView(emptyState)
                }
                emptyState.visibility = View.VISIBLE
                return emptyState
            }
        }
        return null
    }

    fun showCheckInternet(layout:Int): View?{
        rootView?.let { rootView->
            myContext?.let {
                var checkInternet = rootView.findViewById<View>(R.id.lnr_check_internet)
                if (checkInternet == null){
                    checkInternet = LayoutInflater.from(myContext).inflate(layout,rootView,false)
                }
                checkInternet.visibility = View.VISIBLE
                return checkInternet
            }
        }
        return null
    }


}

fun <T> Single<T>.customObserver(): Single<T> {

    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()
    val progressBarLiveData = MutableLiveData<Boolean>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}