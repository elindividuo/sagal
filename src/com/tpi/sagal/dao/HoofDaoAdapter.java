package com.tpi.sagal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HoofDaoAdapter {
	private static final String DATABASE_NAME = "sagal.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "hoof";
	private static final String DATABASE_FOREIGN_TABLE1 = "cow";
	private static final String DATABASE_FOREIGN_TABLE2 = "limb";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_COW = "cow_id";
	private static final String KEY_LIMB = "limb_id";

	private static final String[] columns = { KEY_ID, KEY_COW, KEY_LIMB };

	private static final String HOOF_TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE+" ("+
			KEY_ID + " INTEGER PRIMARY KEY,"+
			KEY_COW + " INTEGER NOT NULL,"+
			KEY_LIMB + " INTEGER NOT NULL,"+
			" FOREIGN KEY ("+KEY_COW+") REFERENCES "+DATABASE_FOREIGN_TABLE1+" (_id),"+
			" FOREIGN KEY ("+KEY_LIMB+") REFERENCES "+DATABASE_FOREIGN_TABLE2+" (_id));";
	
	private HoofDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class HoofDBHelper extends SQLiteOpenHelper{

		public HoofDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(HOOF_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
		@Override
		public void onOpen(SQLiteDatabase _db) {
		    _db.execSQL(HOOF_TABLE_CREATE);
		}

		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public HoofDaoAdapter(Context c){
		ourContext = c; 
	}
	
	public HoofDaoAdapter open() throws SQLException{
		ourHelper = new HoofDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
		
	public long createHoof(int id, int cowId, int limbId) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_ID, id);
		cv.put(KEY_COW, cowId);
		cv.put(KEY_LIMB, limbId);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public Cursor readHoof() throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
	}
	
	public Cursor searchHoof(int id) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "=" + id,null,null,null,null);
	}
	
	public void deleteHoof(int id) throws SQLException{
		ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + id,null);
	}
	
	public void updateHoof(int id, int cowId, int limbId) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_ID, id);
		cv.put(KEY_COW, cowId);
		cv.put(KEY_LIMB, limbId);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}
	
	public Cursor readHoofFromCow(int id) {
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_COW + "=" + id,null,null,null,null);
	}
}
