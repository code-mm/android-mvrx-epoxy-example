package com.ms.app.ui.fragment.my

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.ms.app.ui.base.BaseViewModel
import com.ms.app.ui.datamodel.UiDataModel
import com.ms.app.ui.di.AssistedViewModelFactory
import com.ms.app.ui.di.DaggerMvRxViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class MyViewModel @AssistedInject constructor(
    @Assisted state: MyState,
    private val repo: MyRepository
) : BaseViewModel<MyState>(state) {

    init {
        sayHello()
    }

    fun sayHello() {
        repo.sayHello().execute { copy(message = it) }
    }

    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<MyViewModel, MyState> {
        override fun create(state: MyState): MyViewModel
    }

    companion object : DaggerMvRxViewModelFactory<MyViewModel, MyState>(MyViewModel::class.java)


}