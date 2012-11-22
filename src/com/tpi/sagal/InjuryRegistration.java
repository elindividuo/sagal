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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class InjuryRegistration extends Activity implements
		View.OnTouchListener {

	private CharSequence[] injuries = { "(C) Pezuña de Tirabuzón",
			"(D) Dermatitis Digital", "(E) Erosión del Talón",
			"(F) Gabarro o Flemón", "(G) Fisura o Grieta Horizontal",
			"(H) Hemorragia de la Suela", "(I) Dermatitis Interdigital",
			"(K) Hiperplasia Interdigital", "(T) Úlcera de Punta",
			"(U) Úlcera de la Suela", "(V) Fisura o Grieta Vertical",
			"(W) Lesión de la Línea Blanca", "(X) Fisura Axial",
			"(Z) Suela Delgada" };
	private ArrayList<CharSequence> selectedInjuries = new ArrayList<CharSequence>();

	Button selectInjuries, ok;
	ImageButton hoof;
	TextView sectionNumber;
	ImageView hoofZone;

	int[][] matrizXY = {
			{ 184, 25, 174, 112, 175, 238, 214, 237, 217, 185, 217, 113, 209,
					25 },// Zona 0
			{ 184, 25, 164, 9, 132, 13, 78, 70, 99, 82, 156, 38, 174, 112 },// Zona
																			// 1
																			// LEFT
			{ 209, 26, 217, 113, 230, 32, 297, 83, 316, 69, 255, 13, 220, 11 },// Zona
																				// 1
																				// RIGHT
			{ 6, 171, 78, 70, 99, 82, 28, 175 },// Zona 2 LEFT
			{ 297, 83, 316, 69, 389, 175, 365, 170 },// Zona 1 RIGHT
			{ 6, 171, 28, 175, 48, 190, 50, 260, 5, 277 },// Zona 3 LEFT
			{ 349, 189, 365, 170, 389, 175, 390, 282, 342, 261 },// Zona 3 RIGHT
			{ 50, 260, 48, 190, 110, 136, 174, 183, 175, 238 },// Zona 4 LEFT
			{ 214, 237, 217, 185, 288, 136, 349, 189, 342, 260 },// Zona 4 RIGHT
			{ 28, 175, 99, 82, 156, 38, 174, 113, 174, 182, 109, 136, 48, 190 },// Zona
																				// 5
																				// LEFT
			{ 217, 186, 217, 113, 230, 32, 297, 83, 365, 170, 349, 189, 288,
					136 },// Zona 5 RIGHT
			{ 5, 277, 50, 260, 175, 238, 176, 308, 56, 334 },// Zona 6 LEFT
			{ 219, 309, 214, 237, 342, 261, 389, 282, 340, 335 },// Zona 6 RIGHT
			{ 56, 334, 176, 308, 175, 238, 214, 237, 219, 309, 340, 335 } // Zona
																			// 10
	};

	// El orden de los elementos de los arreglos es igual que el de la matrizXY:
	// 0, 1L, 1R, 2L, 2R, 3L, 3R, 4L, 4R, 5L, 5R, 6L, 6R, 10
	boolean[] lesiones = { false, false, false, true, false, false, true,
			false, false, true, false, false, false, false };
	int[] zones = { R.id.zone0, R.id.zone1L, R.id.zone1R, R.id.zone2L,
			R.id.zone2R, R.id.zone3L, R.id.zone3R, R.id.zone4L, R.id.zone4R,
			R.id.zone5L, R.id.zone5R, R.id.zone6L, R.id.zone6R, R.id.zone10 };
	int green[] = { R.drawable.g0, R.drawable.g1l, R.drawable.g1r,
			R.drawable.g2l, R.drawable.g2r, R.drawable.g3l, R.drawable.g3r,
			R.drawable.g4l, R.drawable.g4r, R.drawable.g5l, R.drawable.g5r,
			R.drawable.g6l, R.drawable.g6r, R.drawable.g10 };
	int red[] = { R.drawable.r0, R.drawable.r1l, R.drawable.r1r,
			R.drawable.r2l, R.drawable.r2r, R.drawable.r3l, R.drawable.r3r,
			R.drawable.r4l, R.drawable.r4r, R.drawable.r5l, R.drawable.r5r,
			R.drawable.r6l, R.drawable.r6r, R.drawable.r10 };

	ArrayList<Poligono> poligonos = new ArrayList<Poligono>(); // Los poligonos
																// de las zonas

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_injury_registration);

		ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource(this,
				R.array.hoofSections, android.R.layout.simple_spinner_item);
		spinner_adapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		hoof = (ImageButton) findViewById(R.id.ibHoofSections);
		selectInjuries = (Button) findViewById(R.id.bSelectInjuries);
		ok = (Button) findViewById(R.id.bOkInjuryRegistration);
		sectionNumber = (TextView) findViewById(R.id.bSectionNumber);

		hoof.setOnTouchListener(this);

		// Aquí se deben leer las lesiones de cada zona de la pezuña:
		// lesiones[i] = ?

		for (int i = 0; i <= 13; i++) {
			changeColor(i, zones[i], green[i], red[i]);
		}

		for (int i = 0; i < matrizXY.length; i++) {
			ArrayList<Punto> puntos = new ArrayList<Punto>();
			for (int j = 0; j < matrizXY[i].length - 1; j += 2) {
				puntos.add(new Punto(matrizXY[i][j], matrizXY[i][j + 1]));
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
		ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(),
						"¡Listo! Enfermedades registradas.", Toast.LENGTH_LONG)
						.show();
				ok(v);
			}
		});

	}

	public void showSelectInjuries() {
		boolean[] checkedInjuries = new boolean[injuries.length];

		for (int i = 0; i < injuries.length; i++)
			checkedInjuries[i] = selectedInjuries.contains(injuries[i]);

		DialogInterface.OnMultiChoiceClickListener injuriesDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked)
					selectedInjuries.add(injuries[which]);
				else
					selectedInjuries.remove(injuries[which]);
				onChangeSelectedInjuries();
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Agregar enfermedades: ");
		builder.setMultiChoiceItems(injuries, checkedInjuries,
				injuriesDialogListener);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		AlertDialog dial = builder.create();
		dial.show();

	}

	public void onChangeSelectedInjuries() {
		StringBuilder stringBuilder = new StringBuilder();

		for (CharSequence injury : selectedInjuries)
			stringBuilder.append(injury + ",");

		Toast.makeText(getApplicationContext(), stringBuilder.toString(),
				Toast.LENGTH_LONG).show();
	}

	public void addNotes(View v) {
		Intent intent = new Intent("com.tpi.sagal.ADDNOTES");
		startActivity(intent);
	}

	public void ok(View v) {
		Intent intent = new Intent("com.tpi.sagal.SELECTHOOF");
		startActivity(intent);
	}

	public boolean onTouch(View view, MotionEvent event) {
		// int eventPadTouch = event.getAction();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			int iX = (int) event.getX();
			int iY = (int) event.getY();
			int i;
			sectionNumber.setText(iX + " " + iY);
			for (i = 0; i < poligonos.size(); i++) {
				if (poligonos.get(i).rellenar(iX, iY))
					break;
			}

			// Aquí se cambia el color de la zona sólo por hacerle click, pero
			// debe depender de las lesiones que se registren
			if (i <= 13)
				changeColor(i, zones[i], green[i], red[i]);

			switch (i) {
			case 0:
				sectionNumber.setText("CERO");
				break;
			case 1:
				sectionNumber.setText("1L");
				break;
			case 2:
				sectionNumber.setText("1R");
				break;
			case 3:
				sectionNumber.setText("2L");
				break;
			case 4:
				sectionNumber.setText("2R");
				break;
			case 5:
				sectionNumber.setText("3L");
				break;
			case 6:
				sectionNumber.setText("3R");
				break;
			case 7:
				sectionNumber.setText("4L");
				break;
			case 8:
				sectionNumber.setText("4R");
				break;
			case 9:
				sectionNumber.setText("5L");
				break;
			case 10:
				sectionNumber.setText("5R");
				break;
			case 11:
				sectionNumber.setText("6L");
				break;
			case 12:
				sectionNumber.setText("6R");
				break;
			case 13:
				sectionNumber.setText("10");
				break;
			default:
				sectionNumber.setText("NO");
				break;
			}
			return true;
		}
		return false;
	}

	private void changeColor(int i, int zone, int green, int red) {
		hoofZone = (ImageView) findViewById(zone);
		if (lesiones[i]) {
			hoofZone.setImageResource(red);
			lesiones[i] = false;
		} else {
			hoofZone.setImageResource(green);
			lesiones[i] = true;
		}
	}

}

class Arista implements Comparable {

	int x1, y1; // Punto inicial
	int x2, y2; // Punto final
	float m; // Inversa de la pendiente

	int minY, minX; // Menor valor de Y, y valor de X que lo acompaña
	int maxY;

	Arista(int x1, int y1, int x2, int y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;

		if (y1 != y2)
			m = (float) (x1 - x2) / (float) (y1 - y2);

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

	public int compareToY(Arista a) {

		if (this.minY == a.minY)
			return this.compareToX(a);
		else if (this.minY > a.minY)
			return 1;
		else
			return -1;

	}

	public int compareToX(Arista a) {

		if (this.minX == a.minX)
			return this.compareToM(a);
		else if (this.minX > a.minX)
			return 1;
		else
			return -1;
	}

	public int compareToM(Arista a) {

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

	EdgeTable(int x, int y, float m) {
		this.x = x;
		this.y = y;
		this.m = m;
	}

	EdgeTable(Arista a) {
		this.x = a.minX;
		this.y = a.maxY;
		this.m = a.m;
	}

	public int compareTo(Object o) {
		EdgeTable e = (EdgeTable) o;
		return this.compareToX(e);
	}

	public int compareToX(EdgeTable e) {

		if (this.x == e.x)
			return this.compareToM(e);
		else if (this.x > e.x)
			return 1;
		else
			return -1;

	}

	public int compareToM(EdgeTable e) {

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

	Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Poligono {

	ArrayList<Punto> puntos;
	ArrayList<Punto> transformados;

	ArrayList<Arista> aristas;

	int maxY = 0;

	public Poligono(ArrayList<Punto> puntos) {
		this.puntos = puntos;
		construirAristas(puntos);
	}

	public void construirAristas(ArrayList<Punto> a) {

		ArrayList<Punto> arreglo = a;
		aristas = new ArrayList<Arista>();

		int i = 0;
		int j;
		int tam = arreglo.size();

		do {

			j = (i + 1) % tam;
			aristas.add(new Arista(arreglo.get(i).x, arreglo.get(i).y, arreglo
					.get(j).x, arreglo.get(j).y));
			i = j;
			if (arreglo.get(i).y > maxY)
				maxY = (int) (arreglo.get(i).y);

		} while (i != 0);

	}

	boolean rellenar(int mouseX, int mouseY) {

		Collections.sort(aristas);

		ArrayList<ArrayList<EdgeTable>> ET = new ArrayList<ArrayList<EdgeTable>>();
		ArrayList<Integer> coordY = new ArrayList<Integer>();

		ArrayList<EdgeTable> nuevo = new ArrayList<EdgeTable>();

		// Agrega la primera arista si no es horizontal
		if (aristas.get(0).y1 != aristas.get(0).y2) {
			nuevo.add(new EdgeTable(aristas.get(0)));
		}

		for (int i = 1; i < aristas.size(); i++) {

			if (aristas.get(i - 1).minY != aristas.get(i).minY) {

				if (nuevo.size() > 0) {
					ET.add(nuevo);
					coordY.add(aristas.get(i - 1).minY);
				}

				nuevo = new ArrayList<EdgeTable>();

			}

			if (aristas.get(i).y1 != aristas.get(i).y2)
				nuevo.add(new EdgeTable(aristas.get(i)));
		}

		if (nuevo.size() > 0) {
			ET.add(nuevo);
			coordY.add(aristas.get(aristas.size() - 1).minY);
		}

		coordY.add(maxY);

		ArrayList<EdgeTable> AET = new ArrayList<EdgeTable>();

		for (int y = 0; y < coordY.size() - 1; y++) {

			for (EdgeTable e : ET.get(y)) {
				AET.add(e);
			}

			Collections.sort(AET);

			for (int k = 0; k < AET.size(); k++) {
				if (AET.get(k).y == coordY.get(y)) {
					AET.remove(k);
					k--;
				}
			}

			for (int i = coordY.get(y); i < coordY.get(y + 1); i += 1) {

				boolean bit = true;
				for (int k = 0; k < AET.size() - 1; k++) {
					if (bit)
						if (mouseX >= (int) (AET.get(k).x)
								&& mouseX <= AET.get(k + 1).x && mouseY == i)
							return true;
					bit = !bit;
				}

				for (EdgeTable e : AET)
					e.x += e.m;

			}

		}
		return false;
	}

}