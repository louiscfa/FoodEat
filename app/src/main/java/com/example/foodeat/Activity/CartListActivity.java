package com.example.foodeat.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodeat.Adaptor.CartListAdaptor;
import com.example.foodeat.Helper.ManagementCart;
import com.example.foodeat.Listener.INumberList;
import com.example.foodeat.R;

import java.text.DecimalFormat;

public class CartListActivity extends AppCompatActivity {
    private TextView textViewPrixNourriture, textViewPrixFrais, textViewPrixTax, textViewPrixTotal;

    private Button buttonPayer;

    private RecyclerView listPanier;

    private RecyclerView.Adapter adapter;

    private double tax;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        textViewPrixNourriture = findViewById(R.id.textViewPrixNourriture);
        textViewPrixFrais = findViewById(R.id.textViewPrixFrais);
        textViewPrixTax = findViewById(R.id.textViewPrixTax);
        textViewPrixTotal = findViewById(R.id.textViewPrixTotal);

        buttonPayer = findViewById(R.id.buttonPayer);

        listPanier = findViewById(R.id.listPanier);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        listPanier.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdaptor(ManagementCart.getInstance(this).getListCart(), this, new INumberList() {
            @Override
            public void changed() {
                TotalPanier();
            }
        });

        listPanier.setAdapter(adapter);
       /* if(managementCart.getListCart.isEmpty()){
            emptyText.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyText.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }*/

        TotalPanier();
    }

    private void TotalPanier(){
        double percentTax = 0.02;
        double livraison = 2;

        tax = (ManagementCart.getInstance(this).getTotalFee() * percentTax * 100)/100;
        double totalProduit = ManagementCart.getInstance(this).getTotalFee();
        double total = ManagementCart.getInstance(this).getTotalFee() + tax + livraison ;
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        textViewPrixNourriture.setText(numberFormat.format(totalProduit) + " €");
        textViewPrixTax.setText(numberFormat.format(tax) + " €");
        textViewPrixFrais.setText(numberFormat.format(livraison) + " €");
        textViewPrixTotal.setText(numberFormat.format(total) + " €");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
