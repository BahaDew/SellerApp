package com.example.sellerapp.presentation.dialog

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.databinding.DialogCheckPayBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CheckedPayBtDialogF : BottomSheetDialogFragment(R.layout.dialog_check_pay) {
    private val binding : DialogCheckPayBinding by viewBinding(DialogCheckPayBinding::bind)
    private var textInfo = ""
    private var onClickCheck : ((Int) -> Unit)? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtInfo.text = textInfo
        binding.btnPaid.setOnClickListener {
            onClickCheck?.invoke(1)
        }
        binding.btnUnpaid.setOnClickListener {
            onClickCheck?.invoke(0)
        }
    }
    fun setTextInfo(textInfo : String) {
        this.textInfo = textInfo
    }
    fun setOnClickCheck(onClickCheck : (Int) -> Unit) {
        this.onClickCheck = onClickCheck
    }
}