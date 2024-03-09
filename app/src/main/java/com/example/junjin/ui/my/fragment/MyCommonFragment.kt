package com.example.junjin.ui.my.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.junjin.R
import com.example.junjin.databinding.FragmentMyCommonBinding
import com.example.junjin.ui.home.adapter.EssayAdapter
import com.example.junjin.ui.home.adapter.MyEssayAdapter
import com.example.junjin.ui.home.viewModel.EssayViewModel
import com.example.junjin.ui.my.viewmodel.MyEssayViewModel
import com.example.junjin.util.KVUtil
import kotlinx.coroutines.launch

class MyCommonFragment : Fragment() {
    private lateinit var viewModel1: MyEssayViewModel
    private lateinit var viewModel2: MyEssayViewModel
    private lateinit var essayAdapter: MyEssayAdapter
    private var pageTitle: String = ""

    companion object {
        private const val ARG_PAGE_TITLE = "arg_page_title"

        fun newInstance(pageTitle: String): MyCommonFragment {
            val fragment = MyCommonFragment()
            val args = Bundle()
            args.putString(ARG_PAGE_TITLE, pageTitle)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pageTitle = it.getString(ARG_PAGE_TITLE, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyCommonBinding.inflate(inflater, container, false)
        val view = binding.root
        val userId = KVUtil.get("userId",1)
        viewModel1 = ViewModelProvider(this).get(MyEssayViewModel::class.java)
        viewModel2 = ViewModelProvider(this).get(MyEssayViewModel::class.java)
        // 根据 pageTitle 处理不同的逻辑
        if (pageTitle == "我的文章"){
            essayAdapter = MyEssayAdapter(viewModel1)
            lifecycleScope.launch{
                try {
                    viewModel1.getMyEssays(userId)
                } catch (e: Exception) {
                    Log.e("Error", "Failed to fetch essay list: $e")
                }
            }
        }else{
            essayAdapter = MyEssayAdapter(viewModel2)
            lifecycleScope.launch{
                try {
                    viewModel2.getMyLikes()
                } catch (e: Exception) {
                    Log.e("Error", "Failed to fetch essay list: $e")
                }
            }
        }

        binding.myRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = essayAdapter
        }

        viewModel1.essayList.observe(viewLifecycleOwner) { essays ->
            essayAdapter.submitList(essays)
        }
        viewModel2.essayList.observe(viewLifecycleOwner) { essays ->
            essayAdapter.submitList(essays)
        }
        return view
    }
}
