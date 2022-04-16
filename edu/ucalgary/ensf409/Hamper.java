package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hamper extends InventoryData{
    // Member Variables
    private double wholeGrains;
    private double fruitVeg;
    private double protein;
    private double other;
    private int calories;
    private final Client[] clients;
    private ArrayList<Food> items;

    // Constructors
    Hamper(Client[] clients){
        this.clients = clients;
        calcCaloriesNeeded();
        items = new ArrayList<>();
    }
    
    // Setters
    public void clearItems(){ this.items.clear(); }

    // Getters
    public double getGrain(){ return this.wholeGrains; }
    public double getFruitVeg(){ return this.fruitVeg; }
    public double getProtien(){ return this.protein; }
    public double getOther(){ return this.other; }
    public int getCalories(){ return this.calories; }
    public ArrayList<Food> getItems(){ return this.items; }

    public double getAddedGrain(){
        double total = 0;
        for(Food item : items){
            total += item.getGrains();
        }
        return total;
    }
    public double getAddedFruitVeg(){
        double total = 0;
        for(Food item : items){
            total += item.getFruitVeg();
        }
        return total;
    }
    public double getAddedProtien(){
        double total = 0;
        for(Food item : items){
            total += item.getProtien();
        }
        return total;
    }
    public double getAddedOther(){
        double total = 0;
        for(Food item : items){
            total += item.getOther();
        }
        return total;
    }
    public int getAddedCalories(){
        int total = 0;
        for(Food item : items){
            total += item.getCalories();
        }
        return total;
    }

    // Methods
    public void addFood(Food item){
        items.add(item);
    }
    
    public void calcCaloriesNeeded(){
        for(int i = 0; i < clients.length; i ++){
            wholeGrains += clients[i].getGrains();
            fruitVeg += clients[i].getFruitVeg();
            protein += clients[i].getProtien();
            other += clients[i].getOther();
            calories += clients[i].getCalories();
        }
    }

    public double calcProDiff(){
        double totalCal = 0;
        for(Food item : items){
            totalCal += item.getProtien();
        }
        return totalCal - this.protein;
    }

    public double calcFruitDiff(){
        double totalCal = 0;
        for(Food item : items){
            totalCal += item.getFruitVeg();
        }
        return totalCal - this.fruitVeg;
    }

    public double calcGrainDiff(){
        double totalCal = 0;
        for(Food item : items){
            totalCal += item.getGrains();
        }
        return totalCal - this.wholeGrains;
    }

    public double calcOtherDiff(){
        double totalCal = 0;
        for(Food item : items){
            totalCal += item.getOther();
        }
        return totalCal - this.other;
    }

    public int calcCalorieDiff(){
        int totalCal = 0;
        if(items.isEmpty()){
            return -100;
        }else{
            for(Food item : items){
                totalCal += item.getCalories();
            }
        }
        return totalCal - this.calories;
    }

    public void sortHamper(){
        Collections.sort(items, Comparator.comparing(Food::getDescription));
    }
}
