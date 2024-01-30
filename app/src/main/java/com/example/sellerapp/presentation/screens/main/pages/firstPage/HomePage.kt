package com.example.sellerapp.presentation.screens.main.pages.firstPage

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.PageFirstBinding
import com.example.sellerapp.presentation.adapters.PageFirstAdapter

class HomePage : Fragment(R.layout.page_first) {
    private val binding by viewBinding(PageFirstBinding::bind)
    private val viewModel = HomeViewModel()
    private val adapter by lazy { PageFirstAdapter() }
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }
    private lateinit var dialog: Dialog
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() {
        dialog = Dialog(requireContext())
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(viewModel.getAllClients())

        viewModel.transferData.observe(viewLifecycleOwner) {
            binding.placeholder.isGone = it.isNotEmpty()
            adapter.submitList(it)
        }
        adapter.setSelectedItemListener {
            val bundle = Bundle()
            bundle.putLong("userId", it.id)
            navController.navigate(R.id.clientInfo, bundle)
        }
        adapter.setLongSelectListener {
            showBottomSheetDialog(it)
        }
        binding.btnAdd.setOnClickListener {
            navController.navigate(R.id.addClientScreen)
        }
    }

    private fun showBottomSheetDialog(user: UserData) {
        dialog.setContentView(R.layout.dialog_edit_user)
        dialog.findViewById<View>(R.id.btn_delete).setOnClickListener {
            viewModel.deleteUser(user)
            dialog.dismiss()
        }

        dialog.findViewById<View>(R.id.btn_edit).setOnClickListener {
            val bundle = Bundle()
            bundle.putLong("userId", user.id)
            navController.navigate(R.id.editClientScreen, bundle)
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }
}