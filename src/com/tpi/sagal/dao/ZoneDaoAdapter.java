package com.tpi.sagal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ZoneDaoAdapter {
	private static final String DATABASE_NAME = "sagal.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "zone";
	private static final String DATABASE_FOREIGN_TABLE = "zone";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_NUMBER = "zone_number";
	private static final String KEY_HOOF = "hoof_id";

	private static final String[] columns = { KEY_ID, KEY_NUMBER, KEY_HOOF };

	private static final String ZONE_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE+" ("+
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
			KEY_NUMBER + " INTEGER NOT NULL,"+
			KEY_HOOF + " INTEGER NOT NULL,"+
			" FOREIGN KEY ("+KEY_HOOF+") REFERENCES "+DATABASE_FOREIGN_TABLE+" (_id));";
	
	private ZoneDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class ZoneDBHelper extends SQLiteOpenHelper{

		public ZoneDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(ZONE_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
		@Override
		public void onOpen(SQLiteDatabase _db) {
		    _db.execSQL(ZONE_TABLE_CREATE);
		}

		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public ZoneDaoAdapter(Context c){
		ourContext = c; 
	}
	
	public ZoneDaoAdapter open() throws SQLException{
		ourHelper = new ZoneDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
		
	public long createZone(int hoofId, int number) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_HOOF, hoofId);
		cv.put(KEY_NUMBER, number);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public Cursor readZone() throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
	}
	
	public Cursor searchZone(int id) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "=" + id,null,null,null,null);
	}
	
	public void deleteZone(int id) throws SQLException{
		ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + id,null);
	}
	
	public void updateZone(int id, int number, int hoofId) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_ID, id);
		cv.put(KEY_NUMBER, number);
		cv.put(KEY_HOOF, hoofId);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}
	
	public Cursor readZoneFromHoof(int id) {
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_HOOF + "=" + id,null,null,null,null);
	}

	public Cursor readZoneFromHoof(int hoofId, int selectedZone) {
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_HOOF + "=" + hoofId+" AND "+ KEY_NUMBER+ "="+selectedZone,null,null,null,null);
	}
	
}
