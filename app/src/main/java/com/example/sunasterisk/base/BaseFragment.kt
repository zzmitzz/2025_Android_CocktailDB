package com.example.sunasterisk.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : BasePresenter<*>> :
    Fragment(),
    BaseView {
    protected var presenter: P? = null
    private var loadingDialog: LoadingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(getLayoutId(), container, false)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initializePresenter()
        initializeViews()
        setupObservers()
    }

    override fun onDestroyView() {
        presenter?.detachView()
        super.onDestroyView()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initializePresenter()

    protected abstract fun initializeViews()

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

    protected fun showToast(message: String) {
        context?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getContext(): Context? = context

    protected fun navigateToFragment(
        fragment: Fragment,
        addToBackStack: Boolean = true,
        tag: String? = null,
    ) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(getContainerId(), fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commit()
    }

    protected abstract fun getContainerId(): Int

    protected fun goBack() {
        if (parentFragmentManager.backStackEntryCount > 0) {
            parentFragmentManager.popBackStack()
        }
    }
}
