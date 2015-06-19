package com.lockscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lockscreen.LockScreen.Controller;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void sendRequset(View v){
		Intent i = new Intent(this,Controller.class);
		startActivity(i);
	}

}
