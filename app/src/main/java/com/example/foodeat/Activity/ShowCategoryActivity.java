package com.example.foodeat.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodeat.Adaptor.PopularAdaptor;
import com.example.foodeat.DataBase.Repository.CategoryRepository;
import com.example.foodeat.DataBase.Repository.FoodRepository;
import com.example.foodeat.Domain.FoodDomain;
import com.example.foodeat.R;

import java.util.ArrayList;

public class ShowCategoryActivity extends AppCompatActivity {
    private TextView textViewCategory;
    private RecyclerView recyclerViewList;
    private RecyclerView.Adapter adapter;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category);

        extras = getIntent().getExtras();

        textViewCategory = findViewById(R.id.textViewTitreCategory);
        textViewCategory.setText("Catégorie : " + CategoryRepository.getInstance(this).getById(extras.getInt("cat")).getTitle());

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        recyclerViewList= findViewById(R.id.listFood);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foods = FoodRepository.getInstance(this).getAll();
        for(int i =0;i< foods.size(); i++){
            if(foods.get(i).getId_category() != extras.getInt("cat")) {
                foods.remove(foods.get(i));
                i--;
            }
        }

        adapter=new PopularAdaptor(foods);
        recyclerViewList.setAdapter(adapter);
    }
}
