package com.tpi.sagal.control;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import com.tpi.sagal.dao.VaccineDaoAdapter;
import com.tpi.sagal.entity.Vaccine;

public class ManageVaccine {

	private VaccineDaoAdapter vaccineDao;
	
	public ManageVaccine (Context context){
		vaccineDao = new VaccineDaoAdapter(context);
		vaccineDao.open();
		vaccineDao.close();
	}
	
	public void createVaccine(int id, String name){
		vaccineDao.open();
		vaccineDao.createVaccine(id, name);
		vaccineDao.close();
	}
	
	public ArrayList<Vaccine> readAllVaccines(){
		vaccineDao.open();
		Cursor cursor = vaccineDao.readVaccine();
		ArrayList<Vaccine> vaccines = new ArrayList<Vaccine>();
		if (cursor.moveToFirst()){
			do
			{
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				String name = cursor.getString(cursor.getColumnIndex("vaccine_name"));
				vaccines.add(new Vaccine(id, name));
			} while (cursor.moveToNext());
		}
		cursor.close();
		vaccineDao.close();
		return vaccines;
	}
	
	
	public Vaccine searchVaccine(String name){
		vaccineDao.open();
		Cursor cursor = vaccineDao.searchVaccine(name);
		if(cursor.moveToFirst()){
		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		String vacName = cursor.getString(cursor.getColumnIndex("vaccine_name"));
		cursor.close();
		vaccineDao.close();
		return new Vaccine(id,vacName);
		}
		return null;
	}
}