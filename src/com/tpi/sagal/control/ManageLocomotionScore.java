package com.tpi.sagal.control;

import android.content.Context;

import com.tpi.sagal.dao.LocomotionScoreDaoAdapter;

public class ManageLocomotionScore {

	private LocomotionScoreDaoAdapter locomotionDao;
	
	public ManageLocomotionScore (Context context){
		locomotionDao = new LocomotionScoreDaoAdapter(context);
		locomotionDao.open();
		locomotionDao.close();
	}
	
	public void createLocomotion(int locomotion, String date, int cowId){
		locomotionDao.open();
		locomotionDao.createLocomotion(locomotion, date,cowId);
		locomotionDao.close();
	}
	
}
