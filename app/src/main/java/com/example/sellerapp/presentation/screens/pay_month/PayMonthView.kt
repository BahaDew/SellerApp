package com.example.sellerapp.presentation.screens.pay_month

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.PayMonthData
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.databinding.ScreenPayMonthBinding
import com.example.sellerapp.presentation.adapters.PayMonthAdapter
import com.example.sellerapp.presentation.dialog.CheckedPayBtDialogF
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.roundToLong

class PayMonthView : Fragment(R.layout.screen_pay_month) {
    private val binding: ScreenPayMonthBinding by viewBinding(ScreenPayMonthBinding::bind)
    private val adapter = PayMonthAdapter()
    private val viewModel = PayMonthViewModel()
    private var productId = 0L
    private val dataFormat = "dd.MM.yyyy"
    private val btCheckDialog by lazy(LazyThreadSafetyMode.NONE) { CheckedPayBtDialogF() }
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
            createPayMontData(it)
        }
        binding.imageView.setOnClickListener {
            findNavController().popBackStack()
        }
        adapter.setLongClickListener { data, position ->
            openCheckedBtDialog(data, position)
        }
    }

    private fun createPayMontData(it: ProductData) {
        val summa = (it.priceProduct - it.advancePayment) / it.monthOfRent
        var startDate = getDate(it.startDate, dataFormat)
        val currentDate =
            convertStringToDateMillis(getDate(System.currentTimeMillis(), dataFormat), dataFormat)
        val monthOfRent = it.monthOfRent
        var checkPay = it.checkPay
        val list = ArrayList<PayMonthData>()
        for (i in 1..monthOfRent) {
            val dayMillS = convertStringToDateMillis(startDate, dataFormat)
            list.add(
                PayMonthData(
                    summa = summa,
                    paid = if (checkPay.roundToLong() >= summa.roundToLong()) 1 else if (dayMillS < currentDate) -1 else 0,
                    deadLine = startDate
                ),
            )
            checkPay -= summa
            startDate = nextDate(startDate)
        }
        adapter.submitList(list)
    }

    private fun openCheckedBtDialog(payMonthData: PayMonthData, position: Int) {
        if (position != 0 && position != adapter.currentList.size - 1) {
            val prevItem = adapter.currentList[position - 1]
            val nextItem = adapter.currentList[position + 1]
            if (prevItem.paid != 1) {
                Toast.makeText(
                    requireContext(),
                    "Avval oldingi oylar to'lanishi kerak",
                    Toast.LENGTH_SHORT
                ).show()
                return
            } else if (nextItem.paid == 1) {
                Toast.makeText(
                    requireContext(),
                    "Ushbu oyni o'zgartira olmaysiz",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
        } else {
            if (position == 0 && adapter.currentList[1].paid == 1) {
                Toast.makeText(
                    requireContext(),
                    "Ushbu oyni o'zgartira olmaysiz",
                    Toast.LENGTH_SHORT
                ).show()
                return
            } else if (position == adapter.currentList.size - 1 && adapter.currentList[position - 1].paid != 1) {
                Toast.makeText(
                    requireContext(),
                    "Avval oldingi oylar to'lanishi kerak",
                    Toast.LENGTH_SHORT
                ).show()
                return
            }
        }
        btCheckDialog.show(childFragmentManager, "Assalom")
        btCheckDialog.setOnClickCheck { payed ->
            val payedOld = payMonthData.paid
            if (payedOld == payed) {
                btCheckDialog.dismiss()
                return@setOnClickCheck
            }
            val currentDate = convertStringToDateMillis(
                getDate(System.currentTimeMillis(), dataFormat),
                dataFormat
            )
            val deadline = convertStringToDateMillis(payMonthData.deadLine, dataFormat)
            val list = adapter.currentList.toMutableList()
            viewModel.updateProduct(productId, payed, payMonthData.summa)
            list.find { it.deadLine == payMonthData.deadLine }?.paid =
                if (payed == 1) 1 else if (deadline < currentDate) -1 else 0
            adapter.submitList(list)
            btCheckDialog.dismiss()
        }
        btCheckDialog.setTextInfo(payMonthData.deadLine)
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(milliSeconds: Long, dateFormat: String): String {
        val formatter = SimpleDateFormat(dateFormat)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = milliSeconds
        }
        return formatter.format(calendar.time)
    }

    private fun nextDate(inputDate: String): String {
        val inputFormatter = SimpleDateFormat(dataFormat, Locale.getDefault())
        val date = inputFormatter.parse(inputDate)
        val calendar = Calendar.getInstance()
        if (date != null) {
            calendar.time = date
        }
        calendar.add(Calendar.MONTH, 1)
        val outputFormatter = SimpleDateFormat(dataFormat, Locale.getDefault())
        return outputFormatter.format(calendar.time)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertStringToDateMillis(dateString: String, pattern: String): Long {
        val sdf = SimpleDateFormat(pattern)
        val date: Date = sdf.parse(dateString)!!
        return date.time
    }
}