package com.tpi.sagal.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Zone_InjuryDaoAdapter {
	private static final String DATABASE_NAME = "sagal.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "zone_has_injury";
	private static final String DATABASE_FOREIGN_TABLE1 = "zone";
	private static final String DATABASE_FOREIGN_TABLE2 = "injury";
	
	private static final String KEY_ZONE = "zone_id";
	private static final String KEY_INJURY = "injury_id";
	
	private static final String[] columns = { KEY_ZONE, KEY_INJURY};
	
	private static final String ZONE_INJURY_TABLE_CREATE ="CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE+" ("+
			KEY_ZONE + " INTEGER NOT NULL,"+
			KEY_INJURY + " INTEGER NOT NULL," +
			" FOREIGN KEY ("+KEY_ZONE+") REFERENCES "+DATABASE_FOREIGN_TABLE1+" (_id),"+
			" FOREIGN KEY ("+KEY_INJURY+") REFERENCES "+DATABASE_FOREIGN_TABLE2+" (_id));";
	
	private Zone_InjuryDBHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private static class Zone_InjuryDBHelper extends SQLiteOpenHelper{

		public Zone_InjuryDBHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(ZONE_INJURY_TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
		@Override
		public void onOpen(SQLiteDatabase _db) {
		    _db.execSQL(ZONE_INJURY_TABLE_CREATE);
		}

		@Override
		public void onDowngrade(SQLiteDatabase db, int oldVersion,int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public Zone_InjuryDaoAdapter(Context c){
		ourContext = c; 
	}
	
	public Zone_InjuryDaoAdapter open() throws SQLException{
		ourHelper = new Zone_InjuryDBHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
		
	public long createZone_Injury(int zoneId, int injuryId) throws SQLException{
		ContentValues cv = new ContentValues();
		cv.put(KEY_ZONE, zoneId);
		cv.put(KEY_INJURY, injuryId);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
	public Cursor readZone_Injury() throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, null,null,null,null,null);
	}
	
	public Cursor searchZone_Injury(int cowId, int vaccineId ) throws SQLException{
		return ourDatabase.query(DATABASE_TABLE, columns, KEY_ZONE + "=" + cowId+" AND "+ KEY_INJURY + "=" +vaccineId,null,null,null,null);
	}
	
	public void deleteZone_Injury(int cowId, int vaccineId ) throws SQLException{
		ourDatabase.delete(DATABASE_TABLE, KEY_ZONE + "=" + cowId+" AND "+ KEY_INJURY + "=" +vaccineId,null);
	}

	public void deleteZone_Injury(int zoneId) {
		ourDatabase.delete(DATABASE_TABLE, KEY_ZONE + "=" + zoneId,null);
	}
	
	public Cursor searchZone_Injury(int zoneId) throws SQLException{
		return ourDatabase.query("zone join zone_has_injury on (zone._id=zone_has_injury.zone_id) join injury on (zone_has_injury.injury_id = injury._id)",new String[] {"injury.injury_name","injury.injury_abbreviation"}, KEY_ZONE + "=" + zoneId,null,null,null,null);
	}
	
}
