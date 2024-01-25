package com.example.sellerapp.utils.extensions.extentions

import androidx.fragment.app.Fragment
import com.example.sellerapp.R

fun Fragment.openFragmentAddBackStack(fm: Fragment, name: String? = null) {
    parentFragmentManager.beginTransaction()
        .replace(R.id.container, fm)
        .addToBackStack(name)
        .commit()
}

fun Fragment.openFragmentAddMethod(fm: Fragment, name: String? = null) {
    parentFragmentManager.beginTransaction()
        .add(R.id.container, fm)
        .addToBackStack(name)
        .commit()
}

fun Fragment.popUpBackStack() {
    parentFragmentManager.popBackStack()
}

fun Fragment.popUpBackStack(name: String?, inclusive: Int = 0) {
    parentFragmentManager.popBackStack(name, inclusive)
}