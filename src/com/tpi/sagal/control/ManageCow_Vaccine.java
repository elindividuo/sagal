package com.tpi.sagal.control;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.tpi.sagal.dao.Cow_VaccineDaoAdapter;
import com.tpi.sagal.entity.Cow;
import com.tpi.sagal.entity.Farm;

public class ManageCow_Vaccine {
	private Cow_VaccineDaoAdapter cow_vaccDao;

	public ManageCow_Vaccine(Context context) {
		cow_vaccDao = new Cow_VaccineDaoAdapter(context);
		cow_vaccDao.open();
		cow_vaccDao.close();
	}

	public void createCow_Vaccine(int cowId, int vaccineId) {
		cow_vaccDao.open();
		cow_vaccDao.createCow_Vaccine(cowId, vaccineId);
		cow_vaccDao.close();
	}

	public ArrayList<String> searchCow_Vaccine(int cowId) {
		cow_vaccDao.open();
		ArrayList<String> vaccines = new ArrayList<String>();
		Cursor cursor = cow_vaccDao.searchCow_Vaccine(cowId);
		if (cursor.moveToFirst()) {
			do {
				String name = cursor.getString(cursor.getColumnIndex("vaccine_name"));
				vaccines.add(name);
			} while (cursor.moveToNext());

		}
		cursor.close();
		cow_vaccDao.close();
		return vaccines;
	}
	
	public void deleteCow_Vaccine (int cowId){
		cow_vaccDao.open();
		cow_vaccDao.deleteCow_Vaccine(cowId);
		cow_vaccDao.close();
	}
}
