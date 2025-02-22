package com.example.sellerapp.presentation.screens.main.pages.secondPage

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.TodayPaysData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.PageSecondBinding
import com.example.sellerapp.presentation.adapters.TodayPaysAdapter
import com.example.sellerapp.presentation.dialog.SendSmsDialog
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.roundToLong

@Suppress("DEPRECATION")
class PayTodayClients : Fragment(R.layout.page_second) {
    private val binding by viewBinding(PageSecondBinding::bind)
    private val viewModel = PayTodayCViewModel()
    private val adapter = TodayPaysAdapter()
    private var observer: Observer<String> = Observer { openSetSMSDialog(it) }
    private var timeEditComment: Long? = null
    private var timeSendSms: Long? = null

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getAllUsersWithProduct()
        viewModel.usersWithProductLD.observe(viewLifecycleOwner) {
            createPayTodayData(it)
        }
        adapter.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong("userId", it.userId)
            bundle.putLong("productId", it.productId)
            findNavController().navigate(R.id.clientInfo, bundle)
        }
        binding.btnSms.setOnClickListener {
            if (timeSendSms == null) {
                viewModel.onClickSms()
                timeSendSms = System.currentTimeMillis()
                Log.d("TTT", "Birinchi bosilishi")
            } else if(System.currentTimeMillis() - timeSendSms!! > 1000) {
                Log.d("TTT", "1 sekundan keyin bosti")
                viewModel.onClickSms()
                timeSendSms = System.currentTimeMillis()
            } else {
                Log.d("TTT", "Bosolmadi")
            }
        }
        adapter.setOnclickEdit {
            if (timeEditComment == null) {
                openEditComment(it)
                timeEditComment = System.currentTimeMillis()
                Log.d("TTT", "Birinchi bosilishi")
            } else if(System.currentTimeMillis() - timeEditComment!! > 1000) {
                Log.d("TTT", "1 sekundan keyin bosti")
                openEditComment(it)
                timeEditComment = System.currentTimeMillis()
            } else
                Log.d("TTT", "Bosolmadi")
        }
        viewModel.oldSmsLD.observe(this, observer)
    }

    private fun openSetSMSDialog(it: String) {
        val sendSmsDialog = SendSmsDialog()
        sendSmsDialog.show(childFragmentManager, "Salomat")
        sendSmsDialog.setOnClickSend {
            viewModel.onClickSend(it)
            val list = adapter.currentList
            for (i in list) {
                try {
                    val smsManager = SmsManager.getDefault()
                    val phoneNumber = i.phoneNumber.replace(" ", "")
                    Log.d("TTT", phoneNumber)
                    smsManager.sendTextMessage(phoneNumber, null, it, null, null)
                    Log.d("TTT", "SMS Muvafaqiyatli yuborildi!")
                } catch (e: Exception) {
                    Log.d("TTT", "${e.message}")
                }
            }
            sendSmsDialog.dismiss()
        }
        sendSmsDialog.setOldMessage(it)
    }

    private fun openEditComment(todayPaysData: TodayPaysData) {
        val textInputL = TextInputLayout(requireContext())
        val textInputT = TextInputEditText(requireContext())
        textInputT.setText(todayPaysData.comment)
        textInputL.addView(textInputT)
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Edit Comment")
            .setView(textInputL)
            .setPositiveButton("Save") { _, _ ->
                run {
                    viewModel.onClickSave(todayPaysData.productId, textInputT.text.toString())
                    val list = adapter.currentList.toMutableList()
                    list.find { it.productId == todayPaysData.productId }!!.comment =
                        textInputT.text.toString()
                    adapter.submitList(list)
                }
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .create()
        alertDialog.show()
        val params = textInputL.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(60, 50, 60, 50)
    }

    private fun createPayTodayData(it: Map<UserData, List<ProductData>>) {
        val list = ArrayList<TodayPaysData>()
        val currentData = convertDateMillisecond(getData(System.currentTimeMillis()))
        for (item in it) {
            for (product in item.value) {
                var startDate = getData(product.startDate)
                val monthOfRent = product.monthOfRent
                var checkPay = product.checkPay
                val summa = (product.priceProduct - product.advancePayment) / monthOfRent
                for (i in 1..monthOfRent) {
                    if (checkPay.roundToLong() < summa.roundToLong() && convertDateMillisecond(
                            startDate
                        ) == currentData
                    ) {
                        list.add(
                            TodayPaysData(
                                productId = product.id,
                                userId = item.key.id,
                                fullName = "${item.key.firstName} ${item.key.secondName}",
                                phoneNumber = item.key.phoneNumber,
                                productName = product.productName,
                                priceProduct = product.priceProduct,
                                startDate = product.startDate,
                                payingDeadline = 0,
                                comment = product.comment
                            )
                        )
                        break
                    }
                    checkPay -= summa
                    startDate = nextDate(startDate)
                }
            }
        }
        binding.placeholder.isGone = list.isNotEmpty()
        binding.btnSms.isGone = list.isEmpty()
        adapter.submitList(list)
    }

    private fun getData(milliSecond: Long): String {
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val calendar = Calendar.getInstance().apply { timeInMillis = milliSecond }
        return formatter.format(calendar.time)
    }

    private fun convertDateMillisecond(date: String): Long {
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val dateMil: Date = formatter.parse(date)!!
        return dateMil.time
    }

    private fun nextDate(date: String): String {
        val inputFormatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val dateMil = inputFormatter.parse(date)
        val calendar = Calendar.getInstance()
        if (dateMil != null) {
            calendar.time = dateMil
        }
        calendar.add(Calendar.MONTH, 1)
        return inputFormatter.format(calendar.time)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllUsersWithProduct()
    }
}
/*
private fun openSetSMSDialog(it: String) {
        val sendSmsDialog = SendSmsDialog()
        sendSmsDialog.show(childFragmentManager, "Salomat")
        sendSmsDialog.setOnClickSend {
            viewModel.onClickSend(it)
            val list = adapter.currentList
            val numbers = ArrayList<String>()
            for (i in list) {
//                    val smsManager = SmsManager.getDefault()
                val phoneNumber = i.phoneNumber.replace(" ", "").removeRange(0, 4)
//                        smsManager.sendTextMessage(phoneNumber, null, it, null, null)
                numbers.add(phoneNumber)
            }
            try {

                Log.d("TTT", numbers.joinToString(";"))
                val smsIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:${numbers.joinToString(";")}"))
                smsIntent.putExtra("sms_body", it)
                startActivity(smsIntent)
                Log.d("TTT", "SMS Muvafaqiyatli yuborildi!")
            } catch (e: Exception) {
                Log.d("TTT", "Sms Yuborilmadi!")
            }
            sendSmsDialog.dismiss()
        }
        sendSmsDialog.setOldMessage(it)
    }*/