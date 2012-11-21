package com.tpi.sagal.control;

import android.content.Context;

import com.tpi.sagal.dao.HoofDaoAdapter;

public class ManageHoof {
	private HoofDaoAdapter hoofDao;
	
	public ManageHoof (Context context){
		hoofDao = new HoofDaoAdapter(context);
		hoofDao.open();
		hoofDao.close();
	}
}
