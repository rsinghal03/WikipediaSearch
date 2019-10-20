package com.example.wikipediasearch.ui

import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable

interface BaseContract {


    interface BaseView {
        fun showLoading()
        fun hideLoading()
    }

    interface BasePresenter<T : BaseView> {
        var view: T?
        var compositeDisposable: CompositeDisposable

        @CallSuper
        fun attachView(view: T?) {
            this.view = view
        }

        @CallSuper
        fun detachView() {
            this.view = null
            compositeDisposable.dispose()
        }
    }
}