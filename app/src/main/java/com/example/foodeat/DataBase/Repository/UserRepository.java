package com.example.foodeat.DataBase.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.foodeat.DataBase.DatabaseManager;
import com.example.foodeat.DataBase.Interface.IUserRepository;
import com.example.foodeat.Domain.User;

import java.util.ArrayList;

public class UserRepository implements IUserRepository {

    private DatabaseManager dbm;
    private static UserRepository instance;

    private UserRepository(Context context){
        this.dbm = DatabaseManager.getInstance(context);
    }

    public static UserRepository getInstance(Context context){
        if(instance==null){
            instance= new UserRepository(context);
        }
        return instance;
    }


    @Override
    public boolean add(User user) {
        ContentValues values = new ContentValues();
        values.put("username", user.getUserName());
        values.put("password", user.getPassword());
        long line= dbm.getWritableDatabase().insert("user", null, values);
        return line != 0;
    }

    @Override
    public boolean remove(User user) {
        String[] identifier = {String.valueOf(user.getId())};
        long line= dbm.getWritableDatabase().delete("user", "id=?", identifier);
        return line != 0;
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<User>();
        Cursor c= dbm.getReadableDatabase().rawQuery("select * from user ", null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            User user= new User();
            user.setId(c.getInt(0));
            user.setUserName(c.getString(1));
            user.setPassword(c.getString(2));
            users.add(user);
            c.moveToNext();
        }
        c.close();
        return users;
    }
}
