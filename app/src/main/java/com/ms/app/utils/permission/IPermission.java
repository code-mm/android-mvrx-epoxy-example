package com.ms.app.utils.permission;

import androidx.fragment.app.FragmentActivity;

/// 权限
public interface IPermission  {

    void request(FragmentActivity activity, PermisionCallBack callBack, String... pers);

}