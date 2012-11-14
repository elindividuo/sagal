package com.tpi.sagal.control;

import java.util.ArrayList;
import com.tpi.sagal.dao.FarmDaoAdapter;
import com.tpi.sagal.entity.Farm;
import android.content.Context;
import android.database.Cursor;

public class ManageFarm {
	
	private FarmDaoAdapter farmDao;
	
	public ManageFarm(Context context)
	{
		farmDao = new FarmDaoAdapter(context);
	}
	
	public void createFarm(String name, String address, String owner){
		farmDao.open();
		farmDao.createFarm(name, address, owner);
		farmDao.close();
	}
	
	public ArrayList<Farm> readAllFarm(){
		farmDao.open();
		Cursor cursor = farmDao.readFarm();
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
       farmDao.close();
       return farms;
	}
	
	public Farm searchFarm(int id){
		farmDao.open();
		Cursor cursor = farmDao.searchFarm(id);
		if(cursor.moveToFirst()){
			int fId = cursor.getInt(cursor.getColumnIndex("_id"));
	        String name = cursor.getString(cursor.getColumnIndex("farm_name"));
	        String address = cursor.getString(cursor.getColumnIndex("farm_address"));
	        String ownerName = cursor.getString(cursor.getColumnIndex("farm_owner"));
	        cursor.close();
	        farmDao.close();
	        return new Farm(fId,name,address,ownerName);
		}
		return null;
	}
	
	public void deleteFarm(int id){
		farmDao.open();
		farmDao.deleteFarm(id);
		farmDao.close();
	}
	
	public void updateFarm(int id, String name, String address, String ownerName){
		farmDao.open();
		farmDao.updateFarm(id, name, address, ownerName);
		farmDao.close();
	}
 	
}

