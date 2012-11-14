package com.tpi.sagal.control;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;

import com.tpi.sagal.dao.CowDaoAdapter;
import com.tpi.sagal.dao.FarmDaoAdapter;
import com.tpi.sagal.entity.Farm;

public class ManageCow {
	
	private CowDaoAdapter cowDao;
	
	public ManageCow(Context context)
	{
		cowDao = new CowDaoAdapter(context);
	}
	
	public void createFarm(int id, String name, String breed, Date birth, String tattoo, String problems, String finalDestination, int locomotion,int motherId, int fatherId , int farmId){
		cowDao.open();
		cowDao.createCow(id, name, breed, birth, tattoo, problems, finalDestination, locomotion, motherId, fatherId, farmId);
		cowDao.close();
	}
	
	public ArrayList<Farm> readAllFarm(){
		cowDao.open();
		Cursor cursor = cowDao.readCow();
		ArrayList<Farm> farms = new ArrayList<Farm>();
		if (cursor.moveToFirst()){
			do
			{
			   int id = cursor.getInt(cursor.getColumnIndex("_id"));
               String name = cursor.getString(cursor.getColumnIndex("farm_name"));
               String address = cursor.getString(cursor.getColumnIndex("farm_address"));
               String ownerName = cursor.getString(cursor.getColumnIndex("farm_owner"));
              
               farms.add( new Farm(id, name, address, ownerName));             
			} while ( cursor.moveToNext());

       }
       cursor.close();
       cowDao.close();
       return farms;
	}
	
	public Farm searchFarm(int id){
		cowDao.open();
		Cursor cursor = cowDao.searchCow(id);
		if(cursor.moveToFirst()){
			int fId = cursor.getInt(cursor.getColumnIndex("_id"));
	        String name = cursor.getString(cursor.getColumnIndex("farm_name"));
	        String address = cursor.getString(cursor.getColumnIndex("farm_address"));
	        String ownerName = cursor.getString(cursor.getColumnIndex("farm_owner"));
	        cursor.close();
	        cowDao.close();
	        return new Farm(fId,name,address,ownerName);
		}
		return null;
	}
	
	public void deleteCow(int id){
		cowDao.open();
		cowDao.deleteCow(id);
		cowDao.close();
	}
	
	public void updateCow(int id, String name, String breed, Date birth, String tattoo, String problems, String finalDestination, int locomotion,int motherId, int fatherId , int farmId){
		cowDao.open();
		cowDao.updateCow(id, name, breed, birth, tattoo, problems, finalDestination, locomotion, motherId, fatherId, farmId);
		cowDao.close();
	}
 	
}
