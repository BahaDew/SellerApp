package com.example.sellerapp.presentation.screens.userInfo
import java.text.SimpleDateFormat
import java.util.*
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ClientInfoData
import com.example.sellerapp.databinding.ScreenInfoBinding
import com.example.sellerapp.presentation.adapters.ClientInfoAdapter
import java.util.Calendar
import java.util.Locale

class ClientInfo : Fragment(R.layout.screen_info) {
    private val binding by viewBinding(ScreenInfoBinding::bind)

    //private val adapter by lazy(LazyThreadSafetyMode.NONE) { LifeTimeAdapter() }
    private var userId: Long = 0
    private lateinit var adapter: ClientInfoAdapter
    val viewModel = ClientInfoViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = requireArguments().getLong("userId", 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserById(userId)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        viewModel.userLD.observe(viewLifecycleOwner) {
            viewModel.getProductById(it.productId)
            binding.firstname.text = it.firstName
            binding.lastname.text = it.secondName
            binding.paymentMonth.text = it.advance_payment
            viewModel.productLD.observe(viewLifecycleOwner) { itp ->
                binding.productName.text = itp.productName
                binding.productPrice.text = itp.priceProduct.toString()
                val tulanganSumma = (itp.priceProduct - it.advance_payment.toInt()) - it.checkPay
                val summa =
                    (itp.priceProduct - it.advance_payment.toInt()) / it.monthOfRent.toLong()
                var size = tulanganSumma / it.checkPay
                binding.paidMonths.text = size.toString()
                var i = 0
                var limit = it.monthOfRent.toInt()
                val items = ArrayList<ClientInfoData>()
                var startDate = getDate(it.startDate, "dd/MM/yyyy")
                while (limit > 0) {
                    items.add(
                        ClientInfoData(
                            summa = summa,
                            tulangan = if (size > 0) 1 else 0,
                            muddat = startDate
                        )
                    )
                    i++
                    limit--
                    size--
                    startDate = nextDate(startDate, "dd/MM/yyyy")
                }
                adapter = ClientInfoAdapter(items)
            }
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
        calendar.time = date
        calendar.add(Calendar.DAY_OF_MONTH, 1)

        val outputFormatter = SimpleDateFormat(inputFormat, Locale.getDefault())
        return outputFormatter.format(calendar.time)
    }
}