package com.example.junjin.ui.my.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.junjin.ui.my.fragment.MyCommonFragment

class MyPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2 // 假设有两个页面

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyCommonFragment.newInstance("我的文章") // 第一个页面
            1 -> MyCommonFragment.newInstance("我的点赞") // 第二个页面
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    fun getPageTitle(position: Int): String {
        return when (position) {
            0 -> "我的文章"
            1 -> "我的点赞"
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
