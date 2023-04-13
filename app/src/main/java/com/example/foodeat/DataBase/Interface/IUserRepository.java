package com.example.foodeat.DataBase.Interface;

import com.example.foodeat.Domain.User;

import java.util.ArrayList;

public interface IUserRepository {

    public boolean add(User user);

    public boolean remove(User user);

    public ArrayList<User> getAll();

    public boolean isEmpty();

    public boolean update(User user);

    public User getById(int id);

}
