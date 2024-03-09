package com.example.junjin.ui.my.activity

import MyInfoViewModel
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.junjin.base.ui.BaseActivity
import com.example.junjin.databinding.ActivityMyInfoEditBinding
import com.example.junjin.model.network.api.AccountApi
import com.example.junjin.ui.home.fragment.ChangeInfoDialogFragment
import com.example.junjin.ui.login.activity.LoginActivity
import com.example.junjin.util.KVUtil
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class MyInfoEditActivity(private val viewModel: MyInfoViewModel) : BaseActivity<ActivityMyInfoEditBinding>() {

    constructor() : this(MyInfoViewModel())

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            val selectedFileUri: Uri? = data?.data
            // 处理选定的文件URI
            if (selectedFileUri != null) {
                // 执行上传操作
                uploadFile(selectedFileUri)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.changeAvatar.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        val dialog1 = ChangeInfoDialogFragment(viewModel,1)
        binding.changeName.setOnClickListener{
            dialog1.show(supportFragmentManager,"ChangeNameDialogFragment")
        }

        val dialog2 = ChangeInfoDialogFragment(viewModel,2)
        binding.changePassword.setOnClickListener{
            dialog2.show(supportFragmentManager,"ChangePasswordDialogFragment")
        }

        binding.logOut.setOnClickListener{
            // 清除存储的 Token 信息
            KVUtil.remove("token")
            KVUtil.remove("userId")
            KVUtil.remove("userName")
            // 返回到登录页面
            val intent = Intent(this@MyInfoEditActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun uploadFile(fileUri: Uri) {
        val file = File(getRealPathFromURI(fileUri))
        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        // 调用服务器端的上传方法
        lifecycleScope.launch(){
            try {
                val response = AccountApi.changeAvatar(body)
                Log.d("changeAvatar","修改头像成功！")
                // 处理服务器响应
            } catch (e: Exception) {
                // 处理异常情况
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): String? {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        val idx = cursor?.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
        val path = idx?.let { cursor?.getString(it) }
        cursor?.close()
        return path
    }
}