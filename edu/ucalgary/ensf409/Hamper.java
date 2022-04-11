package edu.ucalgary.ensf409;

import java.util.ArrayList;

public class Hamper {
    // Member Variables
    private double wholeGrains;
    private double fruitVeg;
    private double protien;
    private double other;
    private double calories;
    private Client[] clients;
    private ArrayList<Food> items;

    // Constructors
    Hamper(Client[] clients){
        this.clients = clients;
    }
    
    // Setters

    // Getters

    // Methods
    // public void addFood(){
    //     items.add(InventoryData.get)
    // }
    
    public void calculateCalories(){
        for(int i = 0; i < clients.length; i ++){
            wholeGrains += clients[i].getGrains();
            fruitVeg += clients[i].getFruitVeg();
            protien += clients[i].getProtien();
            other += clients[i].getOther();
            calories += clients[i].getCalories();
        }
    }
}
