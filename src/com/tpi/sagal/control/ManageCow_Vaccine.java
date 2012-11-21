package com.tpi.sagal.control;

import android.content.Context;
import com.tpi.sagal.dao.Cow_VaccineDaoAdapter;

public class ManageCow_Vaccine {
	private Cow_VaccineDaoAdapter cow_vaccDao;

	public ManageCow_Vaccine(Context context) {
		cow_vaccDao = new Cow_VaccineDaoAdapter(context);
		cow_vaccDao.open();
		cow_vaccDao.close();
	}
}
