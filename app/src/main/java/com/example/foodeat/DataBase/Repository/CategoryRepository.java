package com.example.foodeat.DataBase.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.foodeat.DataBase.DatabaseManager;
import com.example.foodeat.DataBase.Interface.ICategoryRepository;
import com.example.foodeat.Domain.CategoryDomain;

import java.util.ArrayList;

public class CategoryRepository implements ICategoryRepository {

    private DatabaseManager dbm;
    private static CategoryRepository instance;

    private CategoryRepository(Context context){
        this.dbm = DatabaseManager.getInstance(context);
    }

    public static CategoryRepository getInstance(Context context){
        if(instance==null){
            instance= new CategoryRepository(context);
        }
        return instance;
    }

    @Override
    public boolean add(CategoryDomain cat) {
        ContentValues values = new ContentValues();
        values.put("title", cat.getTitle());
        values.put("pic", cat.getPic());
        long line= dbm.getWritableDatabase().insert("category", null, values);
        return line != 0;
    }

    @Override
    public boolean remove(CategoryDomain cat) {
        String[] identifier = {String.valueOf(cat.getId())};
        long line= dbm.getWritableDatabase().delete("category", "id=?", identifier);
        return line != 0;
    }

    @Override
    public ArrayList<CategoryDomain> getAll() {
        ArrayList<CategoryDomain> cats = new ArrayList<CategoryDomain>();
        Cursor c= dbm.getReadableDatabase().rawQuery("select * from category ", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            CategoryDomain cat= new CategoryDomain();
            cat.setId(c.getInt(0));
            cat.setTitle(c.getString(1));
            cat.setPic(c.getString(2));
            cats.add(cat);
            c.moveToNext();
        }
        c.close();
        return cats;
    }
}
