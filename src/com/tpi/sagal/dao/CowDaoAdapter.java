package com.tpi.sagal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CowDaoAdapter {
	private static final String DATABASE_NAME = "sagal.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "cow";
	private static final String DATABASE_FOREIGN_TABLE = "farm";
	
	private static final String KEY_ID = "_id";
	private static final String KEY_REGISTRY = "cow_registry";
	private static final String KEY_NAME ="cow_name";
	private static final String KEY_BREED ="cow_breed";
	private static final String KEY_BIRTH ="cow_birthday";
	private static final String KEY_TATTOO ="cow_tattoo";
	private static final String KEY_PROBLEMS ="cow_problems";
	private static final String KEY_FINAL ="cow_finaldestination";
	private static final String KEY_DIF_DIAG ="cow_dif_diag";
	private static final String KEY_REGIMENS ="cow_regimens";
	private static final String KEY_LOCOMOTION ="cow_locomotion";
	private static final String KEY_MOTHER ="cow_mother";
	private static final String KEY_FATHER ="cow_father";
	private static final String KEY_FARM ="farm_id";
	private static final String[] columns = {KEY_ID,KEY_REGISTRY,KEY_NAME,KEY_BREED, KEY_BIRTH, KEY_TATTOO, KEY_PROBLEMS,KEY_REGIMENS,KEY_DIF_DIAG, KEY_FINAL,KEY_LOCOMOTION,KEY_MOTHER,KEY_FATHER,KEY_FARM};
	
	private static final String COW_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE+" ("+
			KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
			KEY_REGISTRY + " INTEGER NOT NULL," +
			KEY_NAME + " TEXT NOT NULL,"+
			KEY_BREED + " TEXT NOT NULL,"+
			KEY_BIRTH + " TEXT NOT NULL,"+
			KEY_TATTOO + " TEXT NOT NULL,"+
			KEY_PROBLEMS + " TEXT NOT NULL,"+
			KEY_DIF_DIAG + " TEXT,"+
			KEY_REGIMENS + " TEXT,"+
			KEY_FINAL + " TEXT,"+
			KEY_LOCOMOTION + " INTEGER,"+
			KEY_MOTHER + " INTEGER,"+
			KEY_FATHER + " INTEGER,"+
			KEY_FARM + " INTEGER NOT NULL,"+
			" FOREIGN KEY ("+KEY_FARM+") REFERENCES "+DATABASE_FOREIGN_TABLE+" (_id),"+
			" FOREIGN KEY ("+KEY_MOTHER+") REFERENCES "+DATABASE_TABLE+" (_id),"+
			" FOREIGN KEY ("+KEY_FATHER+") REFERENCES "+DATABASE_TABLE+" (_id));";
	
	private CowDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class CowDBHelper extends SQLiteOpenHelper{

		public CowDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(COW_CREATE_TABLE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
		@Override
		public void onOpen(SQLiteDatabase _db) {
		    _db.execSQL(COW_CREATE_TABLE);
		}

		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public CowDaoAdapter(Context c){
		ourContext = c; 
	}
	
	public CowDaoAdapter open() throws SQLException{
		ourHelper = new CowDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
		
	public long createCow(int registry, String name, String breed, String birth, String tattoo, String problems, String finalDestination, String differentialDiag, String regimens ,int locomotion,int motherId, int fatherId , int farmId) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_REGISTRY, registry);
		cv.put(KEY_NAME, name);
		cv.put(KEY_BREED, breed);
		cv.put(KEY_BIRTH, birth);
		cv.put(KEY_TATTOO, tattoo);
		cv.put(KEY_PROBLEMS, problems);
		cv.put(KEY_REGIMENS, regimens);
		cv.put(KEY_DIF_DIAG, differentialDiag);
		cv.put(KEY_FINAL, finalDestination);
		cv.put(KEY_LOCOMOTION, locomotion);
		cv.put(KEY_MOTHER, motherId);
		cv.put(KEY_FATHER, fatherId);
		cv.put(KEY_FARM, farmId);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	
	public long createCow(int registry, String name, String breed, String birth, String tattoo, String problems, int farmId) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_REGISTRY, registry);
		cv.put(KEY_NAME, name);
		cv.put(KEY_BREED, breed);
		cv.put(KEY_BIRTH, birth);
		cv.put(KEY_TATTOO, tattoo);
		cv.put(KEY_PROBLEMS, problems);
		cv.put(KEY_FARM, farmId);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	
	public Cursor readCow() throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
	}
	
	public Cursor searchCow(int id) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "=" + id,null,null,null,null);
	}
	
	public void deleteCow(int id) throws SQLException{
		ourDatabase.delete(DATABASE_TABLE, KEY_ID + "=" + id,null);
	}
	
	public void updateCow(int id,int registry, String name, String breed, String birth, String tattoo, String problems, String finalDestination, int locomotion,int motherId, int fatherId , int farmId) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_REGISTRY, registry);
		cv.put(KEY_NAME, name);
		cv.put(KEY_BREED, breed);
		cv.put(KEY_BIRTH, birth.toString());
		cv.put(KEY_TATTOO, tattoo);
		cv.put(KEY_PROBLEMS, problems);
		cv.put(KEY_FINAL, finalDestination);
		cv.put(KEY_LOCOMOTION, locomotion);
		cv.put(KEY_MOTHER, motherId);
		cv.put(KEY_FATHER, fatherId);
		cv.put(KEY_FARM, farmId);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}
	
	public Cursor readCowFromFarm(int id) {
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_FARM + "=" + id,null,null,null,null);
	}

	public void updateCow(int id, int registry, String name, String breed, String birth, String tattoo, String problems, String finalDestination, int motherId, int fatherId, int farmId) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_REGISTRY, registry);
		cv.put(KEY_NAME, name);
		cv.put(KEY_BREED, breed);
		cv.put(KEY_BIRTH, birth.toString());
		cv.put(KEY_TATTOO, tattoo);
		cv.put(KEY_PROBLEMS, problems);
		cv.put(KEY_FINAL, finalDestination);
		cv.put(KEY_MOTHER, motherId);
		cv.put(KEY_FATHER, fatherId);
		cv.put(KEY_FARM, farmId);
		ourDatabase.update(DATABASE_TABLE, cv, KEY_ID + "=" + id,null);
	}
	
	public Cursor readCowFromFarm(int id, int fatherId, int motherId, int farmId) {
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + " != " + id + " AND "+ KEY_ID + " != "+ fatherId+ " AND "+ KEY_ID + " != "+motherId+ " AND "+KEY_FARM+" = "+farmId,null,null,null,null);
	}
	
	public Cursor readLastCow() {
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_ID + "= (SELECT MAX (" + KEY_ID + ") FROM " + DATABASE_TABLE + ");",null,null,null,null);
	}
	
	public void updateCow(int id, String regimens, String diffDiag){
		ContentValues cv = new ContentValues();
		cv.put(KEY_REGIMENS, regimens);
		cv.put(KEY_DIF_DIAG, diffDiag);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}
	
	public void updateCow(int id, int locomotionScore){
		ContentValues cv = new ContentValues();
		cv.put(KEY_LOCOMOTION, locomotionScore);
		ourDatabase.update(DATABASE_TABLE,cv, KEY_ID + "=" + id,null);
	}
	
}
