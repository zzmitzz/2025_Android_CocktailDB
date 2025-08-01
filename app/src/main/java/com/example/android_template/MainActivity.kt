package com.example.android_template

import android.os.Bundle
import com.example.android_template.base.BaseActivity
import com.example.android_template.base.BasePresenter

class MainActivity : BaseActivity<BasePresenter<*>>() {
    
    override fun getLayoutId(): Int = R.layout.activity_main
    
    override fun initializePresenter() {
        // Initialize your presenter here
        // Example: presenter = MainPresenter()
        // presenter?.attachView(this)
    }
    
    override fun initializeViews() {
        // Initialize your views here
        // Example: findViewById<Button>(R.id.btn_example).setOnClickListener { ... }
    }
    
    override fun setupObservers() {
        // Setup any observers here
    }
}
