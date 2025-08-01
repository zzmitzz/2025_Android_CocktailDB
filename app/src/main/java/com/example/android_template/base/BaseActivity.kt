package com.example.android_template.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

/**
 * Base activity for MVP architecture
 * Provides common functionality for all activities
 */
abstract class BaseActivity<P : BasePresenter<*>> : AppCompatActivity(), BaseView {
    
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
    
    /**
     * Get layout resource ID for this activity
     * @return Layout resource ID
     */
    protected abstract fun getLayoutId(): Int
    
    /**
     * Initialize presenter
     * Override this method to create and attach presenter
     */
    protected abstract fun initializePresenter()
    
    /**
     * Initialize views
     * Override this method to setup views and click listeners
     */
    protected abstract fun initializeViews()
    
    /**
     * Setup observers
     * Override this method to setup any observers
     */
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
    
    /**
     * Show toast message
     * @param message Message to display
     */
    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    /**
     * Get context for this activity
     * @return Context instance
     */
    protected fun getContext(): Context = this
    
    /**
     * Navigate to another activity
     * @param activityClass Class of the activity to navigate to
     * @param extras Optional extras to pass
     */
    protected fun navigateTo(activityClass: Class<*>, extras: Bundle? = null) {
        val intent = android.content.Intent(this, activityClass)
        extras?.let { intent.putExtras(it) }
        startActivity(intent)
    }
    
    /**
     * Navigate to another activity and finish current
     * @param activityClass Class of the activity to navigate to
     * @param extras Optional extras to pass
     */
    protected fun navigateToAndFinish(activityClass: Class<*>, extras: Bundle? = null) {
        navigateTo(activityClass, extras)
        finish()
    }
} 