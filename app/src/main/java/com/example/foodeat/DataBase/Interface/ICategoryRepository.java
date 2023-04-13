package com.example.foodeat.DataBase.Interface;

import com.example.foodeat.Domain.CategoryDomain;

import java.util.ArrayList;

public interface ICategoryRepository {
    public boolean add(CategoryDomain cat);

    public boolean remove(CategoryDomain cat);

    public ArrayList<CategoryDomain> getAll();

    public boolean isEmpty();

    public CategoryDomain getById(int id);

}
