package com.giz.android.yfui.dialog

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.giz.android.yfui.R
import com.giz.android.yfui.databinding.ActivityBottomDialogFragmentBinding

/**
 * Description of the file
 * Created by GizFei on 2021/1/31
 */
class BottomDialogFragmentActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityBottomDialogFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_dialog_fragment)

        initView()
    }

    private fun initView() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        supportActionBar?.hide()
        window?.statusBarColor = Color.TRANSPARENT

        mBinding.ivLogin.setOnClickListener {
            showBottomDialogFragment()
        }
    }

    private fun showBottomDialogFragment() {
        BottomDialogFragment().apply {
            setContentView(R.layout.activity_bottom_dialog_fragment)
        }.show(supportFragmentManager, null)
    }
}