package com.ms.app.ui.base

import android.view.View
import com.ms.app.utils.md5.MD5Utils

import java.util.*

open class BaseRepository {
    fun generateId(): String {
        return MD5Utils.md5(UUID.randomUUID().toString() + System.currentTimeMillis());
    }
}