package com.example.hcompanion;


import android.content.ContentValues;
import android.content.Context; 
import android.database.Cursor;
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseStuff {

	public static final String KEY_ROWID = "_id";
	public static final String KEY_REGNO = "reg_no";
	public static final String KEY_INFO = "info"; 
	public static final String KEY_DESC = "desc";
	
	private static final String DATABASE_NAME = "Companiondb";
	private static final String DATABASE_TABLE = "peopletable";
	private static final int DATABASE_VERSION = 1;
	
	
	private DbHelper ourHelper;
	private static Context ourContext;
	private SQLiteDatabase ourDatabase;	
	
	private static class DbHelper extends SQLiteOpenHelper{
		
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + "(" 
					+ KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_REGNO + " TEXT NOT NULL, " + KEY_INFO + " TEXT NOT NULL, "
					+ KEY_DESC + " TEXT NOT NULL);"); 
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
	}
	
	public DatabaseStuff(Context c)
	{
		ourContext = c;
	}
	
	public DatabaseStuff open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}

	public long createEntry(String sregno, String sinfo, String sdesc) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_REGNO, sregno);
		cv.put(KEY_INFO, sinfo); 
		cv.put(KEY_DESC, sdesc);
		return ourDatabase.insert(DATABASE_TABLE, null, cv); 
	}
	

	public String getdata() throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_REGNO, KEY_INFO, KEY_DESC};
		
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = ""; 
		int iRow = c.getColumnIndex(KEY_ROWID); 
		int iRegno = c.getColumnIndex(KEY_REGNO);
		int iInfo = c.getColumnIndex(KEY_INFO);
		int iDesc = c.getColumnIndex(KEY_DESC);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
		result = result + "\n\t\t\t" + c.getString(iRow) + "\n\n\t\t\t" + c.getString(iInfo) + "\n\n\t\t\t" + c.getString(iDesc) + "\n";
		} 
		 
		return result;
	}

	public int getpostno(String s) {
		// TODO Auto-generated method stub
		s = "\'" + s + "\'"; 
		String[] columns = new String[]{KEY_ROWID, KEY_REGNO, KEY_INFO, KEY_DESC};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REGNO + "=" + s, null, null, null, null);
		if(c != null)
		{
			c.moveToFirst();
			int postt = c.getInt(0); 
			return postt;  
		}
		else 
		return -1;
	}

	public void updatentry(String regg, String inf, String des) throws SQLException{
		// TODO Auto-generated method stub
		regg = "\'" + regg + "\'"; 
		ContentValues cvUpdate = new ContentValues(); 
		cvUpdate.put(KEY_INFO, inf);
		cvUpdate.put(KEY_DESC, des);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_REGNO + "=" + regg, null); 
	}

	public int getpostnondelete(String sreg) throws SQLException{
		// TODO Auto-generated method stub
		sreg = "\'" + sreg + "\'"; 
		String[] columns = new String[]{KEY_ROWID, KEY_REGNO, KEY_INFO, KEY_DESC};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_REGNO + "=" + sreg, null, null, null, null);
		if(c != null)
		{
			c.moveToFirst();
			int postt = c.getInt(0); 
			
			ourDatabase.delete(DATABASE_TABLE, KEY_REGNO + "=" + sreg, null);
			
			return postt;  
		}
		else 
		return -1; 
	} 
}
	
