package com.example.sellerapp.utils.extensions.extentions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.sellerapp.R

fun FragmentActivity.addFragment(fm: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .add(R.id.container, fm)
        .commit()
}