package com.example.foodeat;

import com.example.foodeat.Domain.FoodDomain;

import java.util.ArrayList;

public class ManagementCart {

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
