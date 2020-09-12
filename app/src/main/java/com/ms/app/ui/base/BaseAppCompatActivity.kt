package com.ms.app.ui.base

import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.bdlbsc.doper.utils.thread.ThreadPoolUtils.runOnMainThread
import com.bdlbsc.doper.utils.toast.ToastUtils.show
import com.ms.view.dialog.loading.progress.UIProgressDialog
import com.ms.view.dialog.loading.progress.UIProgressDialog.MaterialBuilder

open class BaseAppCompatActivity :
    AppCompatActivity() {
    var baseDialog: UIProgressDialog? = null
    protected var fragmentManager: FragmentManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentManager = supportFragmentManager
        baseDialog = MaterialBuilder(this).create()
        if (isFullScreen()) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            val decorView = window.decorView
            val uiOption = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            decorView.systemUiVisibility = uiOption
            decorView.setOnSystemUiVisibilityChangeListener { visibility ->
                if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                    decorView.systemUiVisibility = uiOption
                }
            }
        }
        super.onCreate(savedInstanceState)
        if (layout() != 0) {
            setContentView(layout())
            // 设置沉浸式
            setStatusBar()
            // 初始化控件
            initView()
        }
    }

    protected open fun initView() {}
    protected open fun layout(): Int {
        return 0
    }

    protected open fun isFullScreen(): Boolean {
        return false
    }


    protected open fun setStatusBar() {
        //这里做了两件事情，1.使状态栏透明并使contentView填充到状态栏 2.预留出状态栏的位置，防止界面上的控件离顶部靠的太近。这样就可以实现开头说的第二种情况的沉浸式状态栏了
        StatusBarUtil.setTransparent(this)
    }

    fun showDialog() {
        runOnMainThread {
            if (!baseDialog!!.isShowing) {
                baseDialog!!.show()
            }
        }
    }

    fun hideDialog() {
        runOnMainThread {
            if (baseDialog!!.isShowing) {
                baseDialog!!.hide()
            }
        }
    }

    fun <T> findView(viewID: Int): T {
        return findViewById<View>(viewID) as T
    }

    fun showToast(text: String?) {
        runOnMainThread { show(text) }
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
    protected var userNameAndPasswordInputFilter = arrayOf(lengthfilter20, LengthFilter(20))
    protected var phoneNumberInputFilter = arrayOf(lengthfilter11, LengthFilter(11))
    protected var iDcardInputFilter = arrayOf(lengthfilter18, LengthFilter(18))
}