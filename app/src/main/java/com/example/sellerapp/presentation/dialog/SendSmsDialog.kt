package com.example.sellerapp.presentation.dialog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.databinding.DialogSendSmsBinding

class SendSmsDialog : DialogFragment(R.layout.dialog_send_sms) {
    private var onClickSend: ((String) -> Unit)? = null
    private var oldMessage = ""
    private val binding: DialogSendSmsBinding by viewBinding(DialogSendSmsBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (oldMessage.isNotEmpty()) {
            binding.textOld.text = oldMessage
            binding.textOld.isGone = false
        }
        binding.textInputT.setText("")
        binding.textInputL.setEndIconOnClickListener {
            binding.textInputL.helperText = "Message sent ✔"
            onClickSend?.invoke(binding.textInputT.text.toString())
        }
        binding.textOld.setOnClickListener {
            binding.textInputT.setText(oldMessage)
            binding.textOld.isGone = true
        }
        binding.textInputL.isEndIconVisible = false
        binding.textInputT.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.textInputL.isEndIconVisible = binding.textInputT.text.toString().isNotEmpty()
            }
        })
    }

    fun setOnClickSend(onClickSend: ((String) -> Unit)) {
        this.onClickSend = onClickSend
    }

    fun setOldMessage(oldMessage: String) {
        this.oldMessage = oldMessage
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}