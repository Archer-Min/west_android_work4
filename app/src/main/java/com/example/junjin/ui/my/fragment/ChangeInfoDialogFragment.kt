package com.example.junjin.ui.home.fragment

import MyInfoViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.junjin.R
import com.example.junjin.databinding.FragmentMyBinding
import com.example.junjin.databinding.FragmentMyDialogBinding
import com.example.junjin.model.network.api.AccountApi
import kotlinx.coroutines.launch

class ChangeInfoDialogFragment(private val viewModel: MyInfoViewModel, private val index: Int) : DialogFragment() {

    private var _binding: FragmentMyDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyDialogBinding.inflate(inflater, container, false)

        binding.root.setOnClickListener{
            dismiss()
        }
        binding.view.setOnClickListener{

        }
        if (index == 1){
            binding.send.setOnClickListener{
                val name = binding.commentInput.text
                lifecycleScope.launch{
                    val result = AccountApi.changeName(name.toString())
                    if (result.code == 200){
                        Log.d("username","修改用户名成功！")
                        viewModel.userInfo.value?.username = name.toString()
                        viewModel.updateUserName(name.toString())
                    }
                    dismiss()
                }
            }
        }else{
            binding.send.setOnClickListener {
                val password = binding.commentInput.text
                lifecycleScope.launch{
                    val result = AccountApi.changePassword(password.toString())
                    if (result.code == 200){
                        Log.d("password","修改密码成功！")
                    }
                    dismiss()
                }
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 在这里可以设置弹框的内容和行为
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置弹框样式为底部显示
        setStyle(STYLE_NORMAL, R.style.BottomDialog)
    }
}