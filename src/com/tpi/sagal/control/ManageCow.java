package com.tpi.sagal.control;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import com.tpi.sagal.dao.CowDaoAdapter;
import com.tpi.sagal.entity.Cow;
import com.tpi.sagal.entity.Farm;

public class ManageCow {
	
	private CowDaoAdapter cowDao;
	private ManageFarm mf;
	
	public ManageCow(Context context)
	{
		cowDao = new CowDaoAdapter(context);
		mf = new ManageFarm (context);
		cowDao.open();
		cowDao.close();
	}
	
	public void createCow(int registry, String name, String breed, String birth, String tattoo, String problems, String finalDestination, String differentialDiag, String regimens,int locomotion,int motherId, int fatherId , int farmId){
		cowDao.open();
		cowDao.createCow(registry, name, breed, birth, tattoo, problems, finalDestination,differentialDiag,regimens ,locomotion, motherId, fatherId, farmId);
		cowDao.close();
	}
	
	public void createCow(int registry, String name, String breed, String birth, String tattoo, String problems, int farmId){
		cowDao.open();
		cowDao.createCow(registry, name, breed, birth, tattoo, problems, farmId);
		cowDao.close();
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
		ArrayList<Cow> cows = readAllCowFromFarm(id_farm);
		ArrayList<Integer> locomotionScoring = new ArrayList<Integer>();
		for (Cow c:cows){
			locomotionScoring.add(c.getLocomotionScoring());
		}
		
		int[] ls = new int [5];
		for (Integer i:locomotionScoring){
			ls[i-1]++;
		}
		
		return ls;
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
	
 	
}
