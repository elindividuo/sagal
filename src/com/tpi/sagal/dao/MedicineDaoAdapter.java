package com.tpi.sagal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MedicineDaoAdapter {
	private static final String DATABASE_NAME = "sagal.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "medicine";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_NAME = "medicine_name";
	private static final String KEY_CONCENTRATION = "medicine_concentration";

	private static final String[] columns = { KEY_ID, KEY_NAME, KEY_CONCENTRATION};
	
	private static final String MEDICINE_TABLE_CREATE ="CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE+" ("+
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
			KEY_NAME + " TEXT NOT NULL,"+
			KEY_CONCENTRATION + " DOUBLE NOT NULL);";
	
	private MedicineDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	private static class MedicineDBHelper extends SQLiteOpenHelper{

		public MedicineDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(MEDICINE_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
		@Override
		public void onOpen(SQLiteDatabase _db) {
		    _db.execSQL(MEDICINE_TABLE_CREATE);
		}
		
		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public MedicineDaoAdapter(Context c){
		ourContext = c; 
	}
	
	public MedicineDaoAdapter open() throws SQLException{
		ourHelper = new MedicineDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
	
	public long createMedicine(int id, String name, double concentration) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_ID, id);
		cv.put(KEY_NAME, name);
		cv.put(KEY_CONCENTRATION, concentration);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	
	public long createMedicine(String name, double concentration) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_CONCENTRATION, concentration);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public Cursor readMedicine() throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
	}
	
	public Cursor searchMedicine(int id) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "=" + id,null,null,null,null);
	}
	
	public void deleteMedicine(int id) throws SQLException{
		ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + id,null);
	}
	
	public void updateMedicine(int id, String name) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}
	
	public Cursor searchMedicine(String name) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_NAME + "= \"" + name+"\"",null,null,null,null);
	}

	public void updateMedicine(int id, String name, double concentration) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_CONCENTRATION, concentration);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}
}
