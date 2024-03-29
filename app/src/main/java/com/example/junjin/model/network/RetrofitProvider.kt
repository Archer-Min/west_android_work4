package com.example.junjin.model.network

import android.util.Log
import androidx.viewbinding.BuildConfig
import com.example.junjin.MyApplication
import com.example.junjin.util.KVUtil
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

object RetrofitProvider {
    private val retrofits = HashMap<String, Retrofit>()

    private fun createRetrofit(baseUrl: String, isJson: Boolean, key: String): Retrofit {
        val builder = Retrofit.Builder().baseUrl(baseUrl).client(getClient().build())
        if (isJson) builder.addConverterFactory(GsonConverterFactory.create())

        val res = builder.build()
        retrofits[key] = res
        return res
    }

    fun getRetrofit(isJson: Boolean = true): Retrofit {
        val baseUrl = BASE_URL
        val key = "$baseUrl-$isJson"
        return retrofits.getOrElse(key) {
            createRetrofit(baseUrl, isJson, key)
        }
    }


    class HeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            //在请求头里添加token的拦截器处理
            val token: String = KVUtil.get("token", "")
            Log.d("token",token)
            var request: Request = chain.request()
            val response = if (token == "") {
                chain.proceed(request)
            } else {
                request = request.newBuilder().header("Authorization", "Bearer $token").build()
                chain.proceed(request)
            }
            //缓存设置
            val cacheControl: String = request.cacheControl.toString()
            return response.newBuilder()
                .removeHeader("Pragma") //清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                .header("Cache-Control", cacheControl).build()
        }
    }

    private val logInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    private fun getClient(): OkHttpClient.Builder {
        val httpCacheDirectory = File(MyApplication.instance.cacheDir, "HttpCache")
        val cacheSize = 30 * 1024 * 1024
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())
        return OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor(HeaderInterceptor()).addInterceptor(logInterceptor)
            .cache(cache)
    }

    private const val BASE_URL = "http://8.134.219.139:6666/"
}