package com.example.hcompanion;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseStuff2 {

	public static final String KEY_ROWID2 = "_id2";
	public static final String KEY_POSTNO = "post_no";
	public static final String KEY_COMM = "comm"; 
	
	private static final String DATABASE_NAME2 = "Companiondb2";
	private static final String DATABASE_TABLE2 = "peopletable2";
	private static final int DATABASE_VERSION2 = 1;
	
	private DbHelper ourHelper;
	private static Context ourContext;
	private SQLiteDatabase ourDatabase;
	
private static class DbHelper extends SQLiteOpenHelper{
		
		public DbHelper(Context context) {
			super(context, DATABASE_NAME2, null, DATABASE_VERSION2);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			db.execSQL("CREATE TABLE " + DATABASE_TABLE2 + "(" 
					+ KEY_ROWID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ KEY_POSTNO + " INTEGER NOT NULL, " + KEY_COMM + " TEXT NOT NULL);"); 
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE2);
			onCreate(db);
		}
	}

	
	public DatabaseStuff2(Context c)
	{
		ourContext = c;
	}
	
	public DatabaseStuff2 open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close(){
		ourHelper.close();
	}

	public long createCommentEntry(String scomment, int l) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues(); 
		cv.put(KEY_POSTNO, l);
		cv.put(KEY_COMM, scomment); 
		return ourDatabase.insert(DATABASE_TABLE2, null, cv);
	}

	public String getcomm(int n) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID2,KEY_POSTNO,KEY_COMM};
		String result = "";
		
		Cursor c = ourDatabase.query(DATABASE_TABLE2, columns, KEY_POSTNO + "=" + n, null, null, null, null);
		
			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
			{
				if(n == c.getInt(1)) 
				result = result + "\n\t\t\t" + c.getString(2) + "\n\n";
			}
			return result;
		
	}

	public void deletecom(int nn) throws SQLException{
		// TODO Auto-generated method stub 
		String[] columns = new String[]{KEY_ROWID2,KEY_POSTNO,KEY_COMM};
		Cursor cc = ourDatabase.query(DATABASE_TABLE2, columns, KEY_POSTNO + "=" + nn, null, null, null, null);
		for(cc.moveToFirst(); !cc.isAfterLast(); cc.moveToNext())
		{
			if(nn == cc.getInt(1)) 
				ourDatabase.delete(DATABASE_TABLE2, KEY_POSTNO + "=" + nn, null);  
		}
	}
 
 
	
}
