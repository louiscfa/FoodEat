package com.example.foodeat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodeat.DataBase.Repository.CategoryRepository;
import com.example.foodeat.DataBase.Repository.FoodRepository;
import com.example.foodeat.DataBase.Repository.UserRepository;
import com.example.foodeat.Domain.CategoryDomain;
import com.example.foodeat.Domain.FoodDomain;
import com.example.foodeat.Domain.User;
import com.example.foodeat.R;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        if(CategoryRepository.getInstance(this).isEmpty()) {
            CategoryRepository.getInstance(this).add(new CategoryDomain("Pizza", "cat_1"));
            CategoryRepository.getInstance(this).add(new CategoryDomain("Burger", "cat_2"));
            CategoryRepository.getInstance(this).add(new CategoryDomain("HotDog", "cat_3"));
            CategoryRepository.getInstance(this).add(new CategoryDomain("Drink", "cat_4"));
            CategoryRepository.getInstance(this).add(new CategoryDomain("Donut", "cat_5"));
        }

        if(FoodRepository.getInstance(this).isEmpty()) {
            FoodRepository.getInstance(this).add(new FoodDomain("Pepperoni pizza", "pop_1", "morceaux de pepperoni, mozzarella, origan, poivre noir, sauce tomate", 9.76,1));
            FoodRepository.getInstance(this).add(new FoodDomain("Burger au Fromage", "pop_2", "boeuf, Gouda, sauce burger, laitue, tomate", 8.00,2));
            FoodRepository.getInstance(this).add(new FoodDomain("Pizza végétarienne", "pop_3", "Huile d'olive, huile végétale, tomate cerise", 8.50,1));
        }

        if(UserRepository.getInstance(this).isEmpty()) {
            UserRepository.getInstance(this).add(new User("Charles", "1234", "", "", "", "", "profile"));
            UserRepository.getInstance(this).add(new User("test", "test", "", "", "", "", "icons8_male_user_100"));
        }

        Button button = findViewById(R.id.startBtn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(IntroActivity.this, ConnexionActivity.class));
        finish();

    }
}