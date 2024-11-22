package com.unip.safeeats.data.DTO;

import android.content.Context;

import com.unip.safeeats.data.model.CarrinhoItem;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoManager {

    private static List<CarrinhoItem> carrinhoItems = new ArrayList<>();

    public static List<CarrinhoItem> getCarrinhoItems(Context context) {
        return carrinhoItems;
    }

    public static void addCarrinhoItem(Context context, CarrinhoItem item) {
        carrinhoItems.add(item);
        saveCarrinhoItems(context, carrinhoItems);
    }

    public static void saveCarrinhoItems(Context context, List<CarrinhoItem> carrinhoItems) {
        // Salvar no DB
    }
    public static void removeCarrinhoItems(Context context, CarrinhoItem item){
        carrinhoItems.remove(item);
    }

    public static double getTotal(){
        if(carrinhoItems.isEmpty()){
            return 0;
        }
        else{
            double count = 0.0;
            for(CarrinhoItem carrinho : carrinhoItems){
                count += carrinho.getPrecoUnitario();
            }
            return count;
        }
    }
}

