package com.giz.android.practice.hencoder.hencoderpracticedraw3.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class Practice08SetTextSkewXView : View {
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var text = "Hello HenCoder"

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText(text, 50f, 100f, paint)
    }

    init {
        paint.textSize = 60f

        // 使用 Paint.setTextSkewX() 来让文字倾斜
//        paint.textSkewX = 0.5f  // 正数左斜
        paint.textSkewX = -0.5f     // 负数右斜
    }
}