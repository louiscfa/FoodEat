package com.example.foodeat.DataBase.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.foodeat.DataBase.DatabaseManager;
import com.example.foodeat.DataBase.Interface.IFoodRepository;
import com.example.foodeat.Domain.FoodDomain;

import java.util.ArrayList;

public class FoodRepository implements IFoodRepository {

    private DatabaseManager dbm;
    private static FoodRepository instance;

    private FoodRepository(Context context){
        this.dbm = DatabaseManager.getInstance(context);
    }

    public static FoodRepository getInstance(Context context){
        if(instance==null){
            instance= new FoodRepository(context);
        }
        return instance;
    }

    @Override
    public boolean add(FoodDomain food) {
        ContentValues values = new ContentValues();
        values.put("title", food.getTitle());
        values.put("pic", food.getPic());
        values.put("description", food.getDescription());
        values.put("fee", food.getFee());
        values.put("numberincart", food.getNumberInCart());
        values.put("id_category",food.getId_category());
        long line= dbm.getWritableDatabase().insert("food", null, values);
        return line != 0;
    }

    @Override
    public boolean remove(FoodDomain food) {
        String[] identifier = {String.valueOf(food.getId())};
        long line= dbm.getWritableDatabase().delete("food", "id=?", identifier);
        return line != 0;
    }

    @Override
    public ArrayList<FoodDomain> getAll() {
        ArrayList<FoodDomain> foods = new ArrayList<FoodDomain>();
        Cursor c= dbm.getReadableDatabase().rawQuery("select * from food ", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            FoodDomain food= new FoodDomain();
            food.setId(c.getInt(0));
            food.setTitle(c.getString(1));
            food.setPic(c.getString(2));
            food.setDescription(c.getString(3));
            food.setFee(c.getDouble(4));
            food.setNumberInCart(c.getInt(5));
            food.setId_category(c.getInt(6));
            foods.add(food);
            c.moveToNext();
        }
        c.close();
        return foods;
    }

    @Override
    public boolean isEmpty() {
        Cursor c= dbm.getReadableDatabase().rawQuery("select * from food",null);
        return !(c.getCount()>0);
    }
}
