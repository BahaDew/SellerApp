package com.example.sellerapp.presentation.screens.main.pages.firstPage

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.sellerapp.R
import com.example.sellerapp.data.model.UserData
import com.example.sellerapp.databinding.PageFirstBinding
import com.example.sellerapp.presentation.adapters.PageFirstAdapter
import com.example.sellerapp.presentation.screens.addUser.AddClientScreen
import com.example.sellerapp.presentation.screens.editUser.EditClientScreen
import com.example.sellerapp.utils.replaceScreen

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
            adapter.submitList(it)
        }
        adapter.setSelectedItemListener {
            val direction = HomePageDirections.actionHomePageToClientInfo(it.id)
            navController.navigate(direction)
        }
        adapter.setLongSelectListener {
            showBottomSheetDialog(it)
        }
        binding.btnAdd.setOnClickListener {
            Log.d("TAG", "initView: bosildi")
            replaceScreen(AddClientScreen())
        }
    }
    fun showBottomSheetDialog(user: UserData) {
        dialog.setContentView(R.layout.dialog_edit_user)


        dialog.findViewById<ImageView>(R.id.delete).setOnClickListener{
            viewModel.deleteUser(user)
            dialog.dismiss()
        }

        dialog.findViewById<ImageView>(R.id.edit).setOnClickListener{
            val editClientScreen = EditClientScreen()
            editClientScreen.apply {
                arguments = bundleOf(
                    Pair("firstName", user.firstName),
                    Pair("lastName", user.secondName),
                    Pair("number", user.phoneNumber),
                    Pair("productName", user.productName),
                    Pair("productPrice", user.productPrice),
                    Pair("advance_payment", user.advance_payment),
                    Pair("monthOfRent", user.monthOfRent),
                    Pair("comment", user.comment),
                )
            }
            replaceScreen(editClientScreen)
            dialog.dismiss()
        }


        dialog.show()
        dialog.window?.setBackgroundDrawable(ColorDrawable( Color.TRANSPARENT))
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }
}