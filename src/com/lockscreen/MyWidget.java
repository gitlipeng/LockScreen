package com.lockscreen;

import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;
import android.widget.RemoteViews;

import com.lockscreen.LockScreen.Controller;

public class MyWidget extends AppWidgetProvider{
	private static final String broadcastString = "com.lockscreen.MyWidget";
	DevicePolicyManager mDPM;
    ComponentName mDeviceAdminSample;
	WindowManager				mWM;		// WindowManager
	WindowManager.LayoutParams	mWMParams;	// WindowManager参数
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		Log.i(broadcastString, "--------------onDeleted----------------");
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		Log.i(broadcastString, "--------------onDisabled----------------");
	}

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Log.i(broadcastString, "--------------onEnabled----------------");
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.i(broadcastString, "--------------onReceive----------------");
		if(broadcastString.equals(intent.getAction())){
			RemoteViews remotesViews = new RemoteViews(context.getPackageName(), R.layout.widget);
			remotesViews.setTextViewText(R.id.btn, "李鹏");
			
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			ComponentName componentName = new ComponentName(context, MyWidget.class);
			
			//manager.updateAppWidget(componentName, remotesViews);
			closeScreen(context);
		}
		super.onReceive(context, intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.i(broadcastString, "--------------onUpdate----------------");
		Intent i = new Intent();
		i.setAction(broadcastString);
		PendingIntent intent = PendingIntent.getBroadcast(context, 0, i, 0);
		
		   RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);  
	       remoteViews.setOnClickPendingIntent(R.id.btn, intent);  
	          
	       // 更新所有的appWidget   
	       appWidgetManager.updateAppWidget(appWidgetIds, remoteViews); 
		
	}  
	
	public void closeScreen(Context c){
		mDPM = (DevicePolicyManager) c.getSystemService(Context.DEVICE_POLICY_SERVICE);
        //LockScreen 继承自 DeviceAdminReceiver
        mDeviceAdminSample = new ComponentName(c,
                        LockScreen.class);
        //得到当前设备管理器有没有激活
        boolean active = mDPM.isAdminActive(mDeviceAdminSample);
        if (!active) { 
                //如果没有激活的话，就去提示用户激活（第一次运行程序时）
       	 	Intent i = new Intent(c,Controller.class); 
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				c.startActivity(i);
        }else{
                //如果已经激活的话，就执行立即锁屏
                mDPM.lockNow();
        }
	}

	
	
	
}
