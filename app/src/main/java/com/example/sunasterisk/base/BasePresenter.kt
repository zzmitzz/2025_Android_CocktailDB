package com.example.sunasterisk.base

interface BasePresenter<V : BaseView> {
    fun attachView(view: V)

    fun detachView()

    fun isViewAttached(): Boolean

    fun getView(): V?
}

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

    protected open fun onViewAttached() {}

    protected open fun onViewDetached() {}

    protected fun executeIfViewAttached(action: (V) -> Unit) {
        view?.let { action(it) }
    }
}
