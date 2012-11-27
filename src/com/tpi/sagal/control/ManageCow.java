package com.tpi.sagal.control;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tpi.sagal.dao.CowDaoAdapter;
import com.tpi.sagal.dao.HoofDaoAdapter;
import com.tpi.sagal.dao.ZoneDaoAdapter;
import com.tpi.sagal.entity.Cow;
import com.tpi.sagal.entity.Farm;

public class ManageCow {
	
	private CowDaoAdapter cowDao;
	private HoofDaoAdapter hoofDao;
	private ZoneDaoAdapter zoneDao;
	private ManageFarm mf;
	
	public ManageCow(Context context)
	{
		cowDao = new CowDaoAdapter(context);
		mf = new ManageFarm (context);
		hoofDao = new HoofDaoAdapter(context);
		zoneDao = new ZoneDaoAdapter(context);
		cowDao.open();
		cowDao.close();
	}
	
	public void createCow(int registry, String name, String breed, String birth, String tattoo, String problems, String finalDestination, String differentialDiag, String regimens,int locomotion,int motherId, int fatherId , int farmId){
		cowDao.open();
		cowDao.createCow(registry, name, breed, birth, tattoo, problems, finalDestination,differentialDiag,regimens ,locomotion, motherId, fatherId, farmId);
		Cursor cursorC = cowDao.readLastCow();
		hoofDao.open();
		zoneDao.open();
		if(cursorC.moveToFirst()){
			int cowId = cursorC.getInt(cursorC.getColumnIndex("_id"));
			for (int i = 1; i < 5; i++) {
				hoofDao.createHoof(cowId, i);
				Cursor cursorH = hoofDao.readLastHoof();
				if (cursorH.moveToFirst()) {
					int hoofId = cursorH.getInt(cursorH.getColumnIndex("_id"));
					for (int j = 0; j < 14; j++) {
						zoneDao.createZone(hoofId, j);
					}
				}
				cursorH.close();
			}
		}
		cursorC.close();
		zoneDao.close();
		hoofDao.close();
		cowDao.close();
	}
	
	public void createCow(int registry, String name, String breed, String birth, String tattoo, String problems, int farmId){
		cowDao.open();
		cowDao.createCow(registry, name, breed, birth, tattoo, problems, farmId);
		Cursor cursor = cowDao.readLastCow();
		hoofDao.open();
		if(cursor.moveToFirst()){
			int cowId = cursor.getInt(cursor.getColumnIndex("_id"));
			for (int i = 1; i < 5; i++) {
				hoofDao.createHoof(cowId, i);
			}
		}
		hoofDao.close();
		cowDao.close();
	}
	
	public int findRegistry(int registry, int farmId){
		cowDao.open();
		Cursor cursor = cowDao.findregistry(registry, farmId);
		if(cursor.moveToFirst()){
			int cowRegistry = cursor.getInt(cursor.getColumnIndex("cow_registry"));
	        cursor.close();
	        cowDao.close();
	        return cowRegistry;
		}
		return 0;		
	}
	
	
	public ArrayList<Cow> readAllCow(){
		cowDao.open();
		Cursor cursor = cowDao.readCow();
		ArrayList<Cow> cows = new ArrayList<Cow>();
		if (cursor.moveToFirst()){
			do
			{
			   int id = cursor.getInt(cursor.getColumnIndex("_id"));
               String name = cursor.getString(cursor.getColumnIndex("cow_name"));
               String breed = cursor.getString(cursor.getColumnIndex("cow_breed"));
               String tattoo = cursor.getString(cursor.getColumnIndex("cow_tattoo"));
               String problems = cursor.getString(cursor.getColumnIndex("cow_problems"));
               String birth = cursor.getString(cursor.getColumnIndex("cow_birthday"));
               int farmId = cursor.getInt(cursor.getColumnIndex("farm_id"));
               Farm farm = mf.searchFarm(farmId);
               cows.add( new Cow(id, name, breed, birth,tattoo,problems,farm));  
			} while ( cursor.moveToNext());

       }
       cursor.close();
       cowDao.close();
       return cows;
	}
	
	public ArrayList<Cow> readCowFromFarm(int id, int fatherId,int motherId, int farmId){
		cowDao.open();
		Cursor cursor = cowDao.readCowFromFarm(id,fatherId,motherId, farmId);
		ArrayList<Cow> cows = new ArrayList<Cow>();
		if (cursor.moveToFirst()){
			do
			{
			   int cowid = cursor.getInt(cursor.getColumnIndex("_id"));
               String name = cursor.getString(cursor.getColumnIndex("cow_name"));
               String breed = cursor.getString(cursor.getColumnIndex("cow_breed"));
               String tattoo = cursor.getString(cursor.getColumnIndex("cow_tattoo"));
               String problems = cursor.getString(cursor.getColumnIndex("cow_problems"));
               String birth = cursor.getString(cursor.getColumnIndex("cow_birthday"));
               Farm farm = mf.searchFarm(farmId);
               cows.add( new Cow(cowid, name, breed, birth,tattoo,problems,farm));  
			} while ( cursor.moveToNext());

       }
       cursor.close();
       cowDao.close();
       return cows;
	}
	
	
	public Cow searchCow(int id){
		cowDao.open();
		Cursor cursor = cowDao.searchCow(id);
		if(cursor.moveToFirst()){
			int cId = cursor.getInt(cursor.getColumnIndex("_id"));
			int registry = cursor.getInt(cursor.getColumnIndex("cow_registry"));
	        String name = cursor.getString(cursor.getColumnIndex("cow_name"));
	        String breed = cursor.getString(cursor.getColumnIndex("cow_breed"));
	        String birthday = cursor.getString(cursor.getColumnIndex("cow_birthday"));
	        String tattoo = cursor.getString(cursor.getColumnIndex("cow_tattoo"));
	        String problems = cursor.getString(cursor.getColumnIndex("cow_problems"));
	        String regimens = cursor.getString(cursor.getColumnIndex("cow_regimens"));
	        String diffDiag = cursor.getString(cursor.getColumnIndex("cow_dif_diag"));
	        String finalDestination = cursor.getString(cursor.getColumnIndex("cow_finaldestination"));
	        int motherId = cursor.getInt(cursor.getColumnIndex("cow_mother"));
	        int fatherId = cursor.getInt(cursor.getColumnIndex("cow_father"));
	        int locomotion = cursor.getInt(cursor.getColumnIndex("cow_locomotion"));
	        int farmId = cursor.getInt(cursor.getColumnIndex("farm_id"));
	        
	        Cow father = searchCow(fatherId);
	        Cow mother = searchCow(motherId);
	        
            Farm farm = mf.searchFarm(farmId);
	        cursor.close();
	        cowDao.close();
	        return new Cow(cId,registry,name,breed, birthday, tattoo,father,mother,finalDestination,problems,locomotion, farm,diffDiag,regimens);
		}
		return null;
	}
	
	public int[] getLocomotionScoring (int id_farm) {
		ArrayList<Cow> cows = readCowsFromFarmLS(id_farm);
		ArrayList<Integer> locomotionScoring = new ArrayList<Integer>();
		for (Cow c:cows){
			locomotionScoring.add(c.getLocomotionScoring());
		}
		int ls[] = new int [5];
		for (int i = 0; i<ls.length; i++)
			ls[i] = 0;
		//Log.v("BLAH", "id_farm" + id_farm);
		//Log.v("BLAH", "size" + locomotionScoring.size());
		for (int j = 0; j < locomotionScoring.size(); j++){
			//ls[j-1]++;
			Log.v("Locomotio Scoring ", j +" "+locomotionScoring.get(j)+"");
			switch ((int)locomotionScoring.get(j)){
			case 1: ls[0]++;break;
			case 2: ls[1]++;break;
			case 3: ls[2]++;break;
			case 4: ls[3]++;break;
			case 5: ls[4]++;break;
			}
		}
		//Log.v("BLAH", "4");
		//Log.v("blah", ls.length+" tamaño ls");
		return ls;
	}

	
	public double[] getIdealLocomotionScoringFromCattle (int farm_id){
		int cattleSize = readCowsFromFarmLS(farm_id).size();
		Log.v("BLAH", "cattle size" + cattleSize);
		double ls1Percent = cattleSize * 0.50;
		double ls2Percent = cattleSize * 0.25;
		double ls3Percent = cattleSize * 0.10;
		double ls4Percent = cattleSize * 0.10;
		double ls5Percent = cattleSize * 0.05;
		
		double [] idealLocScor = new double [5];
		idealLocScor[0] = ls1Percent;
		idealLocScor[1] = ls2Percent;
		idealLocScor[2] = ls3Percent;
		idealLocScor[3] = ls4Percent;
		idealLocScor[4] = ls5Percent;
		
		return idealLocScor;
	}	

	
	public ArrayList<Cow> readCowsFromFarmLS(int farm_id){
		cowDao.open();
		Cursor cursor = cowDao.readCowFromFarm(farm_id);
		ArrayList<Cow> cows = new ArrayList<Cow>();
		if(cursor.moveToFirst()){
			do{
				int cId = cursor.getInt(cursor.getColumnIndex("_id"));
				int registry = cursor.getInt(cursor.getColumnIndex("cow_registry"));
		        String name = cursor.getString(cursor.getColumnIndex("cow_name"));
		        String breed = cursor.getString(cursor.getColumnIndex("cow_breed"));
		        String birthday = cursor.getString(cursor.getColumnIndex("cow_birthday"));
		        String tattoo = cursor.getString(cursor.getColumnIndex("cow_tattoo"));
		        String problems = cursor.getString(cursor.getColumnIndex("cow_problems"));
		        String regimens = cursor.getString(cursor.getColumnIndex("cow_regimens"));
		        String diffDiag = cursor.getString(cursor.getColumnIndex("cow_dif_diag"));
		        String finalDestination = cursor.getString(cursor.getColumnIndex("cow_finaldestination"));
		        int motherId = cursor.getInt(cursor.getColumnIndex("cow_mother"));
		        int fatherId = cursor.getInt(cursor.getColumnIndex("cow_father"));
		        int locomotion = cursor.getInt(cursor.getColumnIndex("cow_locomotion"));
		        int farmId = cursor.getInt(cursor.getColumnIndex("farm_id"));
		        
		        Cow father = searchCow(fatherId);
		        Cow mother = searchCow(motherId);
		        
	            Farm farm = mf.searchFarm(farmId);
	            
	            cows.add(new Cow(cId,registry,name,breed, birthday, tattoo,father,mother,finalDestination,problems,locomotion, farm,diffDiag,regimens));
		        
			}while(cursor.moveToNext());
		}
		cursor.close();
        cowDao.close();
        return cows;
	}
	
	
	public ArrayList<Cow> readAllCowFromFarm(int farm_id){
		cowDao.open();
		Cursor cursor = cowDao.readCowFromFarm(farm_id);
		ArrayList<Cow> cows = new ArrayList<Cow>();
		if (cursor.moveToFirst()){
			do
			{
				int id = cursor.getInt(cursor.getColumnIndex("_id"));
				String name = cursor.getString(cursor.getColumnIndex("cow_name"));
				String breed = cursor.getString(cursor.getColumnIndex("cow_breed"));
				String tattoo = cursor.getString(cursor.getColumnIndex("cow_tattoo"));
				String problems = cursor.getString(cursor.getColumnIndex("cow_problems"));
				String birth = cursor.getString(cursor.getColumnIndex("cow_birthday"));
				int farmId = cursor.getInt(cursor.getColumnIndex("farm_id"));
				Farm farm = mf.searchFarm(farmId);
				cows.add( new Cow(id, name, breed, birth,tattoo,problems,farm));               
			} while ( cursor.moveToNext());

       }
       cursor.close();
       cowDao.close();
       return cows;
	}
	
	
	
	public void deleteCow(int id){
		cowDao.open();
		cowDao.deleteCow(id);
		cowDao.close();
	}
	
	public void updateCow(int id,int registry ,String name, String breed, String birth, String tattoo, String problems, String finalDestination, int locomotion,int motherId, int fatherId , int farmId){
		cowDao.open();
		cowDao.updateCow(id,registry, name, breed, birth, tattoo, problems, finalDestination, locomotion, motherId, fatherId, farmId);
		cowDao.close();
	}
	
	public void updateCow(int id,int registry ,String name, String breed, String birth, String tattoo, String problems, String finalDestination, int motherId, int fatherId , int farmId){
		cowDao.open();
		cowDao.updateCow(id,registry, name, breed, birth, tattoo, problems, finalDestination, motherId, fatherId, farmId);
		cowDao.close();
	}
	
	public void updateCow(int id, String regimens, String diffDiag){
		cowDao.open();
		cowDao.updateCow(id,regimens,diffDiag);
		cowDao.close();
	}
	public void updateCow(int id, int locomotionScore){
		cowDao.open();
		cowDao.updateCow(id,locomotionScore);
		cowDao.close();
	}
	
	
 	
}