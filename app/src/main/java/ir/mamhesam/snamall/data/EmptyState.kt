package ir.mamhesam.snamall.data

import androidx.annotation.StringRes

data class EmptyState(val show:Boolean, @StringRes val message:Int=0, val showButton: Boolean=false)
