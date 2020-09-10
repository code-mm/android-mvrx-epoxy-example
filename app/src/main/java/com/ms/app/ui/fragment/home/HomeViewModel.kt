package com.ms.app.ui.fragment.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.ms.app.ui.base.BaseViewModel
import com.ms.app.ui.datamodel.DataModel
import com.ms.app.ui.datamodel.UiDataModel
import com.ms.app.ui.di.AssistedViewModelFactory
import com.ms.app.ui.di.DaggerMvRxViewModelFactory
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject


class HomeViewModel @AssistedInject constructor(
    @Assisted state: HomeState,
    private val repo: HomeRepository
) : BaseViewModel<HomeState>(state) {

    init {
        repo.getMessage().execute {
            copy(
                datas = it
            )
        }
    }

    @AssistedInject.Factory
    interface Factory : AssistedViewModelFactory<HomeViewModel, HomeState> {
        override fun create(state: HomeState): HomeViewModel
    }

    companion object :
        DaggerMvRxViewModelFactory<HomeViewModel, HomeState>(HomeViewModel::class.java)

}