package com.example.alfonso.era04;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Buscamos nuestros botones de Alto,Medio, Bajo

        Button BtnRecientes = (Button) findViewById(R.id.BtnRecientes) ;
        ImageButton BtnAlta = (ImageButton) findViewById(R.id.BtnAlta);
        ImageButton BtnMedia = (ImageButton) findViewById(R.id.BtnMedia);
        ImageButton BtnBaja = (ImageButton) findViewById(R.id.BtnBaja);

        setTitle("ERA");


        //Al pulsar uno de estos botones cargara la actividad listado_formulas con el valor de cada boton. (Alto,Medio,Bajo)
        BtnAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(Inicio.this, FormulasPrioridad.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("Prioridad", "Alta");

                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });

        BtnMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(Inicio.this, FormulasPrioridad.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("Prioridad", "Media");

                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });

        BtnBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(Inicio.this, FormulasPrioridad.class);

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("Prioridad", "Baja");

                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });

        BtnRecientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos el Intent
                Intent intent =
                        new Intent(Inicio.this, FormulasRecientes.class);


                //Iniciamos la nueva actividad
                startActivity(intent);

            }
        });





    }




}



/*



import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Sólo se debe entrar en esta pantalla si se han creado las tablas con las formulas.

        //Creamos un boton para las formulas Alta.


        //Creamos un boton para las formulas Media.


        //Creamos un boton para las formulas Baja



        //Creamos un boton para acceder a recientes.


        //Creamos un boton para acceder a configuracion


        //Creamos un boton para acceder a la ayuda.


        //Creamos un evento para los botones de Alta/Media/Baja.
        //Cuando se puse este boton con el valor Alta se cargará la pagina FormulasAltaPrioridad.
        //Cuando se puse este boton con el valor Media se cargará la pagina FormulasMediaPrioridad.
        //Cuando se puse este boton con el valor Baja se cargará la pagina FormulasBajaPrioridad.



    }

}
*/