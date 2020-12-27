package com.yilmazvolkan.moviesapp.viewModels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class ReactiveViewModel : ViewModel() {
    val disposable = CompositeDisposable()

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }
}