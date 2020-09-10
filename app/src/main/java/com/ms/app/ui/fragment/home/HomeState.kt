package com.ms.app.ui.fragment.home

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.ms.app.ui.datamodel.DataModel

data class HomeState(val datas: Async<List<DataModel>> = Uninitialized) : MvRxState
