package com.example.imc01;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Conseil extends Activity {

	Button quitter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conseil);
		
		//On récupère l'IMC
		Bundle b = getIntent().getExtras();
		String imc = b.getString("imc");
		Float fimc = Float.valueOf(imc);
		
		TextView valeurImc = (TextView)findViewById(R.id.message_votre_imc);
		TextView msgImc = (TextView)findViewById(R.id.msgImc);
		TextView leConseil = (TextView)findViewById(R.id.leConseil);
		TextView votre_imc = (TextView)findViewById(R.id.message_votre_imc);
		
		//TextView inter_normale = (TextView)findViewById(R.id.inter_corpulence_normale);
				
		if (fimc < 16.5){
			//Dénutrition
			valeurImc.setText(getResources().getString(R.string.votre_imc) + imc);
			msgImc.setText(getResources().getString(R.string.message_denutrition));
			leConseil.setText(getResources().getString(R.string.conseil_denutrition));
			
		}else{
			if((fimc > 16.5) && (fimc < 18.5)){
				//Maigreur
				valeurImc.setText(getResources().getString(R.string.votre_imc) + imc);
				msgImc.setText(getResources().getString(R.string.message_maigreur));
				leConseil.setText(getResources().getString(R.string.conseil_maigreur));
			}else{
				if(fimc >= 18.5 && fimc < 25){
					//Corpulence normale
					valeurImc.setText(getResources().getString(R.string.votre_imc) + imc);
					msgImc.setText(getResources().getString(R.string.message_corpulence_normale));
					leConseil.setText(getResources().getString(R.string.conseil_corpulence_normale));
				}else{
					if(fimc >= 25 && fimc < 30){
						//Surpoids
						valeurImc.setText(getResources().getString(R.string.votre_imc) + imc);
						msgImc.setText(getResources().getString(R.string.message_surpoids));
						leConseil.setText(getResources().getString(R.string.conseil_surpoids));
					}else{
						if(fimc >= 30 && fimc < 35){
							//Obésité modérée
							valeurImc.setText(getResources().getString(R.string.votre_imc) + imc);
							msgImc.setText(getResources().getString(R.string.message_obesite_modere));
							leConseil.setText(getResources().getString(R.string.conseil_obesite_modere));
						}else{
							if(fimc >= 35 && fimc < 40){
								//Obésité sévère
								valeurImc.setText(getResources().getString(R.string.votre_imc) + imc);
								msgImc.setText(getResources().getString(R.string.message_obesite_severe));
								leConseil.setText(getResources().getString(R.string.conseil_obesite_severe));
							}else{
								if(fimc >= 40){
									//Obésité massive
									valeurImc.setText(getResources().getString(R.string.votre_imc) + imc);
									msgImc.setText(getResources().getString(R.string.message_obesite_massive));
									leConseil.setText(getResources().getString(R.string.conseil_obesite_massive));
								}
							}
						}
					}
				}
			}
		}
		//Toast.makeText(getApplicationContext(), "Votre IMC est :" +  imc, Toast.LENGTH_LONG).show();
		// déclaration du bouton d'action quitter et association avec un listener 
				quitter = (Button)findViewById(R.id.quitter);
				quitter.setOnClickListener(btnQuitter);
	}
	
	private OnClickListener btnQuitter = new OnClickListener()
	{
		public void onClick(View v)
		 {
			 Intent i = new Intent ();
			 setResult (Activity.RESULT_OK, i);
			 finish(); // arrêt de l'activité ==> on revient à l'activité précédente
		 }
	 };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conseil, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
