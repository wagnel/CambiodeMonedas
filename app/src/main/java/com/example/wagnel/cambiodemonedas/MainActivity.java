package com.example.wagnel.cambiodemonedas;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Spinner spMoneda;
    private TextView result1,result2; //  Result1 es el TextView o cuadro que esta de color rojo
    private EditText cantidad; // Cantidad ingresada en la aplicacion
    private final double PRECIO_DOLAR_DOP = 49.48d; // Precio del dolar en peso dominicano
    private final double PRECIO_EURO_DOP = 59.47d; // Precio del euro en peso dominicano
    private final double PRECIO_EURO_USD = 1.20d; // Precio del euro en dolar estadounidense

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result1 = (TextView) findViewById(R.id.tResult1);
        result2 = (TextView) findViewById(R.id.tResult2);
        cantidad = (EditText) findViewById(R.id.tCantidad);

        chargeSpinner();
    }

    // Este metodo carga el spinner del activity main
    private void chargeSpinner(){
        spMoneda = (Spinner) findViewById(R.id.spCoin);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.coin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMoneda.setAdapter(adapter);
    }

    // Este metodo es para calcular el cambio de moneda
    @SuppressLint("SetTextI18n")
    public void calculate(View view) {
        try {
            String moneda = spMoneda.getSelectedItem().toString();
            Double cant = Double.parseDouble(cantidad.getText().toString());

            DecimalFormat df = new DecimalFormat("#,###.##");
            df.setRoundingMode(RoundingMode.CEILING);

            if (moneda.equals("DOP")) {
                result1.setText("US$ " + df.format(cant * (1 / PRECIO_DOLAR_DOP)));
                result2.setText("€ " + df.format(cant * (1 / PRECIO_EURO_DOP)));
            }
            if (moneda.equals("USD")) {
                result1.setText("DOP$ " + df.format(cant * PRECIO_DOLAR_DOP));
                result2.setText("€ " + df.format(cant * (1 / PRECIO_EURO_USD)));
            }
            if (moneda.equals("EUR")) {
                result1.setText("DOP$ " + df.format(cant * PRECIO_EURO_DOP));
                result2.setText("US$ " + df.format(cant * PRECIO_EURO_USD));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}