package com.ms.app.ui.base

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.BuildConfig
import com.airbnb.mvrx.MvRxState
import com.ms.app.ui.datamodel.UiDataModel


abstract class BaseViewModel<S : MvRxState>(initialState: S) : BaseMvRxViewModel<S>(initialState,debugMode = BuildConfig.DEBUG){

}


