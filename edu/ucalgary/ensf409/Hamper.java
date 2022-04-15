package edu.ucalgary.ensf409;

import java.util.ArrayList;

public class Hamper extends InventoryData{
    // Member Variables
    private double wholeGrains;
    private double fruitVeg;
    private double protien;
    private double other;
    private int calories;
    private final Client[] clients;
    private ArrayList<Food> items;

    // Constructors
    Hamper(Client[] clients){
        this.clients = clients;
    }
    
    // Setters

    // Getters
    public double getGrain(){ return this.wholeGrains; }
    public double getFruitVeg(){ return this.fruitVeg; }
    public double getProtien(){ return this.protien; }
    public double getOther(){ return this.other; }
    public int getCalories(){ return this.calories; }

    // Methods
    public void addFood(Food item){
        items.add(item);
    }
    
    public void calculateCaloriesNeeded(){
        for(int i = 0; i < clients.length; i ++){
            wholeGrains += clients[i].getGrains();
            fruitVeg += clients[i].getFruitVeg();
            protien += clients[i].getProtien();
            other += clients[i].getOther();
            calories += clients[i].getCalories();
        }
    }

    public int calculateDiff(){
        int totalCal = 0;
        for(Food item : items){
            totalCal += item.getCalories();
        }
        return totalCal - this.calories;
    }
}
