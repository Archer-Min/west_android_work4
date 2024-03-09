package com.example.junjin.ui.home.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.junjin.R
import com.example.junjin.base.ui.BaseFragment
import com.example.junjin.databinding.FragmentHomeBinding
import com.example.junjin.ui.home.adapter.EssayAdapter
import com.example.junjin.ui.home.viewModel.EssayViewModel
import com.example.junjin.ui.login.activity.LoginActivity
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var viewModel: EssayViewModel
    private lateinit var essayAdapter: EssayAdapter
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // 初始化 ViewModel 和 Adapter
        viewModel = ViewModelProvider(this).get(EssayViewModel::class.java)
        essayAdapter = EssayAdapter(viewModel)
        arrayAdapter = ArrayAdapter(requireContext(),R.layout.item_spinner,resources.getStringArray(R.array.sorting_options))

//        viewModel.navigateToLogin.observe(viewLifecycleOwner, Observer { navigateToLogin ->
//            if (navigateToLogin) {
//                // 执行跳转到登录页面的逻辑
//                val intent = Intent(requireContext(), LoginActivity::class.java)
//                startActivity(intent)
//
//                // 重置 LiveData，确保只触发一次跳转
//                viewModel.onNavigateToLoginComplete()
//            }
//        })
        // 在视图创建时调用 ViewModel 的获取数据方法
        lifecycleScope.launch {
            try {
                viewModel.getEssayClickList(10)
            } catch (e: Exception) {
                Log.e("Error", "Failed to fetch essay list: $e")
            }
        }

        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        // 设置 RecyclerView 的布局管理器和适配器
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = essayAdapter
        }

        spinner = binding.spinner
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 1){
                    lifecycleScope.launch {
                        try {
                            viewModel.getEssayDateList(1)
                        } catch (e: Exception) {
                            Log.e("Error", "Failed to fetch essay list: $e")
                        }
                    }
                }else{
                    lifecycleScope.launch {
                        try {
                            viewModel.getEssayClickList(10)
                        } catch (e: Exception) {
                            Log.e("Error", "Failed to fetch essay list: $e")
                        }
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented")
            }
        }

        // 从 ViewModel 中观察数据，并在数据更新时更新 Adapter
        viewModel.essayList.observe(viewLifecycleOwner) { essays ->
            essayAdapter.submitList(essays)
        }
        return view
    }
}