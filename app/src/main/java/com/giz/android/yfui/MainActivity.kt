package com.giz.android.yfui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.giz.android.yfui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initView()
    }

    private fun initView() {
        mBinding.onClickListener = this
    }

    override fun onClick(view: View) {
        try {
            val clz = Class.forName(view.tag as? String ?: "com.giz.android.MainActivity")
            startActivity(Intent(this, clz))
        } catch (e: Exception) {
            Toast.makeText(this, "跳转错误", Toast.LENGTH_SHORT).show()
        }
    }
}