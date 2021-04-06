package com.giz.android.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.giz.android.practice.common.DataBindingBaseActivity
import com.giz.android.practice.databinding.ActivityUiPracticeMainBinding
import com.giz.android.practice.hencoder.customview.c1_DrawBasics.DrawBasicsActivity
import kotlin.reflect.KClass

class UiPracticeMainActivity : DataBindingBaseActivity<ActivityUiPracticeMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_ui_practice_main

    override fun initView() {

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_draw_basics -> DrawBasicsActivity::class.navigation()
        }
    }

    private fun KClass<out AppCompatActivity>.navigation() {
        startActivity(Intent(this@UiPracticeMainActivity, this.java))
    }

}