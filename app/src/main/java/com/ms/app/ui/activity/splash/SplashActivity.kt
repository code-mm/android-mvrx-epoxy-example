package com.ms.app.ui.activity.splash

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import com.ms.app.R
import com.ms.app.ui.activity.main.MainActivity
import com.ms.app.ui.base.BaseAppCompatActivity
import com.ms.app.ui.base.StatusBarUtil
import com.ms.app.utils.permission.PermisionCallBack
import com.ms.app.utils.permission.PermissionClient
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseAppCompatActivity() {


    override fun layout(): Int {
        return R.layout.activity_splash;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        lauch()
    }


    override fun onStart() {
        super.onStart()
        lauch()
    }


    private fun lauch() {

        PermissionClient.getPermission().request(
            this, object : PermisionCallBack {
                override fun onSuccess(vararg per: String?) {

                    GlobalScope.launch(IO) {
                        SystemClock.sleep(3000)
                        GlobalScope.launch(Main) {
                            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                            finish()
                        }
                    }
                }

                override fun onFailure(vararg per: String?) {
                    val builder = AlertDialog.Builder(this@SplashActivity)
                    builder.setMessage("是否退出")
                    builder.setPositiveButton(
                        "确定"
                    ) { dialog, which -> System.exit(0) }
                    builder.setNegativeButton(
                        "取消"
                    ) { dialog, which -> lauch() }

                    builder.create().show()
                }
            },
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
        )


    }

    override fun setStatusBar() {
        StatusBarUtil.setTransparent(this)
        StatusBarUtil.setLightMode(this)
    }

    override fun isFullScreen(): Boolean {
        return true
    }
}