package com.example.android_template.base

/**
 * Base presenter interface for MVP architecture
 * Defines common presenter lifecycle methods
 */
interface BasePresenter<V : BaseView> {
    
    /**
     * Attach view to presenter
     * @param view The view to attach
     */
    fun attachView(view: V)
    
    /**
     * Detach view from presenter
     */
    fun detachView()
    
    /**
     * Check if view is attached
     * @return true if view is attached, false otherwise
     */
    fun isViewAttached(): Boolean
    
    /**
     * Get the attached view
     * @return The attached view or null if not attached
     */
    fun getView(): V?
}

/**
 * Abstract base presenter implementation
 * Provides common presenter functionality
 */
abstract class BasePresenterImpl<V : BaseView> : BasePresenter<V> {
    
    private var view: V? = null
    
    override fun attachView(view: V) {
        this.view = view
        onViewAttached()
    }
    
    override fun detachView() {
        onViewDetached()
        this.view = null
    }
    
    override fun isViewAttached(): Boolean = view != null
    
    override fun getView(): V? = view
    
    /**
     * Called when view is attached
     * Override this method to perform initialization
     */
    protected open fun onViewAttached() {}
    
    /**
     * Called when view is detached
     * Override this method to perform cleanup
     */
    protected open fun onViewDetached() {}
    
    /**
     * Execute action if view is attached
     * @param action The action to execute
     */
    protected fun executeIfViewAttached(action: (V) -> Unit) {
        view?.let { action(it) }
    }
} 