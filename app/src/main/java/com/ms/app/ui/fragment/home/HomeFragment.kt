package com.ms.app.ui.fragment.home


import android.os.Bundle
import com.airbnb.mvrx.*
import com.ms.app.ui.base.BaseFragment
import com.ms.app.ui.base.simpleController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(), MvRxView {

    private val TAG = "HomeFragment"
    private val viewModel: HomeViewModel by fragmentViewModel()

    override fun epoxyController() = simpleController(viewModel) { state ->
        state.datas.invoke()!!.forEach {
            buildEpoxyRows(viewModel, it);
        }
    }

    override fun invalidate() {
        epoxyController.requestModelBuild()
        withState(viewModel) { state ->
            when (state.datas) {
                is Loading -> {
                    showLoading()
                }
                is Success -> {
                    hideAllStatus()
                }
                is Fail -> {
                    showError()
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setToolBarTitle("消息")
    }

}