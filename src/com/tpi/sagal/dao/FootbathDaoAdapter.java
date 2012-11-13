package com.tpi.sagal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FootbathDaoAdapter {
	
	private static final String DATABASE_NAME = "sagal.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "Footbath";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_NAME ="Footbath_name";
	private static final String KEY_WIDTH ="Footbath_width";
	private static final String KEY_DEEP ="Footbath_deep";
	private static final String KEY_HEIGHT ="Footbath_height";
	private static final String KEY_MEDICINE ="Footbath_medicine_type";
	private static final String KEY_PERCENTAGE ="Footbath_percentage";
	private static final String[] columns = {KEY_ID,KEY_NAME,KEY_WIDTH, KEY_DEEP, KEY_HEIGHT, KEY_MEDICINE, KEY_PERCENTAGE};
	
	private static final String FOOTBATH_TABLE_CREATE = "CREATE TABLE " + DATABASE_TABLE +" (" +
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			KEY_NAME + " TEXT NOT NULL," +
			KEY_WIDTH + " DOUBLE NOT NULL," +
			KEY_DEEP + " DOUBLE NOT NULL," +
			KEY_HEIGHT + " DOUBLE NOT NULL," +
			KEY_MEDICINE + " TEXT NOT NULL," +
			KEY_PERCENTAGE + " FLOAT NOT NULL);";
	
	
	private FootbathDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	private static class FootbathDBHelper extends SQLiteOpenHelper{

		public FootbathDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(FOOTBATH_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}	
	}
	
	public FootbathDaoAdapter(Context c){
		ourContext = c; 
	}
	
	public FootbathDaoAdapter open() throws SQLException{
		ourHelper = new FootbathDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
		
	public long createFootbath(String name, double width, double deep, double height, String medicine, float percentage) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_WIDTH, width);
		cv.put(KEY_DEEP, deep);
		cv.put(KEY_HEIGHT, height);
		cv.put(KEY_MEDICINE, medicine);
		cv.put(KEY_PERCENTAGE, percentage);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public Cursor readFootbath() throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
	}
	
	public Cursor searchFootbath(int id) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "=" + id,null,null,null,null);
	}
	
	public void deleteFootbath(int id) throws SQLException{
		ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + id,null);
	}
	
	public void updateFootbath(int id, String name, double width, double deep, double height, String medicine, float percentage) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_WIDTH, width);
		cv.put(KEY_DEEP, deep);
		cv.put(KEY_HEIGHT, height);
		cv.put(KEY_MEDICINE, medicine);
		cv.put(KEY_PERCENTAGE, percentage);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}
	
}