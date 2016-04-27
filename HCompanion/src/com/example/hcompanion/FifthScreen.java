package com.example.hcompanion;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FifthScreen extends Activity implements OnClickListener{
	
	EditText entryreg;
	TextView showcomm;
	Button getcomm,exitute,deletecomm;
	int n = 0,nn = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fifth_screen);
		initialize();
		
		getcomm.setOnClickListener(this);
		exitute.setOnClickListener(this);
		deletecomm.setOnClickListener(this);
	}
	private void initialize() { 
		// TODO Auto-generated method stub
			entryreg = (EditText) findViewById(R.id.etgetc);
			showcomm = (TextView) findViewById(R.id.tvSQLcomm); 
			getcomm = (Button) findViewById(R.id.bSQLgetc);
			exitute = (Button) findViewById(R.id.bSQLExitttt);
			deletecomm = (Button) findViewById(R.id.bdeletecomm); 
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSQLExitttt:
			this.finish();
			break;
		case R.id.bSQLgetc:
			boolean didWork = true;
			String s = entryreg.getText().toString();
			if(s == null){
				didWork = false;
				Dialog d = new Dialog(this);
				d.setTitle("Enter REGNO");
				TextView tv = new TextView(this);
				tv.setText("Invalid");
				d.setContentView(tv);
				d.show(); 
			}
				
			else
			{
			try{
				
				DatabaseStuff posty = new DatabaseStuff(this);
				posty.open(); 
				n = posty.getpostno(s);
				posty.close(); 
				DatabaseStuff2 posty2 = new DatabaseStuff2(this);
				posty2.open();
				String data = posty2.getcomm(n); 
				posty2.close();  
				showcomm.setText(data); 
			}catch(Exception e){
				didWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Error");
				TextView tv = new TextView(this);
				tv.setText("Error");
				d.setContentView(tv);
				d.show(); 
			}
			if(n == -1)
			{
				Dialog d = new Dialog(this);
				d.setTitle("Not Found");   
				TextView tv = new TextView(this);
				tv.setText("Invalid Data");   
				d.setContentView(tv);  
				d.show();
			}
			}
			break; 
		case R.id.bdeletecomm:
			boolean didWork1 = true;
			try{
				String sreg = entryreg.getText().toString();
				DatabaseStuff posty11 = new DatabaseStuff(this);
				posty11.open(); 
				nn = posty11.getpostnondelete(sreg); 
				posty11.close(); 
				DatabaseStuff2 posty21 = new DatabaseStuff2(this);
				posty21.open();
				posty21.deletecom(n);  
				posty21.close();
			}catch(Exception e){
				didWork1 = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Error");
				TextView tv = new TextView(this);
				tv.setText("Invalid Action"); 
				d.setContentView(tv);
				d.show(); 
			}
			finally{
				if(didWork1){
					Dialog d = new Dialog(this);
					d.setTitle("Deleted");  
					TextView tv = new TextView(this);
					tv.setText("Success");  
					d.setContentView(tv);  
					d.show();
				}
			}
			break;
		}
		
	}
	

}
