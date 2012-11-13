package com.tpi.sagal;

import java.util.ArrayList;
import java.util.Collections;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class InjuryRegistration extends Activity implements View.OnTouchListener {

	private CharSequence[] injuries = {"(C) Pezuña de Tirabuzón","(D) Dermatitis Digital",
			"(E) Erosión del Talón","(F) Gabarro o Flemón",
			"(G) Fisura o Grieta Horizontal","(H) Hemorragia de la Suela",
			"(I) Dermatitis Interdigital","(K) Hiperplasia Interdigital",
			"(T) Úlcera de Punta", "(U) Úlcera de la Suela",
			"(V) Fisura o Grieta Vertical","(W) Lesión de la Línea Blanca",
			"(X) Fisura Axial", "(Z) Suela Delgada"};
	private ArrayList<CharSequence> selectedInjuries = new ArrayList<CharSequence>();

	Button selectInjuries, addNotes, ok;
	ImageButton hoof;
	TextView sectionNumber;
	
	int[][] matrizXY = {{184,25,175,238,214,238,209,25},//Zona 0
            {184,25,132,13,78,70,99,82,156,38,174,112},//Zona 1
            {209,26,217,113,230,32,297,83,316,69,255,13},
            {6,171,78,70,99,82,28,175},//Zona 2
            {297,83,316,69,389,175,365,170},
            {6,171,28,175,48,190,50,260,5,277},// Zona 3
            {349,189,365,170,389,175,390,282,342,261},
            {50,260,48,190,110,136,174,183,175,238},//Zona 4
            {214,237,217,185,288,136,349,189,342,260},
            {28,175,99,82,156,38,174,113,174,182,109,136,48,190},//Zona 5
            {217,186,217,113,230,32,297,83,365,170,349,189,288,136},
            {5,277,50,260,175,238,176,308,56,334},//Zona 6
            {219,309,214,237,342,261,389,282,340,335},
            {56,334,176,308,175,238,214,237,219,309,340,335} //Zona 10
	};
	ArrayList<Poligono> poligonos = new ArrayList<Poligono>(); //Los poligonos de las zonas
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_injury_registration);
		
		ArrayAdapter spinner_adapter= ArrayAdapter.createFromResource(this, R.array.hoofSections, android.R.layout.simple_spinner_item);
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				
		hoof = (ImageButton) findViewById(R.id.ibHoofSections);
		selectInjuries = (Button)findViewById(R.id.bSelectInjuries);
		addNotes = (Button)findViewById(R.id.bAddNotes);
		ok = (Button)findViewById(R.id.bOkInjuryRegistration);
		sectionNumber = (TextView) findViewById(R.id.bSectionNumber);
		
		hoof.setOnTouchListener(this);
		
		for (int i = 0; i < matrizXY.length; i++) {
			ArrayList<Punto> puntos = new ArrayList<Punto>();
		    for (int j = 0; j < matrizXY[i].length-1; j+=2) {
		    	puntos.add(new Punto(matrizXY[i][j], matrizXY[i][j+1]));
			}
		    poligonos.add(new Poligono(puntos));
		}
		
		selectInjuries.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showSelectInjuries();
			}
		});
		addNotes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addNotes(v);
			}
		});
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"¡Listo! Enfermedades registradas.", Toast.LENGTH_LONG)
					    .show();
				ok(v);
			}
		});
		
	}
	
	
	public void showSelectInjuries(){
		boolean[] checkedInjuries = new boolean[injuries.length];
		
		for(int i = 0; i < injuries.length; i++)
			checkedInjuries[i] = selectedInjuries.contains(injuries[i]);
		
		DialogInterface.OnMultiChoiceClickListener injuriesDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
					selectedInjuries.add(injuries[which]);
				else
					selectedInjuries.remove(injuries[which]);
				onChangeSelectedInjuries();
			}
		};
		
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Agregar enfermedades: ");
		builder.setMultiChoiceItems(injuries, checkedInjuries, injuriesDialogListener);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		AlertDialog dial = builder.create();
		dial.show();
		
	}
	
	
	public void onChangeSelectedInjuries(){
		StringBuilder stringBuilder = new StringBuilder();

		for(CharSequence injury : selectedInjuries)
			stringBuilder.append(injury + ",");
		
		Toast.makeText(getApplicationContext(),
				stringBuilder.toString(), Toast.LENGTH_LONG)
			    .show();
	}
	
	public void addNotes(View v){
		Intent intent = new Intent ("com.tpi.sagal.ADDNOTES");
		startActivity(intent);
	}
	
	public void ok(View v){
		Intent intent = new Intent ("com.tpi.sagal.SELECTHOOF");
		startActivity(intent);
	}
	
	public boolean onTouch(View view, MotionEvent event) {
		//int eventPadTouch = event.getAction();
		int iX = (int)event.getX();
		int iY = (int)event.getY();
		int i;
		sectionNumber.setText(iX + " " + iY);
		for (i = 0; i < poligonos.size(); i++) {
			if (poligonos.get(i).rellenar(iX, iY)) break;
		}
		switch (i) {
			case 0: sectionNumber.setText("CERO"); break;
			case 1: sectionNumber.setText("1"); break;
			case 2: sectionNumber.setText("1"); break;
			case 3: sectionNumber.setText("2"); break;
			case 4: sectionNumber.setText("2"); break;
			case 5: sectionNumber.setText("3"); break;
			case 6: sectionNumber.setText("3"); break;
			case 7: sectionNumber.setText("4"); break;
			case 8: sectionNumber.setText("4"); break;
			case 9: sectionNumber.setText("5"); break;
			case 10: sectionNumber.setText("5"); break;
			case 11: sectionNumber.setText("6"); break;
			case 12: sectionNumber.setText("6"); break;
			case 13: sectionNumber.setText("10"); break;
			default: sectionNumber.setText("NO"); break;
		}
		return false;
	}

}

class Arista implements Comparable{

	  int x1, y1; //Punto inicial
	  int x2, y2; //Punto final
	  float m; //Inversa de la pendiente
	  
	  int minY, minX; //Menor valor de Y, y valor de X que lo acompaña
	  int maxY;
	  
	  Arista( int x1, int y1, int x2, int y2 ){
	    this.x1 = x1;
	    this.y1 = y1;
	    this.x2 = x2;
	    this.y2 = y2;
	    
	    if (y1 != y2)
	      m = (float)(x1 - x2) / (float)(y1 - y2);
	      
	    if (this.y1 < this.y2) {
	      minY = y1;
	      maxY = y2;
	      minX = x1;
	    } else {
	      minY = y2;
	      maxY = y1;
	      minX = x2;
	    }    
	  }
	  
	  public int compareTo(Object o) {
	    Arista a = (Arista) o;
	    return this.compareToY(a);
	  }
	  
	  public int compareToY (Arista a) {
	      
	    if (this.minY == a.minY)
	      return this.compareToX(a);
	    else if (this.minY > a.minY)
	      return 1;
	    else
	      return -1;
	    
	  }

	  public int compareToX (Arista a) {
	    
	    if (this.minX == a.minX)
	      return this.compareToM(a);
	    else if (this.minX > a.minX)
	      return 1;
	    else
	      return -1;
	  }
	  
	  public int compareToM (Arista a) {
	    
	    if (this.m == a.m)
	      return 0;
	    else if (this.m > a.m)
	      return 1;
	    else
	      return -1;
	    
	  }

	}

class EdgeTable implements Comparable {
	  
	  float y;
	  float x;
	  float m;

	  EdgeTable( int x, int y, float m ){
	    this.x = x;
	    this.y = y; 
	    this.m = m;
	  }
	  
	  EdgeTable( Arista a ){
	    this.x = a.minX;
	    this.y = a.maxY; 
	    this.m = a.m;
	  }
	  
	  public int compareTo(Object o) {
	    EdgeTable e = (EdgeTable) o;
	    return this.compareToX(e);
	  }
	  
	  public int compareToX (EdgeTable e) {
	      
	    if (this.x == e.x)
	      return this.compareToM(e);
	    else if (this.x > e.x)
	      return 1;
	    else
	      return -1;
	    
	  }
	  
	  public int compareToM (EdgeTable e) {
	      
	    if (this.m == e.m)
	      return 0;
	    else if (this.m > e.m)
	      return 1;
	    else
	      return -1;
	    
	  }
	  
	  
	  
	}

class Punto {
	  int x, y;
	  Punto (int x, int y) {
	    this.x = x;
	    this.y = y;
	  }
	}

class Poligono{
	  
	  ArrayList<Punto> puntos;
	  ArrayList<Punto> transformados;
	  
	  ArrayList<Arista> aristas;
	  
	  int maxY = 0;
	  
	  public Poligono(ArrayList<Punto> puntos) {
	    this.puntos = puntos;
	    construirAristas(puntos);
	  }
	    
	  public void construirAristas(ArrayList<Punto> a){
	    
	    ArrayList<Punto> arreglo = a;
	    aristas = new ArrayList<Arista>();
	    
	    int i = 0;
	    int j;
	    int tam = arreglo.size();
	    
	    do {
	      
	      j = (i+1)%tam;
	      aristas.add(new Arista(arreglo.get(i).x, arreglo.get(i).y, arreglo.get(j).x, arreglo.get(j).y));
	      i = j;
	      if (arreglo.get(i).y > maxY)
	        maxY = (int)(arreglo.get(i).y);
	      
	    } while (i != 0);
	          
	  }
	  
	boolean rellenar(int mouseX, int mouseY) {
	  
	  Collections.sort(aristas);

	  ArrayList<ArrayList<EdgeTable>> ET = new ArrayList<ArrayList<EdgeTable>>();
	  ArrayList<Integer> coordY = new ArrayList<Integer>();

	  ArrayList<EdgeTable> nuevo = new ArrayList<EdgeTable>();

	  //Agrega la primera arista si no es horizontal
	  if (aristas.get(0).y1 != aristas.get(0).y2) {
	    nuevo.add(new EdgeTable(aristas.get(0)));
	  }

	  for (int i = 1; i < aristas.size(); i++) {

	    if (aristas.get(i-1).minY != aristas.get(i).minY) {

	      if (nuevo.size() > 0) {
	        ET.add(nuevo);
	        coordY.add(aristas.get(i-1).minY);
	      }
	      
	      nuevo = new ArrayList<EdgeTable>();
	      
	    }

	    if (aristas.get(i).y1 != aristas.get(i).y2)
	      nuevo.add(new EdgeTable(aristas.get(i)));
	  }

	  if (nuevo.size() > 0) {
	    ET.add(nuevo);
	    coordY.add(aristas.get(aristas.size()-1).minY);
	  }
	  
	  coordY.add(maxY);
	  
	  ArrayList<EdgeTable> AET = new ArrayList<EdgeTable>();
	   
	  for(int y = 0; y < coordY.size()-1; y++) {

	    for (EdgeTable e : ET.get(y)) {
	      AET.add(e);
	    }

	    for (int i = coordY.get(y); i < coordY.get(y+1); i+=1) {
	      
	      Collections.sort(AET);
	           
	      for (int k = 0; k < AET.size(); k++) {
	        if (AET.get(k).y == i) {
	          AET.remove(k);
	          k--;
	        }
	      }
	      
	        boolean bit = true;
	        for (int k = 0; k < AET.size()-1; k++) {
	          if(bit)
	        	if (mouseX >= (int)(AET.get(k).x) && mouseX <= AET.get(k+1).x && mouseY == i)  return true;
	            //for (int j = (int)(AET.get(k).x); j < AET.get(k+1).x; j++) {
	              //if (mouseX == j && mouseY == i) { return true;}
	              //point (j, i);
	            //}
	          bit = !bit;
	        }   
	            
	      for (EdgeTable e : AET)
	        e.x += e.m;
	      
	    }         
	      
	  }
	  return false;
	}
	  
	  
	  
	}