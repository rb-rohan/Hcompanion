package com.example.hcompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class InitialScreen extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		 //fullscreen
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.initial_screen);
		
		Thread timer = new Thread() {
				public void run(){
				try{	
					sleep(1400);       
				}
				catch(InterruptedException e){
				e.printStackTrace();
				}
				finally{
					Intent openSecondScreen = new Intent("com.example.hcompanion.SECONDSCREEN");
					startActivity(openSecondScreen);
				}
				}
				};
				timer.start();
	}

	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
}
