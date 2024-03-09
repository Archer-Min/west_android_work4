package com.example.junjin.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.junjin.R

class DividerView : View {
    private var dividerColor: Int = 0
    private var paint: Paint = Paint()

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        dividerColor = ContextCompat.getColor(context, R.color.light_gray) // 默认为灰色
        paint.apply {
            color = dividerColor
            strokeWidth = resources.getDimensionPixelSize(R.dimen.divider_height).toFloat()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(0f, 0f, width.toFloat(), 0f, paint)
    }
}
