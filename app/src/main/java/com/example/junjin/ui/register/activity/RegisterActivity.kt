package com.example.junjin.ui.register.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.junjin.base.ui.BaseActivity
import com.example.junjin.databinding.ActivityRegisterBinding
import com.example.junjin.model.network.api.AccountApi
import com.example.junjin.ui.login.activity.LoginActivity
import com.example.junjin.ui.main.activity.MainActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.registerBt.setOnClickListener{
            launch(tryBlock = {
                val username = binding.nameText0.text.toString()
                val password = binding.passwordText0.text.toString()
                val registerResult = AccountApi.register(username,password)
                if (registerResult.code == 200) {
                    Toast.makeText(this@RegisterActivity, "注册成功", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@RegisterActivity, "用户名已存在", Toast.LENGTH_SHORT).show()
                }
            }, catchBlock = {
                Toast.makeText(this@RegisterActivity, "异常，请重试", Toast.LENGTH_SHORT).show()
            }, )
        }

        binding.goToLogin.setOnClickListener{
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}