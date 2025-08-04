package com.example.sunasterisk

import com.example.android_template.R
import com.example.sunasterisk.base.BaseActivity
import com.example.sunasterisk.base.BasePresenter

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
