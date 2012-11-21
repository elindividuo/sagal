package com.tpi.sagal.control;

import android.content.Context;

import com.tpi.sagal.dao.ZoneDaoAdapter;

public class ManageZone {
	
private ZoneDaoAdapter zoneDao;
	
	public ManageZone (Context context){
		zoneDao = new ZoneDaoAdapter(context);
		zoneDao.open();
		zoneDao.close();
	}
}
