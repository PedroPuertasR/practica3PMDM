package com.pedropuertas.pmdm3p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ProgressActivity extends AppCompatActivity implements View.OnClickListener {

    private Button botonVolver;
    private Button botonGuardar;
    private ProgressBar pgBarra;

    private ArrayList<String> lista;
    private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        botonVolver = (Button) findViewById(R.id.btnVolverP);
        botonGuardar = (Button) findViewById(R.id.btnGuardar);
        pgBarra = (ProgressBar) findViewById(R.id.pbBarra);

        contador = 0;
        prog();

        botonVolver.setOnClickListener(this);
        botonGuardar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent inte;

        if(v.getId() == R.id.btnVolverP){
            if(getIntent() != null){
                inte = new Intent(ProgressActivity.this, MainActivity.class);
                Intent intent = getIntent();
                lista = intent.getStringArrayListExtra("lista");
                if(lista != null){
                    inte.putStringArrayListExtra("lista", lista);
                    Log.i("AL", "Transportando lista a otro activity.");
                }
            }else{
                inte = new Intent(ProgressActivity.this, MainActivity.class);
            }

            startActivity(inte);
        }else{
            String completado = "";

            if(pgBarra.getProgress() == 100){
                completado = "Completado";
            }else{
                completado = "No completado";
                Toast.makeText(ProgressActivity.this, "El progreso no ha terminado", Toast.LENGTH_SHORT).show();
                Log.i("PB", "Cuenta atras no terminada");
            }

            if(getIntent() != null){
                Intent intent = getIntent();
                lista = intent.getStringArrayListExtra("lista");

                if(lista != null){
                    lista.set(4, completado);

                    inte = new Intent(ProgressActivity.this, MainActivity.class);
                    inte.putStringArrayListExtra("lista", lista);
                    Log.i("AL", "Transportando lista a otro activity.");
                }else{
                    lista = new ArrayList<String>();

                    lista.add("");
                    lista.add("");
                    lista.add("");
                    lista.add("");
                    lista.add(completado);
                    lista.add("");
                    lista.add("");

                    inte = new Intent(ProgressActivity.this, MainActivity.class);

                    inte.putStringArrayListExtra("lista", lista);
                    Log.i("AL", "Transportando lista a otro activity.");
                }

                startActivity(inte);
            }else{

                lista = new ArrayList<String>();

                lista.add("");
                lista.add("");
                lista.add("");
                lista.add("");
                lista.add(completado);
                lista.add("");
                lista.add("");

                inte = new Intent(ProgressActivity.this, MainActivity.class);

                inte.putStringArrayListExtra("lista", lista);
                Log.i("AL", "Transportando lista a otro activity.");

                startActivity(inte);
            }
        }

    }

    public void prog(){
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                contador++;
                pgBarra.setProgress(contador);

                if(contador == 100){
                    t.cancel();
                }
            }
        };

        t.schedule(tt, 0, 100);
    }
}