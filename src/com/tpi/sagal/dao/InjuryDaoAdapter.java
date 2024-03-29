package com.tpi.sagal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class InjuryDaoAdapter {
	private static final String DATABASE_NAME = "sagal.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "injury";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_NAME = "injury_name";
	private static final String KEY_INFECTIOUS = "injury_infectious";
	
	private static final String[] columns = { KEY_ID, KEY_NAME, KEY_INFECTIOUS};

	private static final String INJURY_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE+" ("+
			KEY_ID + " INTEGER PRIMARY KEY,"+
			KEY_INFECTIOUS + " INTEGER NOT NULL," +
			KEY_NAME + " TEXT NOT NULL);";
	
	private InjuryDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class InjuryDBHelper extends SQLiteOpenHelper{

		public InjuryDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(INJURY_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
		@Override
		public void onOpen(SQLiteDatabase _db) {
		    _db.execSQL(INJURY_TABLE_CREATE);
		}

		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public InjuryDaoAdapter(Context c){
		ourContext = c; 
	}
	
	public InjuryDaoAdapter open() throws SQLException{
		ourHelper = new InjuryDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
		
	public long createInjury(int id, String name, int inf) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_ID, id);
		cv.put(KEY_NAME, name);
		cv.put(KEY_INFECTIOUS, inf);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public Cursor readInjury() throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
	}
	
	public Cursor searchInjury(int id) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "=" + id,null,null,null,null);
	}
	
	public void deleteInjury(int id) throws SQLException{
		ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + id,null);
	}
	
	public void updateInjury(int id, String name) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}

	public Cursor searchInjury(String name) {
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_NAME + "= \"" + name+"\"",null,null,null,null);
	}

	public long createInjury(String name, int inf) {
		ContentValues cv = new ContentValues();	
		cv.put(KEY_NAME, name);
		cv.put(KEY_INFECTIOUS, inf);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
		
	}

	public void updateInjury(int id, String name, int infec) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_INFECTIOUS, infec);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}

	
}
