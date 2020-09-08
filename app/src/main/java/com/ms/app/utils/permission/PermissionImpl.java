package com.ms.app.utils.permission;


import androidx.fragment.app.FragmentActivity;

public class PermissionImpl implements IPermission {

    @Override
    public void request(FragmentActivity fragmentActivity, final PermisionCallBack iCallBack, String... strings) {

        PermissionUtilsImpl.requestPermission(fragmentActivity, new PermissionUtilsImpl.CallBack() {
            @Override
            public void success() {
                if (iCallBack != null) {
                    iCallBack.onSuccess("");
                }
            }

            @Override
            public void filure() {
                if (iCallBack != null) {
                    iCallBack.onFailure("");
                }
            }
        }, strings);

    }
}
