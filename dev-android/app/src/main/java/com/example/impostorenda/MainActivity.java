package com.example.impostorenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText txtRendaMensal;
    private EditText txtQtdDepen;
    private EditText txtDeducao;
    private TextView txtRendaDisplay;
    private TextView txtBaseCalcDisplay;
    private TextView txtImpTotalDisplay;
    private Button btnCalcular;
    private Button btnLimpar;
    private double rendaMensal;
    private double baseCalc;
    private double totalImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtRendaMensal = findViewById(R.id.txtRendaMensal);
        txtQtdDepen = findViewById(R.id.txtQtdDepen);
        txtDeducao = findViewById(R.id.txtDeducao);
        txtRendaDisplay = findViewById(R.id.txtRendaDisplay);
        txtBaseCalcDisplay = findViewById(R.id.txtBaseCalcDisplay);
        txtImpTotalDisplay = findViewById(R.id.txtImpTotalDisplay);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcImposto(txtRendaMensal.getText().toString(), txtQtdDepen.getText().toString(), txtDeducao.getText().toString());
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpar();
            }
        });
    }

    private void calcImposto(String rendaMensal, String qtdDepen, String deducao) {
        int dependQtd = Integer.parseInt(qtdDepen);
        double valDeducao = Double.parseDouble(deducao);
        double valRendaMensal = calcRendaMensal(rendaMensal);
        double baseCalc = valRendaMensal - calcValDepen(dependQtd) - valDeducao;
        double excedente;
        double valorImp;

        if (baseCalc > 18000) {
            excedente = baseCalc - 18000;
            valorImp = excedente * 0.15;
        } else if (baseCalc > 27000) {
            excedente = baseCalc - 18000;
            valorImp = (excedente * 0.275) + 1350;
        } else {
            valorImp = 0;
        }

        String txtRenda = String.valueOf(valRendaMensal);
        String txtBase = String.valueOf(baseCalc);
        String txtImp = String.valueOf(valorImp);

        setValues(txtRenda, txtBase, txtImp);
    }

    private double calcRendaMensal(String renda) {
        double rendaAnual = Double.parseDouble(renda) * 12;
        return rendaAnual;
    }

    private double calcValDepen(int qtdDepen) {
        double valDepen = (300 * qtdDepen) * 12;
        return valDepen;
    }

    private void setValues(String rendaBruta, String baseCalc, String impDevido) {
        txtImpTotalDisplay.setText("Imposto devido: " + impDevido);
        txtBaseCalcDisplay.setText("Base de Calculo: " +baseCalc);
        txtRendaDisplay.setText("Renda Bruta Anual: " + rendaBruta);
    }

    private void limpar() {
        txtRendaMensal.setText("");
        txtQtdDepen.setText("");
        txtDeducao.setText("");
        txtRendaDisplay.setText("");
        txtBaseCalcDisplay.setText("");
        txtImpTotalDisplay.setText("");
    }
}