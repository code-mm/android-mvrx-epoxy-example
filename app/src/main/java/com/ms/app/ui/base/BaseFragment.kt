package com.ms.app.ui.base

import android.os.Bundle
import android.os.Parcelable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.MvRx
import com.airbnb.mvrx.MvRxState
import com.bdlbsc.doper.utils.thread.ThreadPoolUtils.runOnMainThread
import com.bdlbsc.doper.utils.toast.ToastUtils
import com.ms.app.R
import com.ms.app.itemMessage

import com.ms.app.ui.datamodel.DataModel
import com.ms.app.ui.datamodel.UiDataModel
import com.ms.view.dialog.loading.progress.UIProgressDialog
import kotlinx.android.synthetic.main.common_epoxy.*

abstract class BaseFragment : BaseMvRxFragment() {

    protected val epoxyController by lazy { epoxyController() }

    var _View: View? = null

    protected var baseDialog: UIProgressDialog? = null

    protected var _EpoxyRecyclerView: EpoxyRecyclerView? = null

    protected var _ToolBarTextViewTitle: TextView? = null;


    // 加载中
    private var statusRelativeLayoutLoading: RelativeLayout? = null

    // 空
    private var statusRelativeLayoutEmpty: RelativeLayout? = null

    // 错误
    private var statusRelativeLayoutError: RelativeLayout? = null

    // 无网络
    private var statusRelativeLayoutNotNetwork: RelativeLayout? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        baseDialog = (activity as BaseAppCompatActivity?)!!.baseDialog
        if (getLayoutId() != 0) {
            // 实例化View
            this._View = inflater.inflate(getLayoutId(), container, false)
            _EpoxyRecyclerView = _View!!.findViewById(R.id._EpoxyRecyclerView);
            _ToolBarTextViewTitle = _View!!.findViewById(R.id._ToolBarTextViewTitle);
            try {
                statusRelativeLayoutLoading = findViewById(R.id.statusRelativeLayoutLoading)
                statusRelativeLayoutEmpty = findViewById(R.id.statusRelativeLayoutEmpty)
                statusRelativeLayoutError = findViewById(R.id.statusRelativeLayoutError)
                statusRelativeLayoutNotNetwork = findViewById(R.id.statusRelativeLayoutNotNetwork)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        // 返回视图
        return _View
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        epoxyController.onRestoreInstanceState(savedInstanceState)
    }

    fun <T> findViewById(viewID: Int): T {
        return _View!!.findViewById<View>(viewID) as T
    }


    fun showToast(text: String?) {
        runOnMainThread(Runnable { ToastUtils.show(text) })
    }

    protected var lengthfilter20 = InputFilter { source, start, end, dest, dstart, dend ->
        val dValue = dest.toString()
        if (dValue != null) {
            if (dValue.length > 20) {
                return@InputFilter dValue.substring(0, 20)
            }
        }
        null
    }
    protected var lengthfilter11 = InputFilter { source, start, end, dest, dstart, dend ->
        val dValue = dest.toString()
        if (dValue != null) {
            if (dValue.length > 11) {
                return@InputFilter dValue.substring(0, 11)
            }
        }
        null
    }
    protected var lengthfilter18 = InputFilter { source, start, end, dest, dstart, dend ->
        val dValue = dest.toString()
        if (dValue != null) {
            if (dValue.length > 18) {
                return@InputFilter dValue.substring(0, 18)
            }
        }
        null
    }

    fun showDialog() {
        runOnMainThread(Runnable {
            if (!baseDialog!!.isShowing) {
                baseDialog!!.show()
            }
        })
    }

    fun hideDialog() {
        runOnMainThread(Runnable {
            if (baseDialog!!.isShowing) {
                baseDialog!!.hide()
            }
        })
    }

    protected var userNameAndPasswordInputFilter = arrayOf(lengthfilter20, LengthFilter(20))
    protected var phoneNumberInputFilter = arrayOf(lengthfilter11, LengthFilter(11))
    protected var iDcardInputFilter = arrayOf(lengthfilter18, LengthFilter(18))

    /**
     * Provide the EpoxyController to use when building models for this Fragment.
     * Basic usages can simply use [simpleController]
     */
    abstract fun epoxyController(): MvRxEpoxyController

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        epoxyController.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        epoxyController.cancelPendingModelBuild()
        super.onDestroyView()
    }

    protected fun navigateTo(@IdRes actionId: Int, arg: Parcelable? = null) {
        /**
         * If we put a parcelable arg in [MvRx.KEY_ARG] then MvRx will attempt to call a secondary
         * constructor on any MvRxState objects and pass in this arg directly.
         * @see [com.airbnb.mvrx.sample.features.dadjoke.DadJokeDetailState]
         */
        val bundle = arg?.let { Bundle().apply { putParcelable(MvRx.KEY_ARG, it) } }
        findNavController().navigate(actionId, bundle)
    }

    protected open fun getLayoutId(): Int {
        return R.layout.common_epoxy
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (_EpoxyRecyclerView != null) {
            _EpoxyRecyclerView!!.setController(epoxyController)
        }
    }


    open fun hideAllStatus() {
        if (statusRelativeLayoutLoading != null) {
            statusRelativeLayoutLoading!!.visibility = View.GONE
        }
        if (statusRelativeLayoutEmpty != null) {
            statusRelativeLayoutEmpty!!.visibility = View.GONE
        }
        if (statusRelativeLayoutError != null) {
            statusRelativeLayoutError!!.visibility = View.GONE
        }
        if (statusRelativeLayoutNotNetwork != null) {
            statusRelativeLayoutNotNetwork!!.visibility = View.GONE
        }
    }

    open fun showLoading() {
        hideAllStatus()
        if (statusRelativeLayoutLoading != null) {
            statusRelativeLayoutLoading!!.visibility = View.VISIBLE
        }
    }

    open fun showNotNetwork() {
        hideAllStatus()
        if (statusRelativeLayoutNotNetwork != null) {
            statusRelativeLayoutNotNetwork!!.visibility = View.VISIBLE
        }
    }

    open fun showEmpty() {
        hideAllStatus()
        if (statusRelativeLayoutEmpty != null) {
            statusRelativeLayoutEmpty!!.visibility = View.VISIBLE
        }
    }

    open fun showError() {
        hideAllStatus()
        if (statusRelativeLayoutError != null) {
            statusRelativeLayoutError!!.visibility = View.VISIBLE
        }
    }


    fun <T : MvRxState> EpoxyController.buildEpoxyRows(
        viewModel: BaseViewModel<T>,
        data: DataModel
    ) {

        if (!data.datas.isEmpty()) {
            data.datas.forEach {

                when (it) {
                    is UiDataModel.MessageModel ->
                        itemMessage {
                            id(it.id)
                            avatar(it.avatar)
                            title(it.title)
                            date(it.date)
                            content(it.content)
                            onActionItemClicked(object : View.OnClickListener {
                                override fun onClick(p0: View?) {
                                    showToast("消息ID : ${it.id}")
                                }
                            })
                        }
                }
            }
        }
    }

    open fun setToolBarBackground(color: Int) {
        _ToolBar.setBackgroundColor(color)
    }

    open fun setToolBarTitle(text: String) {
        if (_ToolBarTextViewTitle != null) {
            _ToolBarTextViewTitle!!.text = text;
        }
    }

    open fun setToolBarTitleColor(color: Int) {
        if (_ToolBarTextViewTitle != null) {
            _ToolBarTextViewTitle!!.setTextColor(color)
        }
    }
}
