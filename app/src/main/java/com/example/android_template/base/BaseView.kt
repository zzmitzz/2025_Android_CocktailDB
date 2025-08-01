package com.example.android_template.base

/**
 * Base view interface for MVP architecture
 * Defines common view methods and loading states
 */
interface BaseView {
    
    /**
     * Show loading dialog
     * @param message Optional message to display
     */
    fun showLoading(message: String? = null)
    
    /**
     * Hide loading dialog
     */
    fun hideLoading()
    
    /**
     * Show error message
     * @param message Error message to display
     */
    fun showError(message: String)
    
    /**
     * Show success message
     * @param message Success message to display
     */
    fun showSuccess(message: String)
    
    /**
     * Show info message
     * @param message Info message to display
     */
    fun showInfo(message: String)
    
    /**
     * Check if view is active (not destroyed)
     * @return true if view is active, false otherwise
     */
    fun isActive(): Boolean
}

/**
 * Base view implementation with common functionality
 */
abstract class BaseViewImpl : BaseView {
    
    private var loadingDialog: LoadingDialog? = null
    
    override fun showLoading(message: String?) {
        if (isActive()) {
            loadingDialog = LoadingDialog.create(message)
            loadingDialog?.show(getFragmentManager(), "loading_dialog")
        }
    }
    
    override fun hideLoading() {
        loadingDialog?.dismissAllowingStateLoss()
        loadingDialog = null
    }
    
    override fun showError(message: String) {
        if (isActive()) {
            // Implement your preferred way to show error messages
            // This could be a Toast, Snackbar, or custom dialog
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
    
    /**
     * Get fragment manager for dialog operations
     * @return FragmentManager instance
     */
    protected abstract fun getFragmentManager(): androidx.fragment.app.FragmentManager
    
    /**
     * Show toast message
     * @param message Message to display
     */
    protected open fun showToast(message: String) {
        // Default implementation - override in specific views
    }
} 