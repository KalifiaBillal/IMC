package com.example.imc01;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends Activity {
	
	//private final String defaut = "toto";

	Button calculerIMC = null; 	
	Button raz = null; 
	
	EditText poids = null;		
	EditText taille = null;
	
	TextView result = null;
	RadioGroup group = null;
	
	float imc = 0;
	
	//IMC02
	Button interpretation = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{ 
		super.onCreate(savedInstanceState); //super. permet d'en référer à une méthode mère
		setContentView(R.layout.activity_main);
		calculerIMC = (Button)findViewById(R.id.calcul);
		raz 	=(Button)findViewById(R.id.raz);
		taille	=(EditText)findViewById(R.id.taille);
		poids 	=(EditText)findViewById(R.id.poids);
		
		group 	=(RadioGroup)findViewById(R.id.group);
		//result	=(TextView)findViewById(R.id.result);
		
		//IMC02
		interpretation = (Button)findViewById(R.id.interpretation);
		
		//On affecte un listernet adapté aux évènements qui peuvent se produire
		calculerIMC.setOnClickListener(envoyerListener);
		raz.setOnClickListener(razListener);
		taille.addTextChangedListener(textWatcher);
		poids.addTextChangedListener(textWatcher);
		
		
		
		//On accède 
		interpretation.setOnClickListener(interpretationListener);
	}

	//Evènement onClick sur le bouton "calculerIMC"
private OnClickListener envoyerListener = new OnClickListener()
{
	@Override
	public void onClick(View v)
	{
		
		if (taille.getText().toString().isEmpty() || poids.getText().toString().isEmpty()){
			
			//Message d'erreur si un des champs est vide.
			Resources message = getResources();
			
			Toast.makeText(MainActivity.this, message.getString(R.string.message_erreur_test), Toast.LENGTH_LONG).show();
		}
		else{
		
			String t = taille.getText().toString();
			String p = poids.getText().toString();
			float tValue = Float.valueOf(t);
			float pValue = Float.valueOf(p);
			
			if(group.getCheckedRadioButtonId()== R.id.radio2)
			{
				tValue = tValue/100;
			}
			tValue = (float)Math.pow(tValue, 2);
			imc = pValue/tValue;
			imc = (float)Math.round(imc*100)/100;
			//result.setText("Ton IMC est" + String.valueOf(imc));
			
			Intent i ; // déclaration d'une variable i, de type Intent
			i = new Intent(getApplicationContext(), Conseil.class);
			i.putExtra("imc", String.valueOf(imc));
			startActivity(i);
		}
	}
};

private OnClickListener razListener = new OnClickListener()
{
	@Override
	public void onClick(View v)
	{
		poids.getText().clear();
		taille.getText().clear();
		//result.setText(defaut);
	}
};


private OnClickListener interpretationListener = new OnClickListener()
{
	@Override
	public void onClick(View v)
	{
		Intent i ; // déclaration d'une variable i, de type Intent
		i = new Intent(getApplicationContext(), Interpretation.class);
		 startActivity(i); // Interpretation est le nom de la classe associée à l'activité d'affichage de l'interprétation de l'IMC. 
	}
};

private TextWatcher textWatcher = new TextWatcher()
 {
	 @Override
	 public void onTextChanged(CharSequence s, int start, int before, int count)
	 {
		 //result.setText(defaut);
	 }
	 
	 @Override
	 public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	 
	 @Override
	 public void afterTextChanged(Editable s){}

 };
 


//Savegarde et restauration
@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
    Log.w("P2", "onSaveInstanceState");
    savedInstanceState.putFloat("imc", imc);
    super.onSaveInstanceState(savedInstanceState);
}

@Override
public void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    imc = savedInstanceState.getFloat("imc");
    //result.setText(Float.toString(savedInstanceState.getFloat("imc")));
    Log.w("P2", "onRestoreInstanceState");
}
}

