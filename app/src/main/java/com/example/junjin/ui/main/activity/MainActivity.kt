package com.example.junjin.ui.main.activity

import android.content.Intent
import android.os.Bundle
import com.example.junjin.R
import com.example.junjin.base.ui.BaseActivity
import com.example.junjin.databinding.ActivityMainBinding
import com.example.junjin.ui.home.fragment.HomeFragment
import com.example.junjin.ui.login.activity.LoginActivity
import com.example.junjin.ui.my.fragment.MyFragment
import com.example.junjin.ui.write.WritingFragment
import com.example.junjin.util.KVUtil

class MainActivity : BaseActivity<ActivityMainBinding>()  {
    private val fragments = listOf(
        lazy { HomeFragment() },
        lazy { WritingFragment()},
        lazy { MyFragment() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (KVUtil.get("token", "") == "" ) {
//            KVUtil.remove("token")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        val navView = binding.navView
        navView.setOnItemSelectedListener { item ->
            val id = when (item.itemId) {
                R.id.nav_home -> 0
                R.id.nav_write -> 1
                R.id.nav_my -> 2
                else -> 3
            }
            val transaction = supportFragmentManager.beginTransaction()
            fragments.filter { it.isInitialized() }.forEach {
                transaction.hide(it.value)
            }
            val fragment = fragments[id]
            if (!fragment.isInitialized()) {
                transaction.add(R.id.fragment_container, fragment.value)
            }
            transaction.show(fragment.value).commit()

            true
        }

        navView.selectedItemId = R.id.nav_home
    }
}