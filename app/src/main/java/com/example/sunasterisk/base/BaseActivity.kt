package com.example.sunasterisk.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P : BasePresenter<*>> :
    AppCompatActivity(),
    BaseView {
    protected var presenter: P? = null
    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initializePresenter()
        initializeViews()
        setupObservers()
    }

    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initializePresenter()

    protected abstract fun initializeViews()

    protected open fun setupObservers() {}

    override fun showLoading(message: String?) {
        if (isActive()) {
            loadingDialog = LoadingDialog.create(message)
            loadingDialog?.show(supportFragmentManager, "loading_dialog")
        }
    }

    override fun hideLoading() {
        loadingDialog?.dismissAllowingStateLoss()
        loadingDialog = null
    }

    override fun showError(message: String) {
        if (isActive()) {
            showToast(message)
        }
    }

    override fun showSuccess(message: String) {
        if (isActive()) {
            showToast(message)
        }
    }

    override fun showInfo(message: String) {
        if (isActive()) {
            showToast(message)
        }
    }

    override fun isActive(): Boolean = !isFinishing && !isDestroyed

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun getContext(): Context = this

    protected fun navigateTo(
        activityClass: Class<*>,
        extras: Bundle? = null,
    ) {
        val intent = android.content.Intent(this, activityClass)
        extras?.let { intent.putExtras(it) }
        startActivity(intent)
    }

    protected fun navigateToAndFinish(
        activityClass: Class<*>,
        extras: Bundle? = null,
    ) {
        navigateTo(activityClass, extras)
        finish()
    }
}
