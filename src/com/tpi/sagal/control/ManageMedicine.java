package com.tpi.sagal.control;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.tpi.sagal.dao.MedicineDaoAdapter;
import com.tpi.sagal.entity.Medicine;

public class ManageMedicine {
private MedicineDaoAdapter medicineDao;
	
	public ManageMedicine (Context context){
		medicineDao = new MedicineDaoAdapter(context);
		medicineDao.open();
		medicineDao.close();
	}
	
	public void createMedicine(int id, String name, Double concentration, String unit){
		medicineDao.open();
		medicineDao.createMedicine(id, name,concentration, unit);
		medicineDao.close();
	}
	
	public void createMedicine( String name, Double concentration, String unit){
		medicineDao.open();
		medicineDao.createMedicine(name, concentration, unit);
		medicineDao.close();
	}
	
	public ArrayList<Medicine> readAllMedicines(){
		medicineDao.open();
		Cursor cursor = medicineDao.readMedicine();
		ArrayList<Medicine> medicines = new ArrayList<Medicine>();
		if (cursor.moveToFirst()){
			do
			{
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				String name = cursor.getString(cursor.getColumnIndex("medicine_name"));
				Double concen = cursor.getDouble(cursor.getColumnIndex("medicine_concentration"));
				String unit = cursor.getString(cursor.getColumnIndex("medicine_unit"));
				medicines.add(new Medicine(id, name,concen,unit));
			} while (cursor.moveToNext());
		}
		cursor.close();
		medicineDao.close();
		return medicines;
	}
	
	public Medicine searchMedicine(String name){
		medicineDao.open();
		Cursor cursor = medicineDao.searchMedicine(name);
		if(cursor.moveToFirst()){
		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		String medName = cursor.getString(cursor.getColumnIndex("medicine_name"));
		Double concen = cursor.getDouble(cursor.getColumnIndex("medicine_concentration"));
		String unit = cursor.getString(cursor.getColumnIndex("medicine_unit"));
		cursor.close();
		medicineDao.close();
		return new Medicine(id,medName, concen, unit);
		}
		return null;
	}

	public Medicine searchMedicine(int medId) {
		medicineDao.open();
		Cursor cursor = medicineDao.searchMedicine(medId);
		if(cursor.moveToFirst()){
		int id = cursor.getInt(cursor.getColumnIndex("_id"));
		String medName = cursor.getString(cursor.getColumnIndex("medicine_name"));
		Double concen = cursor.getDouble(cursor.getColumnIndex("medicine_concentration"));
		String unit = cursor.getString(cursor.getColumnIndex("medicine_unit"));
		cursor.close();
		medicineDao.close();
		return new Medicine(id,medName, concen, unit);
		}
		return null;
	}

	public void deleteMedicine(int id){
		medicineDao.open();
		medicineDao.deleteMedicine(id);
		medicineDao.close();
	}
	
	public void udpdateMedicine (int id, String name, double concentration, String unit){
		medicineDao.open();
		medicineDao.updateMedicine(id, name,concentration, unit);
		medicineDao.close();
	}
}
