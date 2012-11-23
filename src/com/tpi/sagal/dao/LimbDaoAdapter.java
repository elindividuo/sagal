package com.tpi.sagal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LimbDaoAdapter {
	private static final String DATABASE_NAME = "sagal.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "limb";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_NAME = "limb_name";

	private static final String[] columns = { KEY_ID, KEY_NAME};
	
	private static final String VACCINE_TABLE_CREATE ="CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE+" ("+
			KEY_ID + " INTEGER PRIMARY KEY," +
			KEY_NAME + " TEXT NOT NULL);";
	
	private LimbDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	private static class LimbDBHelper extends SQLiteOpenHelper{

		public LimbDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(VACCINE_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
		@Override
		public void onOpen(SQLiteDatabase _db) {
		    _db.execSQL(VACCINE_TABLE_CREATE);
		}
		
		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public LimbDaoAdapter(Context c){
		ourContext = c; 
	}
	
	public LimbDaoAdapter open() throws SQLException{
		ourHelper = new LimbDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public long createLimb(int id, String name) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_ID, id);
		cv.put(KEY_NAME, name);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public Cursor readLimb() throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
	}
	
	public Cursor searchLimb(int id) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "=" + id,null,null,null,null);
	}
	
	public void deleteLimb(int id) throws SQLException{
		ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + id,null);
	}
	
	public void updateLimb(int id, String name) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}
	
}
