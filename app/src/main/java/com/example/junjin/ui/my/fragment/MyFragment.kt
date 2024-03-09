package com.example.junjin.ui.my.fragment

import MyInfoViewModel
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.junjin.base.ui.BaseFragment
import com.example.junjin.databinding.FragmentMyBinding
import com.example.junjin.model.network.api.AccountApi
import com.example.junjin.model.network.dto.UserInfo
import com.example.junjin.ui.home.viewModel.EssayViewModel
import com.example.junjin.ui.my.activity.MyInfoEditActivity
import com.example.junjin.ui.my.adapter.MyPagerAdapter
import com.example.junjin.util.KVUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class MyFragment : BaseFragment<FragmentMyBinding>() {

    private lateinit var viewModel: MyInfoViewModel
    val userId : Int = KVUtil.get("userId",1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this).get(MyInfoViewModel::class.java)

        lifecycleScope.launch{
            viewModel.getUserInfo(userId)
            val base64String = viewModel.userInfo.value?.avatar?: AccountApi.getAvatar(userId).data
            val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            binding.myAvatar.setImageBitmap(bitmap)
        }

        binding.myName.text = viewModel.userInfo.value?.username
        val viewPager: ViewPager2 = binding.myViewpage
        val tabLayout: TabLayout = binding.tabLayout
        val adapter = MyPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        binding.editBtText.setOnClickListener{
            val intent = Intent(requireContext(), MyInfoEditActivity(viewModel)::class.java)
            startActivity(intent)
        }

        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            // 更新 UI
            binding.myName.text = userInfo.username

        }
        return view
    }
}