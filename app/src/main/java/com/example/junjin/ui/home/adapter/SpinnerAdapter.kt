package com.example.junjin.ui.home.adapter

import android.R
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.junjin.ui.home.fragment.HomeFragment

class SpinnerAdapter(
    private val context: Context,
    private val spinner: Spinner,
    private val options: Array<String>,
    private val onItemSelectedListener: ((String) -> Unit)? = null
) {
    init {
        // 创建适配器
        val adapter = ArrayAdapter(context, R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // 设置选择监听器
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                onItemSelectedListener?.invoke(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // 当没有选中项时的处理逻辑
            }
        }
    }
}