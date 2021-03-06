package com.example.alfonso.era04;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.alfonso.era04.Clases.Formula;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class CalcularFormula extends AppCompatActivity {

    String cadenaPrueba = "";
    int indiceCampoIncorrecto = 0 ;
    boolean existenCamposEdit = false;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_formula);

        final LinearLayout lm = (LinearLayout) findViewById(R.id.LytContenedor);


        //Lo primero es capturar la abreviatura de la formula que se nos pasa en un bundle
        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        final String valorRecibido = bundle.getString("IdFormula");

        //Se crea una formula con el id que nos han pasado.
        final Formula formulaActual = new Formula(valorRecibido, getApplicationContext());
        //Parametro parametro = new Parametro("2" , getApplicationContext());
        //Lista para los editText
        final List<EditText> allEds = new ArrayList<EditText>();
        //Lista para los checkbox
        final List<CheckBox> allChs = new ArrayList<CheckBox>();
        //Lista para los radial group
        final List<RadioGroup> allRgs = new ArrayList<RadioGroup>();

        //Se crea un parametro auxiliar para cuestiones de diseño con el TextView y el EditText
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);


        /*
        //Vamos a probar si se crea correctamente una formula lo meteremos todo en una cadena luego la mostramos por pantlla.
        String cadenaPrueba = "";
        //Mostramos el nombre de la formula
        cadenaPrueba = cadenaPrueba + "Nombre:" + formulaActual.getAbreviatura() + "\n";

        //Mostramos el nombre de sus parametros
        for (int i =0; i < formulaActual.contarParametros(); i++)
        {
            cadenaPrueba = cadenaPrueba + "Parametro" +i +":"+ formulaActual.getParametro(i).getNombre() + "\n";

            //Para cada parametro mostramos su criterio
            for(int j =0; j < formulaActual.getParametro(i).contarCriterios();j++)
            {
                cadenaPrueba = cadenaPrueba + "Con criterio" + formulaActual.getParametro(i).getCriterioPuntuacion(j).getCriterio() + "\n"  ;
            }

        }

        TextView textoPrueba = new TextView(this);
        textoPrueba.setText(cadenaPrueba);
        lm.addView(textoPrueba);
        */

        /* Ahora tenemos que mostrar todos los parametros de cada formula.
        El formato utilizado será Nombre de parametro, Capturador del dato (caja de texto, botones radiales o check)
        //Creamos un linear layout para cada uno de los parametros
        //Dependiendo del tipo creamos un elemento diferente los tipos y sus respectivos elementos seran.
        //Elemento caja de texto Para los parametros de tipo numero
        //Elemento radial group (radial button) para los elementos de tipo lista
        //Elemento chebox para los elementos de tipo logico
        */

        //antes de nada creamos un layaout con un label de nombre de la formula y a su derecho un boton de información.
        LinearLayout layoutCabecera = new LinearLayout(this);
        layoutCabecera.setOrientation(LinearLayout.HORIZONTAL);
        TextView nombreDeFormula = new TextView(this);
        nombreDeFormula.setText(formulaActual.getNombreCompleto());
        nombreDeFormula.setLayoutParams(param);
        Button btnAyuda = new Button(this);
        btnAyuda.setText("Ayuda");
        btnAyuda.setLayoutParams(param);
        layoutCabecera.addView(nombreDeFormula);

        //tenemos que ver si alguno de los parametros es numero para mostrar o no el boton ayuda.
        for (int i = 0; i < formulaActual.contarParametros(); i++) {
            if (formulaActual.getParametro(i).getTipo().equals("numero"))
                existenCamposEdit = true;
        }

        //Solo mostramos el boton ayuda si existen campos editables en el formulario
        if(existenCamposEdit)
            layoutCabecera.addView(btnAyuda);
        lm.addView(layoutCabecera);


        for (int i = 0; i < formulaActual.contarParametros(); i++) {
            //Creamos un linear layout para cada iteraccón , en el estarán todos los elementos
            //No queremos mostrar los parametros de tipo resultado.
            LinearLayout layoutDeParametro = new LinearLayout(this);
            layoutDeParametro.setOrientation(LinearLayout.HORIZONTAL);
            //Primero le creamos una caja de texto
            TextView nombreDeParametro = new TextView(this);
            //nombreDeParametro.setText(formulaActual.getParametro(i).getNombre());

            if (formulaActual.getParametro(i).getMedida() != null)
                nombreDeParametro.setText("" +formulaActual.getParametro(i).getNombre()+ "(" +formulaActual.getParametro(i).getMedida()+ ")." );
            else
                nombreDeParametro.setText(formulaActual.getParametro(i).getNombre());

            nombreDeParametro.setLayoutParams(param);
            layoutDeParametro.addView(nombreDeParametro);
            //Ahora dependiendo del tipo de parametro creamos un elemento u otro

            switch (formulaActual.getParametro(i).getTipo()) {
                //Si es del tipo numero se crea una caja de texto
                case "numero":
                    EditText valorNumeroParametro = new EditText(this);
                    valorNumeroParametro.setLayoutParams(param);
                    valorNumeroParametro.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    //valorNumeroParametro.setId(formulaActual.getParametro(i).getIdParametro());
                    allEds.add(valorNumeroParametro);
                    layoutDeParametro.addView(valorNumeroParametro);
                    break;
                //Se crea un checbox para cada uno de los criterios de puntuacion
                case "logico":
                    CheckBox valorLogicoParametro = new CheckBox(this);
                    valorLogicoParametro.setLayoutParams(param);
                    //valorLogicoParametro.setId(formulaActual.getParametro(i).getIdParametro());
                    allChs.add(valorLogicoParametro);
                    layoutDeParametro.addView(valorLogicoParametro);
                    break;

                //Se crea un radial group con un radial button para cada una de las opciones
                case "lista":
                    RadioGroup valorListaParametro = new RadioGroup(this);
                    valorListaParametro.setLayoutParams(param);
                    for (int j = 0; j < formulaActual.getParametro(i).contarCriterios(); j++) {
                        RadioButton opcion = new RadioButton(this);
                        //Asignamos a cada uno de los botones de opcion como su id la de su criterio para poder encontrarlos luego
                        opcion.setId(formulaActual.getParametro(i).getCriterioPuntuacion(j).getIdCriterioPuntuacion());
                        opcion.setText(formulaActual.getParametro(i).getCriterioPuntuacion(j).getCriterio());
                        valorListaParametro.addView(opcion);
                    }
                    allRgs.add(valorListaParametro);
                    layoutDeParametro.addView(valorListaParametro);

                    break;
            }

            lm.addView(layoutDeParametro);

        }

        /* Se creará un botón que al pulsarlo:
        1 Evalue que todos los datos introducidos sean correctos (Valores Prohibidos, minimos, maximos,
        seleccionar al menos una opcion los radio button)
        2 Almacenara todos los valores introducidos en un vector que pasaremos a la siguiente pantalla para realizar los calculos.
         2.1 Para coger los datos debemos acceder a los almacenes donde estan. Para ello sera necesario
          Crear un bucle que vaya entrando a cada uno de los list<> dependiendo del tipo de datos de cada almacen
          Como son 3 tipos distintos de list<> necesitamos 3 variables para ir almacenando la posicion actual de cada iterador.
          Cuando tengamos todos los valores podemos crear una cadena que contenta idParametro: valorParametro; IdParametro:valor...

         */

        final Button btnCalcular = new Button(this);
        btnCalcular.setText("Calcular");
        lm.addView(btnCalcular);




        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setButton("Continue..", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you can add functions
            }
        });

        final TextView textoResultado = new TextView(this);
        textoResultado.setTextSize(30);
        lm.addView(textoResultado);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Necesitamos 3 iteradores uno para cada tipo de list<>
                int iEdit = 0;
                int iCheck = 0;
                int iRadio = 0;
                String aux = "";
                cadenaPrueba = "";
                //Creamos una variable bool que sera true cuando todos los campos tengan valores correctos
                boolean camposCorrectos = true;
                //Creamos una varable donde almacenaremos el indice del parametro incorrecto para despues mostrarlo en pantalla.
                indiceCampoIncorrecto = -1;
                //Vamos a meter todos los resultados en un vector de cadenas
                String[] vectorResultados = new String[formulaActual.contarParametros()];
                //Vamos a extraer los valores introducidos

                //Contamos el numero de parametros -1 porque no estamos interesados en el parametro resultado
                for (int i = 0; i < formulaActual.contarParametros(); i++) {
                    //Necesitamos 3 iteradores uno para cada tipo de list<>
                    switch (formulaActual.getParametro(i).getTipo()) {
                        case "numero":
                            vectorResultados[i] = allEds.get(iEdit).getText().toString();
                            iEdit++;
                            //Si el valor no es numerico
                            if (!isNumeric(vectorResultados[i])) {
                                //Como no es un numero ya hay un campo incorrecto.
                                camposCorrectos = false;
                                //Guardamos el indice del campo incorrecto para mostrarlo mas adelante.
                                indiceCampoIncorrecto = i;
                            }

                            //Si el valor es numero pero es menor que el valor numero es incorrecto
                            else if (Float.parseFloat(vectorResultados[i]) < formulaActual.getParametro(i).getValorMinimo()) {
                                camposCorrectos = false;
                                indiceCampoIncorrecto = i;
                            }

                            //Si es mayor que el valor maximo es incorrecto
                            else if (Float.parseFloat(vectorResultados[i]) > formulaActual.getParametro(i).getValorMaximo()) {
                                camposCorrectos = false;
                                indiceCampoIncorrecto = i;
                            }


                            break;
                        case "lista":
                            aux = "" + allRgs.get(iRadio).getCheckedRadioButtonId();
                            vectorResultados[i] = aux;
                            if (aux.equals("-1")) {
                                camposCorrectos = false;
                                indiceCampoIncorrecto = i;
                            }

                            iRadio++;
                            //Sino ha seleccionado ninguna opcion es incorrecto

                            break;
                        case "logico":
                            if (allChs.get(iCheck).isChecked())
                                vectorResultados[i] = "1";
                            else
                                vectorResultados[i] = "0";
                            iCheck++;
                            break;
                    }

                    if (!camposCorrectos) {
                        alertDialog.setMessage("Error en el parametro " + formulaActual.getParametro(indiceCampoIncorrecto).getNombre());
                        alertDialog.show();
                        break;
                    }


                        //Introdicimos los valores de los parametros en nuestra formula.
                        //formulaActual.introducirValoresFormula(vectorResultados);

                        //Calculamos los resultados.
                       // formulaActual.calcularFormula();

                       // textoResultado.setText(cadenaPrueba  + "\n Su resultado es \n" + formulaActual.getResultado().getValor());

                        //Agregamos la formula a recientes
                        //Esta funcion no debe tener parametros salvo el context????
                       // formulaActual.introducirRecientes(formulaActual.getIdFormula() , formulaActual.getResultado().getValor(), getApplicationContext());




                }
                if (camposCorrectos) {
                    //Introdicimos los valores de los parametros en nuestra formula.
                    formulaActual.introducirValoresFormula(vectorResultados);

                    //Calculamos los resultados.
                    formulaActual.calcularFormula();

                    String resultadoFinal ="";

                    if (formulaActual.getResultado().getMedida() == null)
                        resultadoFinal = formulaActual.getResultado().getValor() ;

                    else
                        resultadoFinal = formulaActual.getResultado().getValor() + " " + formulaActual.getResultado().getMedida() ;
                    textoResultado.setText(resultadoFinal);


                }



            }
        });

        btnAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cadena = "";

                for(int i =0; i < formulaActual.contarParametros(); i++)
                {
                    if (formulaActual.getParametro(i).getTipo().equals("numero"))
                        cadena = cadena + formulaActual.getParametro(i).getNombre()+ "\nValor minimo: " +formulaActual.getParametro(i).getValorMinimo() +  "\n Valor Maximo: " +formulaActual.getParametro(i).getValorMaximo() + " \n" ;
                }

                alertDialog.setMessage(cadena);
                alertDialog.show();
            }
        });



    }



    // Funcion auxiliar que comprueba si una cadena es un numero. Podria ser interesante tenerlas todas en una misma clase
    public boolean isNumeric(String str) {
        return str.matches("^-?[0-9]+([,\\.][0-9]+)?$");
    }


}


//Se deben crear tres contenedores de datos, para tres tipos de datos numero ( editText), lista radial button y logico checkbox.
//Ademas se debe crear una etiqueta con el nombre de cada parametro.

//Para esto creamos en un bucle cada etiqueta con el nombre del parametro y despues le añadimos dependiendo del tipo del parametro
//un editBox, o varios chebox o radialbuton.

//Se deben recoger todos los datos introducidos por el usuario y meterlos en un vector

//Este vector pasara a otra pagina donde seran calculados.

