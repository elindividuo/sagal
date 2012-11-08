package com.tpi.sagal.control;

import com.tpi.sagal.dao.FarmDaoAdapter;
import android.content.Context;

public class ManageFarm {
	
	private FarmDaoAdapter farmDao;
	
	public ManageFarm(Context context)
	{
		farmDao = new FarmDaoAdapter(context);
	}
	
	public void createFarm(String name, String address, String owner){
		farmDao.open();
		farmDao.createFarm(name, address, owner);
		farmDao.close();
	}
	
}
