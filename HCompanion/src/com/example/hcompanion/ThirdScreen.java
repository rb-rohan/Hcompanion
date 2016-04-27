package com.example.hcompanion;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdScreen extends Activity implements OnClickListener{

	EditText sqlreg, sqlshortinfo, sqldesc;
	TextView regtv, shorttv, desctv;
	Button post,exit,update;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_screen);
		
		initialize();
		
		post.setOnClickListener(this);
		exit.setOnClickListener(this);
		update.setOnClickListener(this);
		
	}
	private void initialize() {
		// TODO Auto-generated method stub
		
		sqlreg = (EditText) findViewById(R.id.etSQLregno); 
		sqlshortinfo = (EditText) findViewById(R.id.etSQLinfo);
		sqldesc = (EditText) findViewById(R.id.etSQLdesc);
		regtv = (TextView) findViewById(R.id.tvregno);
		shorttv = (TextView) findViewById(R.id.tvinfo);
		desctv = (TextView) findViewById(R.id.tvdesc);
		post = (Button) findViewById(R.id.bSQLpost);
		exit = (Button) findViewById(R.id.bSQLexit); 
		update = (Button) findViewById(R.id.bSQLupdate);
		
	} 
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bSQLpost:
			
			boolean didItWork = true;
			
			try{
			String sregno = sqlreg.getText().toString();
			String sinfo = sqlshortinfo.getText().toString();
			String sdesc = sqldesc.getText().toString();
			
			
			DatabaseStuff entry = new DatabaseStuff(ThirdScreen.this);
			entry.open();
			entry.createEntry(sregno, sinfo, sdesc);
			entry.close();
			}catch(Exception e){
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Error"); 
 				TextView tv = new TextView(this);
				tv.setText("Invalid Action");
				d.setContentView(tv);
			   	d.show(); 
			}
			finally{
				if(didItWork){
					Dialog d = new Dialog(this);
					d.setTitle("Details Saved");  
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv); 
					d.show();
				}
			}
			break;
		case R.id.bSQLupdate:
			boolean didWork = true;
			try{
			String regg = sqlreg.getText().toString();
			String inf = sqlshortinfo.getText().toString();
			String des = sqldesc.getText().toString();
			
			DatabaseStuff ted = new DatabaseStuff(this);
			ted.open();
			ted.updatentry(regg,inf,des);
			ted.close();
			}
			catch(Exception e){
				didWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Error");
				TextView tv = new TextView(this);
				tv.setText("Invalid Action");
				d.setContentView(tv);
				d.show(); 
			} 
			finally{
				if(didWork){
					Dialog d = new Dialog(this);
					d.setTitle("Updated");   
					TextView tv = new TextView(this);
					tv.setText("Success");  
					d.setContentView(tv);  
					d.show();
				}
			}
			break;
		case R.id.bSQLexit:
			this.finish();
			break;
		}
	}

}
