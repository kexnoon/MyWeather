package ru.alexeypopov.myweather.common

import androidx.lifecycle.MutableLiveData

class UIModelLiveData<T>: MutableLiveData<UIModel<T>>() {

    fun postLoading() {
        postValue(UIModel.loading())
    }

    fun postSuccess(data: T) {
        postValue(UIModel.success(data))
    }

    fun postError(message: String?) {
        postValue(UIModel.error(message))
    }

}