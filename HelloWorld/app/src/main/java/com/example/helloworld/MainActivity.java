package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText inpAltura;
    private EditText peso;
    private Button btnHomem;
    private Button btnMulher;
    private Button btnLimpar;

    private TextView imcValue;
    private TextView imcText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inpAltura = findViewById(R.id.txtAltura);
        peso = findViewById(R.id.txtPeso);
        btnHomem = findViewById(R.id.btnHomem);
        btnMulher = findViewById(R.id.btnMulher);
        btnLimpar = findViewById(R.id.btnLimpar);
        imcValue = findViewById(R.id.txtImcDisplay);
        imcText = findViewById(R.id.txtPesoDisplay);

        btnHomem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double imc = calcularIMC(peso.getText().toString(), inpAltura.getText().toString());
                String imcTxt = String.valueOf(imc);
                imcText.setText("Seu peso está " + imcMsg(imc, 1));
                imcValue.setText("IMC é de: "+imcTxt);
            }
        });

        btnMulher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double imc = calcularIMC(peso.getText().toString(), inpAltura.getText().toString());
                String imcTxt = String.valueOf(imc);
                imcText.setText("Seu peso está " + imcMsg(imc, 0));
                imcValue.setText("IMC é de: "+imcTxt);
            }
        });
    }

    public double calcularIMC(String peso, String alt){
        double doubAltura = Double.parseDouble(alt);
        double doubPeso = Double.parseDouble(peso);
        double imcResul = doubPeso/(doubAltura*doubAltura);
        return imcResul;
    }

    public String imcMsg(double imc, int tipo){
        String msg = "";
            if(tipo == 0){
                if(imc <19.1){
                    msg = "Abaixo do Peso";
                } else if (imc <= 25.8) {
                    msg = "No peso Normal";
                } else if (imc <= 27.3) {
                    msg = "Marginalmente acima do Peso";
                } else if (imc <= 32.3) {
                    msg = "Acima do Peso Ideal";
                }
                else{
                    msg = "Obeso!";
                }
            }else{
                if(imc <20.7){
                    msg = "Abaixo do Peso";
                } else if (imc <= 26.4) {
                    msg = "No peso Normal";
                } else if (imc <= 27.8) {
                    msg = "Marginalmente acima do Peso";
                } else if (imc <= 31.1) {
                    msg = "Acima do Peso Ideal";
                }
                else{
                    msg = "Obeso!";
                }
            }
        return msg;
    }
}