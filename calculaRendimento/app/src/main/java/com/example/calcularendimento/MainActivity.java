package com.example.calcularendimento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText txtInvesInicial;
    private EditText txtTaxaJuros;
    private EditText txtPeriodo;
    private Button btnCalcular;
    private TextView txtTotalValDisplay;
    private TextView txtTotalJurosDisplay;
    private Button btnLimpar;
    private double valorInvest = 0;
    private double valorJuros = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtInvesInicial = findViewById(R.id.txtInvesInicial);
        txtTaxaJuros = findViewById(R.id.txtTaxaJuros);
        txtPeriodo = findViewById(R.id.txtPeriodo);
        btnCalcular = findViewById(R.id.btnCalcular);
        txtTotalValDisplay = findViewById(R.id.txtTotalValDisplay);
        txtTotalJurosDisplay = findViewById(R.id.txtTotalJurosDisplay);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valorInvest = Double.parseDouble(txtInvesInicial.getText().toString());
                double taxa = Double.parseDouble(txtTaxaJuros.getText().toString());
                int periodo = Integer.parseInt(txtPeriodo.getText().toString());
                for (int i = 0; i < periodo; i++) {
                    calcJuros(valorInvest, taxa);
                }
                txtTotalValDisplay.setText("Rendimento total: " + valorInvest);
                txtTotalJurosDisplay.setText("Valor em juros: " + valorJuros);

            }
        });
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpar();
            }
        });
    }

    private void calcJuros(double valor, double taxa) {
        double rendimento = valor * (taxa / 100);
        valorJuros += rendimento;
        valorInvest += rendimento;
    }

    private void limpar() {
        txtInvesInicial.setText("");
        txtTaxaJuros.setText("");
        txtPeriodo.setText("");
        txtTotalValDisplay.setText("");
        txtTotalJurosDisplay.setText("");
    }
}