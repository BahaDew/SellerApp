package com.example.sellerapp.presentation.screens.userInfo
import java.text.SimpleDateFormat
import java.util.*
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.ClientInfoData
import com.example.sellerapp.databinding.ScreenInfoBinding
import com.example.sellerapp.presentation.adapters.ClientInfoAdapter
import com.example.sellerapp.presentation.adapters.ProductListAdapter
import java.util.Calendar
import java.util.Locale

class ClientInfo : Fragment(R.layout.screen_info) {
    private val binding by viewBinding(ScreenInfoBinding::bind)

    private var userId: Long = 0
    private val adapter = ProductListAdapter()
    val viewModel = ClientInfoViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userId = requireArguments().getLong("userId", 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserById(userId)
        viewModel.getProductById(userId)
        viewModel.userLD.observe(viewLifecycleOwner) {
            binding.firstName.text = it.firstName
            binding.lastname.text = it.secondName
            binding.age.text = it.age.toString()
            binding.phoneNumber.text = it.phoneNumber
        }
        viewModel.productLD.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        binding.imgUser.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnAdd.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong("userId", userId)
            findNavController().navigate(R.id.addProductView, bundle)
        }
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
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
        calendar.add(Calendar.DAY_OF_MONTH, 1)

        val outputFormatter = SimpleDateFormat(inputFormat, Locale.getDefault())
        return outputFormatter.format(calendar.time)
    }
}