package com.lockscreen;


import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LockScreen extends DeviceAdminReceiver {
    static final int RESULT_ENABLE = 1;

    public static class Controller extends Activity {
            ComponentName mDeviceAdminSample;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    mDeviceAdminSample = new ComponentName(Controller.this,
                                    LockScreen.class);
                    getAdmin();
            //        killMyself ，锁屏之后就立即kill掉我们的Activity，避免资源的浪费;
                   // android.os.Process.killProcess(android.os.Process.myPid()); 
            }

    public void getAdmin() {
        // Launch the activity to have the user enable our admin.
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                mDeviceAdminSample);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "欢迎您的使用！");
        startActivityForResult(intent, RESULT_ENABLE);
    }
            
    }
}