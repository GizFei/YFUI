package com.giz.android.practice.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.giz.android.practice.R
import kotlin.math.max
import kotlin.math.min

/**
 * 分段着色视图
 *
 * Created by GizFei on 2021/4/19
 */
class ColorPaintingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // 默认宽高
    private val mDefaultWidth = 300.dp
    private val mDefaultHeight = 200.dp

    // 绘制区域宽高
    private var mAreaWidth = 0f
    private var mAreaHeight = 0f

    // 方块宽高、行列数
    private var mBlockWidth = 0f
    private var mBlockHeight = 0f
    private var mCols = 10
    private var mRows = 5

    // 水平、竖直间隙宽度
    private var mGapX = 0f
    private var mGapY = 0f

    // 偏移后的“零点”坐标。即绘制区域左上角在当前视图中的坐标
    private var mZeroX = 0f
    private var mZeroY = 0f

    // 二维颜色数组
    private var mColorArray = Array(0) { IntArray(0) }

    private val mBlockPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        context.obtainStyledAttributes(attrs, R.styleable.ColorPaintingView).apply {
            mCols = max(1, getInt(R.styleable.ColorPaintingView_cpv_cols, 10))
            mRows = max(1, getInt(R.styleable.ColorPaintingView_cpv_rows, 5))
            mGapX = getDimension(R.styleable.ColorPaintingView_cpv_gapX, 2.dp)
            mGapY = getDimension(R.styleable.ColorPaintingView_cpv_gapY, 2.dp)

            recycle()
        }

        updateColorArray()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            getMeasuredSize(mDefaultWidth.toInt(), widthMeasureSpec, false),
            getMeasuredSize(mDefaultHeight.toInt(), heightMeasureSpec, true)
        )
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        // 1、计算绘制宽高
        mAreaWidth = width.toFloat() - paddingLeft - paddingRight
        mAreaHeight = height.toFloat() - paddingTop - paddingBottom

        // 2、计算绘制区域“零点”坐标、block宽高
        mZeroX = paddingLeft.toFloat()
        mZeroY = paddingTop.toFloat()
        mBlockWidth = (mAreaWidth - mGapX * (mCols - 1)) / mCols
        mBlockHeight = (mAreaHeight - mGapY * (mRows - 1)) / mRows
    }

    override fun onDraw(canvas: Canvas) {
        // 根据每个方块的坐标绘制
        for (r in 0 until mRows) {
            for (c in 0 until mCols) {
                val left = (mBlockWidth + mGapX) * c + mZeroX
                val top = (mBlockHeight + mGapY) * r + mZeroY

                mBlockPaint.color = mColorArray.getOrNull(r)?.getOrNull(c) ?: Color.WHITE
                canvas.drawRect(left, top, left + mBlockWidth, top + mBlockHeight, mBlockPaint)
            }
        }
    }

    /**
     * 测量尺寸
     *
     * @param useMin 当规格为AT_MOST，取较小值，还是取较大值
     */
    private fun getMeasuredSize(size: Int, measureSpec: Int, useMin: Boolean): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        return when (specMode) {
            MeasureSpec.AT_MOST -> if (useMin) min(specSize, size) else max(specSize, size)
            MeasureSpec.EXACTLY -> specSize
            else -> size
        }
    }

    /**
     * 更新ColorArray
     */
    private fun updateColorArray() {
        mColorArray = Array(mRows) { IntArray(mCols) { Color.BLACK } }
    }

    private val Int.dp: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(), context.resources.displayMetrics)
}