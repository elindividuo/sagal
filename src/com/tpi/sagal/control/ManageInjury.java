package com.tpi.sagal.control;

import com.tpi.sagal.dao.InjuryDaoAdapter;

import android.content.Context;

public class ManageInjury {
	private InjuryDaoAdapter injuryDao;
	
	public ManageInjury (Context context){
		injuryDao = new InjuryDaoAdapter (context);
		injuryDao.open();
		injuryDao.close();
	}
	
	public void createInjury(String name, String abbreviation){
		injuryDao.open();
		injuryDao.createInjury(name, abbreviation);
		injuryDao.close();
	}
	
}
