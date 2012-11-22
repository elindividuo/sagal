package com.tpi.sagal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocomotionScoreDaoAdapter {
	private static final String DATABASE_NAME = "sagal.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "locomotion";
	private static final String DATABASE_FOREIGN_TABLE = "cow";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_SCORE = "locomotion_score";
	private static final String KEY_DATE = "locomotion_date";
	private static final String KEY_COW = "cow_id";

	private static final String[] columns = { KEY_ID, KEY_SCORE, KEY_DATE, KEY_COW};
	
	private static final String LOCOMOTION_TABLE_CREATE ="CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE+" ("+
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
			KEY_SCORE + " INTEGER NOT NULL,"+
			KEY_DATE + " TEXT NOT NULL,"+
			KEY_COW + " INTEGER NOT NULL,"+
			"FOREIGN KEY ("+KEY_COW+") REFERENCES "+DATABASE_FOREIGN_TABLE+" (_id));";
	
	private LocomotionDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	private static class LocomotionDBHelper extends SQLiteOpenHelper{

		public LocomotionDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(LOCOMOTION_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
		@Override
		public void onOpen(SQLiteDatabase _db) {
		    _db.execSQL(LOCOMOTION_TABLE_CREATE);
		}
		
		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public LocomotionScoreDaoAdapter(Context c){
		ourContext = c; 
	}
	
	public LocomotionScoreDaoAdapter open() throws SQLException{
		ourHelper = new LocomotionDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public long createLocomotion(int score,String date, int cowId) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_SCORE, score);
		cv.put(KEY_DATE, date);
		cv.put(KEY_COW, cowId);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public Cursor readLocomotion() throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
	}
	
	public Cursor searchLocomotion(int cowId) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_COW + "=" + cowId,null,null,null,null);
	}
	
	public void deleteLocomotion(int id) throws SQLException{
		ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + id,null);
	}
	
	public void updateLocomotion(int score,String date, int cowId) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_SCORE, score);
		cv.put(KEY_DATE, date);
		cv.put(KEY_COW, cowId);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + cowId,null);
	}
}

