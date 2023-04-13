package com.example.foodeat.DataBase.Interface;

import com.example.foodeat.Domain.FoodDomain;

import java.util.ArrayList;

public interface IFoodRepository {
    public boolean add(FoodDomain food);

    public boolean remove(FoodDomain food);

    public ArrayList<FoodDomain> getAll();

    public boolean isEmpty();

}
