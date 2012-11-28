package com.tpi.sagal.control;

import java.util.ArrayList;

import com.tpi.sagal.dao.InjuryDaoAdapter;
import com.tpi.sagal.entity.Cow;
import com.tpi.sagal.entity.Farm;
import com.tpi.sagal.entity.Injury;
import com.tpi.sagal.entity.Vaccine;

import android.content.Context;
import android.database.Cursor;

public class ManageInjury {
	private InjuryDaoAdapter injuryDao;
	
	public ManageInjury (Context context){
		injuryDao = new InjuryDaoAdapter (context);
		injuryDao.open();
		injuryDao.close();
	}
	
	public void createInjury(int id, String name, int inf){
		injuryDao.open();
		injuryDao.createInjury(id, name, inf);
		injuryDao.close();
	}
	
	public void createInjury(String name, int inf){
		injuryDao.open();
		injuryDao.createInjury(name, inf);
		injuryDao.close();
	}
	public ArrayList<Injury> readAllInjuries(){
		injuryDao.open();
		Cursor cursor = injuryDao.readInjury();
		ArrayList<Injury> injuries = new ArrayList<Injury>();
		if (cursor.moveToFirst()){
			do
			{
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				String name = cursor.getString(cursor.getColumnIndex("injury_name"));
				int inf = cursor.getInt(cursor.getColumnIndex("injury_infectious"));
				injuries.add(new Injury(id, name,inf));
			} while ( cursor.moveToNext());
		}
		cursor.close();
		injuryDao.close();
		return injuries;
	}
	
	public Injury searchInjury(String name){
		injuryDao.open();
		Cursor cursor = injuryDao.searchInjury(name);
		if(cursor.moveToFirst()){
		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		String injName = cursor.getString(cursor.getColumnIndex("injury_name"));
		int inf = cursor.getInt(cursor.getColumnIndex("injury_infectious"));
		cursor.close();
		injuryDao.close();
		return new Injury(id,injName,inf);
		}
		return null;
	}
	
	public void deleteInjury (int id){
		injuryDao.open();
		injuryDao.deleteInjury(id);
		injuryDao.close();
	}
	
	public void updateInjury (int id, String name, int infec){
		injuryDao.open();
		injuryDao.updateInjury(id, name, infec);
		injuryDao.close();
	}

	public Injury searchInjury(int injuryId) {
		injuryDao.open();
		Cursor cursor = injuryDao.searchInjury(injuryId);
		if(cursor.moveToFirst()){
		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		String injName = cursor.getString(cursor.getColumnIndex("injury_name"));
		int inf = cursor.getInt(cursor.getColumnIndex("injury_infectious"));
		cursor.close();
		injuryDao.close();
		return new Injury(id,injName,inf);
		}
		return null;
	}
}
