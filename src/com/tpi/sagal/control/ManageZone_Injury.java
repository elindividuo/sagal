package com.tpi.sagal.control;

import android.content.Context;
import com.tpi.sagal.dao.Zone_InjuryDaoAdapter;

public class ManageZone_Injury {

	private Zone_InjuryDaoAdapter zone_injDao;
	
	public ManageZone_Injury (Context context){
		zone_injDao = new Zone_InjuryDaoAdapter(context);
		zone_injDao.open();
		zone_injDao.close();
	}
}
