package com.example.foodeat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodeat.Helper.ManagementCart;
import com.example.foodeat.Listener.INumberList;
import com.example.foodeat.Adapter.CartListAdapter;
import com.example.foodeat.Activity.MainActivity;
import com.example.foodeat.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private TextView textViewPrixNourriture, textViewPrixFrais, textViewPrixTax, textViewPrixTotal;

    private Button buttonPayer;

    private RecyclerView listPanier;

    private RecyclerView.Adapter adapter;

    private double tax;

    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        managementCart = new ManagementCart(this);

        textViewPrixNourriture = findViewById(R.id.textViewPrixNourriture);
        textViewPrixFrais = findViewById(R.id.textViewPrixFrais);
        textViewPrixTax = findViewById(R.id.textViewPrixTax);
        textViewPrixTotal = findViewById(R.id.textViewPrixTotal);

        buttonPayer = findViewById(R.id.buttonPayer);

        listPanier = findViewById(R.id.listPanier);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        listPanier.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new INumberList() {
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
        bottomNavigation();
    }

    private void TotalPanier(){
        double percentTax = 0.02;
        double livraison = 2;

        tax = (managementCart.getTotalFee() * percentTax * 100)/100;
        double totalProduit = managementCart.getTotalFee();
        double total = managementCart.getTotalFee() + tax + livraison ;

        textViewPrixNourriture.setText(totalProduit + " €");
        textViewPrixTax.setText(tax + " €");
        textViewPrixFrais.setText(livraison + " €");
        textViewPrixTotal.setText(total + " €");
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cardBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this,CartListActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartListActivity.this, MainActivity.class));
            }
        });
    }
}
