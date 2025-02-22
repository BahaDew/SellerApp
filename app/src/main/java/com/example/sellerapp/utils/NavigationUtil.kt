package com.example.sellerapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.sellerapp.R

fun FragmentActivity.addScreen(fm : Fragment) {
    supportFragmentManager.beginTransaction()
        .add(R.id.container, fm)
        .commit()
}

fun FragmentActivity.replaceScreen(fm : Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fm)
        .addToBackStack(fm::class.java.name)
        .commit()
}

fun FragmentActivity.replaceScreenWithoutSave(fm : Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, fm)
        .commit()
}


fun FragmentActivity.popBackStack() {
    supportFragmentManager.popBackStack()
}

fun Fragment.replaceScreen(fm : Fragment) {
    requireActivity().replaceScreen(fm)
}

fun Fragment.replaceScreenWithoutSave(fm : Fragment) {
    requireActivity().replaceScreenWithoutSave(fm)
}

fun Fragment.popBackStack() {
    requireActivity().popBackStack()
}
