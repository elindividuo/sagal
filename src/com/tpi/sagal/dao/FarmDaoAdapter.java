package com.tpi.sagal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class FarmDaoAdapter {

	private static final String DATABASE_NAME = "sagal.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "farm";
	
	private static final String KEY_ID ="_id";
	private static final String KEY_NAME ="farm_name";
	private static final String KEY_ADDRESS ="farm_address";
	private static final String KEY_OWNERNAME ="farm_owner";
	private static final String[] columns = {KEY_ID,KEY_NAME,KEY_ADDRESS, KEY_OWNERNAME};
	
	private static final String FARM_TABLE_CREATE = "CREATE TABLE " + DATABASE_TABLE +" (" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			KEY_NAME + " TEXT NOT NULL," +
			KEY_ADDRESS + " TEXT NOT NULL," +
			KEY_OWNERNAME + " TEXT NOT NULL);";
	
	
	private FarmDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	private static class FarmDBHelper extends SQLiteOpenHelper{

		public FarmDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(FARM_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}	
	}
	
	public FarmDaoAdapter(Context c){
		ourContext = c; 
	}
	
	public FarmDaoAdapter open() throws SQLException{
		ourHelper = new FarmDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public long createFarm(String name, String address, String ownerName) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_OWNERNAME, ownerName);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public Cursor readFarm() throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
	}
	
	public Cursor searchFarm(int id) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "=" + id,null,null,null,null);
	}
	
	public void deleteFarm(int id) throws SQLException{
		ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + id,null);
	}
	
	public void updateFarm(int id, String name, String address, String ownerName) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_OWNERNAME, ownerName);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}
	
}