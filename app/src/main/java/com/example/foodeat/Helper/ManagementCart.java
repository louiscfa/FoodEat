package com.example.foodeat.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.foodeat.Domain.FoodDomain;
import com.example.foodeat.Listener.INumberList;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB= new TinyDB(context);
    }

    public void insertFood(FoodDomain item) {
        ArrayList<FoodDomain> listFood= getListCart();
        boolean existAlready=false;
        int n=0;
        for(int i = 0; i < listFood.size(); i++) {
             if(listFood.get(i).getTitle.equals(item.getTitle())){
                 existAlready = true;
                 n = i;
                 break;
             }
        }

        if(existAlready) {
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFood.add(item);
        }
        tinyDB.putListObject("CardList", listFood);
        Toast.makeText(context, "Ajouté à votre panier", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<FoodDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void ajoutProduit(ArrayList<FoodDomain> listFood, int position, INumberList listener){
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList", listFood);
        listener.changed();
    }

    public void retireProduit(ArrayList<FoodDomain> listFood, int position, INumberList listener){
        if(listFood.get(position).getNumberInCart() == 1){
            listFood.remove(position);
        }else{
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList", listFood);
        listener.changed();
    }

    public Double getTotalFee(){
        ArrayList<FoodDomain> listFood = getListCart();
        double fee = 0;
        for (int i = 0; i < listFood.size(); i++) {
            fee = fee+(listFood.get(i).getFee() * listFood.get(i).getNumberInCart());
        }
        return fee;
    }
}
