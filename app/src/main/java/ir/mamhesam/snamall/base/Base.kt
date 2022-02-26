package ir.mamhesam.snamall.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ir.mamhesam.snamall.R

abstract class BaseActivity : AppCompatActivity(), BaseView {
    override val rootView: CoordinatorLayout?
        get() = window.decorView.rootView as CoordinatorLayout

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
}

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()
    val progressBarLiveData = MutableLiveData<Boolean>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}