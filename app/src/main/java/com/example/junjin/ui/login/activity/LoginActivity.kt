package com.example.junjin.ui.login.activity

import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.junjin.base.ui.BaseActivity
import com.example.junjin.databinding.ActivityLoginBinding
import com.example.junjin.model.network.api.AccountApi
import com.example.junjin.ui.main.activity.MainActivity
import com.example.junjin.ui.register.activity.RegisterActivity
import com.example.junjin.util.KVUtil
import java.security.AccessController.getContext

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.loginBt.setOnClickListener{
            launch(tryBlock = {
                val username = binding.nameText.text.toString()
                val password = binding.passwordText.text.toString()
                val loginResult = AccountApi.login(username, password)
                if (loginResult.code == 200) {
                    loginResult.data?.let {
                        KVUtil.put("token", it.token)
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, loginResult.message, Toast.LENGTH_SHORT).show()
                }
            }, catchBlock = {
                Toast.makeText(this@LoginActivity, "登录异常，请重试", Toast.LENGTH_SHORT).show()
            }, )
        }

        binding.goToRegister.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}