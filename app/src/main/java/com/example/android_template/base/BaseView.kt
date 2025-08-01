package com.example.android_template.base

interface BaseView {


    fun showLoading(message: String? = null)


    fun hideLoading()


    fun showError(message: String)

    fun showSuccess(message: String)


    fun showInfo(message: String)


    fun isActive(): Boolean
}
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