package com.example.hcompanion;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SecondScreen extends Activity implements OnClickListener{

	Button miss,visit,check,eexit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_screen); 

		initialize();
		
		miss.setOnClickListener(this);
		visit.setOnClickListener(this);
		check.setOnClickListener(this);
		eexit.setOnClickListener(this);
	}

	private void initialize() {
		// TODO Auto-generated method stub
		miss = (Button) findViewById(R.id.bmiss); 
		visit = (Button) findViewById(R.id.bvisit);
		check = (Button) findViewById(R.id.bcheck); 
		eexit = (Button) findViewById(R.id.bABCexit);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bmiss:
			Intent openThirdScreen = new Intent("com.example.hcompanion.THIRDSCREEN");
			startActivity(openThirdScreen);
			break;
		case R.id.bvisit:
			Intent openi = new Intent("com.example.hcompanion.FOURTHSCREEN");
			startActivity(openi);  
			break;
		case R.id.bcheck:
			Intent openFifthScreen = new Intent("com.example.hcompanion.FIFTHSCREEN"); 
			startActivity(openFifthScreen);
			break;
		case R.id.bABCexit:
			finish();
			break; 
		}
		
	}
	

}
