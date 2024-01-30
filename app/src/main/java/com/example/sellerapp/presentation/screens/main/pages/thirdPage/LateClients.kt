package com.example.sellerapp.presentation.screens.main.pages.thirdPage

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ProductData
import com.example.sellerapp.data.model.TodayPaysData
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.PageThirdBinding
import com.example.sellerapp.presentation.adapters.LatePaysAdapter
import com.example.sellerapp.presentation.screens.main.pages.secondPage.PayTodayCViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.roundToLong

class LateClients : Fragment(R.layout.page_third) {
    private val binding by viewBinding(PageThirdBinding::bind)
    private val viewModel = PayTodayCViewModel()
    private val adapter = LatePaysAdapter()
    private var timeEditComment: Long? = null
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
    }

    private fun openEditComment(todayPaysData: TodayPaysData) {
        val textInputL = TextInputLayout(requireContext())
        val textInputT = TextInputEditText(requireContext())
        textInputT.hint = "Comment"
        textInputT.setText(todayPaysData.comment)
        textInputL.addView(textInputT)
        val alertDialog = AlertDialog.Builder(requireContext())
            .setMessage("Edit Comment")
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
        params.setMargins(50, 50, 50, 50)
    }

    private fun createPayTodayData(it: Map<UserData, List<ProductData>>) {
        val list = ArrayList<TodayPaysData>()
        val currentData = convertDateMillisecond(getData(System.currentTimeMillis()))
        for (item in it) {
            for (product in item.value) {
                var startDate = getData(product.startDate)
                val monthOfRent = product.monthOfRent
                var checkPay = product.checkPay
                val summa = (product.priceProduct - product.advance_payment) / monthOfRent
                for (i in 1..monthOfRent) {
                    if (checkPay.roundToLong() < summa.roundToLong() && convertDateMillisecond(startDate) < currentData) {
                        list.add(
                            TodayPaysData(
                                productId = product.id,
                                userId = item.key.id,
                                fullName = "${item.key.firstName} ${item.key.secondName}",
                                phoneNumber = item.key.phoneNumber,
                                productName = product.productName,
                                priceProduct = product.priceProduct,
                                startDate = product.startDate,
                                payingDeadline = convertDateMillisecond(startDate),
                                comment = product.comment
                            )
                        )
                        break
                    }
                    startDate = nextDate(startDate)
                    checkPay -= summa
                }
            }
        }
        binding.placeholder.isGone = list.isNotEmpty()

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