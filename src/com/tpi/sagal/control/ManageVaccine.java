package com.tpi.sagal.control;

import android.content.Context;
import com.tpi.sagal.dao.VaccineDaoAdapter;

public class ManageVaccine {

	private VaccineDaoAdapter vaccineDao;
	
	public ManageVaccine (Context context){
		vaccineDao = new VaccineDaoAdapter(context);
		vaccineDao.open();
		vaccineDao.close();
	}
	
	public void createVaccine(String name){
		vaccineDao.open();
		vaccineDao.createVaccine(name);
		vaccineDao.close();
	}
}
