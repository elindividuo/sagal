package com.tpi.sagal.control;

import android.content.Context;

import com.tpi.sagal.dao.LimbDaoAdapter;

public class ManageLimb {

	private LimbDaoAdapter limbDao;
	
	public ManageLimb (Context context){
		limbDao = new LimbDaoAdapter(context);
		limbDao.open();
		limbDao.close();
	}
	
}
