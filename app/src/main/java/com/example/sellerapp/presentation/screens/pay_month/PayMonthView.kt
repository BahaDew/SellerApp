package com.example.sellerapp.presentation.screens.pay_month

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.PayMonthData
import com.example.sellerapp.databinding.ScreenPayMonthBinding
import com.example.sellerapp.presentation.adapters.PayMonthAdapter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class PayMonthView : Fragment(R.layout.screen_pay_month) {
    private val binding: ScreenPayMonthBinding by viewBinding(ScreenPayMonthBinding::bind)
    private val adapter = PayMonthAdapter()
    private val viewModel = PayMonthViewModel()
    private var productId = 0L
    private val dataFormat = "dd.MM.yyyy"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productId = requireArguments().getLong("productId")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getProduct(productId)
        viewModel.productDL.observe(viewLifecycleOwner) {
            val summa = (it.priceProduct - it.advance_payment.toInt()) / it.monthOfRent.toInt()
            var startDate = getDate(it.startDate, dataFormat)
            val currentDate = convertStringToDateMillis(getDate(System.currentTimeMillis(), dataFormat), dataFormat)
            val monthOfRent = it.monthOfRent.toInt()
            var checkPay = it.checkPay
            val list = ArrayList<PayMonthData>()
            for (i in 1..monthOfRent) {
                val dayMillS = convertStringToDateMillis(startDate, dataFormat)
                list.add(
                    PayMonthData(
                        summa = summa.toLong(),
                        tulangan = if (checkPay >= summa) 1 else if(dayMillS < currentDate) -1 else 0,
                        muddat = startDate
                    ),
                )
                Log.d("TTT", " data ${getDate(dayMillS, dataFormat)} -> ${getDate(currentDate, dataFormat)}")
                checkPay -= summa
                startDate = nextDate(startDate, dataFormat)
            }
            adapter.submitList(list)
        }
        binding.imageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(milliSeconds: Long, dateFormat: String): String {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = milliSeconds
        }
        return formatter.format(calendar.time)
    }

    fun nextDate(inputDate: String, inputFormat: String): String {
        val inputFormatter = SimpleDateFormat(inputFormat, Locale.getDefault())
        val date = inputFormatter.parse(inputDate)
        val calendar = Calendar.getInstance()
        if (date != null) {
            calendar.time = date
        }
        calendar.add(Calendar.MONTH, 1)

        val outputFormatter = SimpleDateFormat(inputFormat, Locale.getDefault())
        return outputFormatter.format(calendar.time)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertStringToDateMillis(dateString: String, pattern: String): Long {
        val sdf = SimpleDateFormat(pattern)
        val date: Date = sdf.parse(dateString)!!
        return date.time
    }
    fun formatAmount(amountString: String): String {
        val amount = amountString.toLongOrNull()
        if (amount != null) {
            val formattedAmount = String.format("%,d", amount)
            return formattedAmount.replace(",", " ")
        }
        return "Invalid amount"
    }
}