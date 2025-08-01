package com.example.android_template.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Base fragment for MVP architecture
 * Provides common functionality for all fragments
 */
abstract class BaseFragment<P : BasePresenter<*>> : Fragment(), BaseView {
    
    protected var presenter: P? = null
    private var loadingDialog: LoadingDialog? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializePresenter()
        initializeViews()
        setupObservers()
    }
    
    override fun onDestroyView() {
        presenter?.detachView()
        super.onDestroyView()
    }
    
    /**
     * Get layout resource ID for this fragment
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
            loadingDialog?.show(childFragmentManager, "loading_dialog")
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
    
    override fun isActive(): Boolean = isAdded && !isDetached && !isRemoving
    
    /**
     * Show toast message
     * @param message Message to display
     */
    protected fun showToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }
    
    /**
     * Get context for this fragment
     * @return Context instance
     */
    override fun getContext(): Context? = context
    
    /**
     * Navigate to another fragment
     * @param fragment Fragment to navigate to
     * @param addToBackStack Whether to add to back stack
     * @param tag Optional tag for the fragment
     */
    protected fun navigateToFragment(
        fragment: Fragment,
        addToBackStack: Boolean = true,
        tag: String? = null
    ) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(getContainerId(), fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commit()
    }
    
    /**
     * Get container ID for fragment transactions
     * @return Container resource ID
     */
    protected abstract fun getContainerId(): Int
    
    /**
     * Go back to previous fragment
     */
    protected fun goBack() {
        if (parentFragmentManager.backStackEntryCount > 0) {
            parentFragmentManager.popBackStack()
        }
    }
} 