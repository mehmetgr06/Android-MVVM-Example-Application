package com.example.phonehelper.app.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, observer: (T) -> Unit) {
    liveData?.observe(this, Observer(observer))
}

fun <T> LifecycleOwner.eventObserve(liveData: LiveData<Event<T>>?, observer: (T) -> Unit) {
    liveData?.observe(this, EventObserver(observer))
}