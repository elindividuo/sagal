package com.tpi.sagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivityViewFarms extends Activity implements View.OnClickListener{

	ListView listViewFarms;
	Button createFarm;
	Intent i;
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_view_farms);
        initialize();
 
        // First paramenter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          //android.R.layout.simple_list_item_single_choice, R.layout.listview_farm_row, values);

        // Assign adapter to ListView
        //listViewFarms.setAdapter(new ArrayAdapter<String>(this,R.layout.listview_farm_row,values)); 
        //listViewFarms.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice,values));
        //listViewFarms.setChoiceMode(ListView.CHOICE_MODE_SINGLE); 
        
        listViewFarms.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
			  int position, long id) {
				//position++;
				//Toast.makeText(getApplicationContext(),
			    //"Click ListItem Number " + position, Toast.LENGTH_LONG)
			    //.show();
				Intent farmDetails = new Intent("com.tpi.sagal.FARMDETAILS");
				startActivity(farmDetails);
			  }
			}); 
    }
    
    
    public void initialize(){
    	createFarm = (Button)findViewById(R.id.bCreateFarm_MainView);
        listViewFarms = (ListView) findViewById(R.id.lvFarms);
        String[] values = new String[] { "Rosales", "La Pintada", "Casa Vieja", "Las Palmas", "Mi Finca", "El Rancho"};
        listViewFarms.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1,values));
        createFarm.setOnClickListener(this);
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_activity_view_farms, menu);
        return true;
    }
    
    
    
    /**Called when the user choose a farm from the List*/
    public void viewDetails(View view){
    	/*
    	Intent intent = new Intent (this, FarmDetails.class);
    	ListView farmListView = (ListView)findViewById(R.id.lvFarms);
    	EditText editText = (EditText)findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    	*/
    }

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.bCreateFarm_MainView:
			i = new Intent("com.tpi.sagal.CREATEFARM");
	    	startActivity(i);
			break;
		}
	}
}