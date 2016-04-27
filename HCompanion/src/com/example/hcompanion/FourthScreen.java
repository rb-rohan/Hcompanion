package com.example.hcompanion;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FourthScreen extends Activity implements OnClickListener{

	TextView tvji;
	Button exitt,addc,submit;
	EditText postno,comment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fourth_screen);
		
		tvji = (TextView) findViewById(R.id.tvSQLdata);
		exitt = (Button) findViewById(R.id.bSQLExitt);
		addc = (Button) findViewById(R.id.bCommentss);
		submit = (Button) findViewById(R.id.bSQLsubmit);
		postno = (EditText) findViewById(R.id.etpostno);
		comment = (EditText) findViewById(R.id.etcommentss);
		
		
		DatabaseStuff information = new DatabaseStuff(this);
		information.open();
		String data = information.getdata();
		information.close();
		tvji.setText(data);
		
		addc.setOnClickListener(this); 
		exitt.setOnClickListener(this); 
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSQLExitt:
			this.finish();
			break;
		case R.id.bCommentss:
			postno.setVisibility(View.VISIBLE);
			comment.setVisibility(View.VISIBLE); 
			break;
		case R.id.bSQLsubmit:
			boolean didWork = true; 
			try{
			String scomment = comment.getText().toString();
			String spostno = postno.getText().toString();
			int l = Integer.parseInt(spostno); 
			
			DatabaseStuff2 entry1 = new DatabaseStuff2(FourthScreen.this); 
			entry1.open();
			entry1.createCommentEntry(scomment,l); 
			entry1.close();
			}catch(Exception e){
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
					d.setTitle("Comments Saved");  
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
