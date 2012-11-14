package com.tpi.sagal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Cow_VaccineDaoAdapter {
	private static final String DATABASE_NAME = "sagal.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "cow_has_vaccine";
	private static final String DATABASE_FOREIGN_TABLE1 = "cow";
	private static final String DATABASE_FOREIGN_TABLE2 = "vaccine";
	
	
	private static final String KEY_COW = "cow_id";
	private static final String KEY_VACCINE = "vaccine_id";
	
	private static final String[] columns = { KEY_COW, KEY_VACCINE};
	
	private static final String COW_VACCINE_TABLE_CREATE ="CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE+" ("+
			KEY_COW + " INTEGER NOT NULL"+
			KEY_VACCINE + " INTEGER NOT NULL," +
			" FOREIGN KEY ("+KEY_COW+") REFERENCES "+DATABASE_FOREIGN_TABLE1+" (_id),"+
			" FOREIGN KEY ("+KEY_VACCINE+") REFERENCES "+DATABASE_FOREIGN_TABLE2+" (_id));";
	
	private Cow_VaccineDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class Cow_VaccineDBHelper extends SQLiteOpenHelper{

		public Cow_VaccineDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(COW_VACCINE_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
		@Override
		public void onOpen(SQLiteDatabase _db) {
		    _db.execSQL(COW_VACCINE_TABLE_CREATE);
		}

		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public Cow_VaccineDaoAdapter(Context c){
		ourContext = c; 
	}
	
	public Cow_VaccineDaoAdapter open() throws SQLException{
		ourHelper = new Cow_VaccineDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
		
	public long createCow_Vaccine(int cowId, int vaccineId) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_COW, cowId);
		cv.put(KEY_VACCINE, vaccineId);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public Cursor readCow_Vaccine() throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
	}
	
	public Cursor searchCow_Vaccine(int cowId, int vaccineId ) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_COW + "=" + cowId+" AND "+ KEY_VACCINE + "=" +vaccineId,null,null,null,null);
	}
	
	public void deleteCow_Vaccine(int cowId, int vaccineId ) throws SQLException{
		ourDatabase.delete(DATABASE_TABLE, KEY_COW + "=" + cowId+" AND "+ KEY_VACCINE + "=" +vaccineId,null);
	}	
	
}
