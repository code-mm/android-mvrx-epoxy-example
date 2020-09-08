package com.ms.app.ui.fragment.my

import android.os.Bundle
import com.airbnb.mvrx.*
import com.ms.app.my
import com.ms.app.ui.base.BaseFragment
import com.ms.app.ui.base.simpleController
import com.ms.app.ui.fragment.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyFragment : BaseFragment(), MvRxView {

    private val viewModel: HomeViewModel by fragmentViewModel()


    override fun epoxyController() = simpleController(viewModel) { state ->


        my {
            id("my_id")
        }

    }

    override fun invalidate() = withState(viewModel) { state ->

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

        setToolBarTitle("我的")
    }

}