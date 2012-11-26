package com.tpi.sagal.control;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import com.tpi.sagal.dao.LocomotionScoreDaoAdapter;
import com.tpi.sagal.entity.LocScoring;

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
	
	public ArrayList<LocScoring> readAllLocomotionScoringByCow(int cowId){
		locomotionDao.open();
		Cursor cursor = locomotionDao.readLocomotion();
		ArrayList<LocScoring> locScoring = new ArrayList<LocScoring>();
		int id;
		if (cursor.moveToFirst()){
			do
			{
			   id = cursor.getInt(cursor.getColumnIndex("cow_id"));
			   if (cowId == id){
				   int ls = cursor.getInt(cursor.getColumnIndex("locomotion_score"));
				   String date = cursor.getString(cursor.getColumnIndex("locomotion_date"));
				   LocScoring locomotion = new LocScoring(ls, date, cowId); 
				   locScoring.add(locomotion);
			   } 
			} while ( cursor.moveToNext());

       }
		Log.v("BLAH COWid - loc score", locScoring.get(0).getCowId()+" - "+locScoring.get(0).getLocomotionScoring());
		Log.v("BLAH COWid - loc score", locScoring.get(1).getCowId()+" - "+locScoring.get(1).getLocomotionScoring());
       cursor.close();
       locomotionDao.close();
       return locScoring;
	}
	
}
