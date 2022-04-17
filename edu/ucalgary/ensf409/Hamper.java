/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * An instance of this class represents a hamper that will be filled with food
 * 
 * @author Group 24
 * @version 2.5
 * @since 1.0
 */
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
    



    // Getters

    public double getGrain(){ return this.wholeGrains; }

    public double getFruitVeg(){ return this.fruitVeg; }

    public double getProtien(){ return this.protein; }
    public double getOther(){ return this.other; }
    public int getCalories(){ return this.calories; }
    public ArrayList<Food> getItems(){ return this.items; }

/****************************** Getters ***************************************/
    
    /** 
     * Find how many grain calories have been added to the hamper
     * 
     * @return  the amount of grain calories that have beee added to the hamper
     */
    public double getAddedGrain(){
        double total = 0;
        for(Food item : items){
            total += item.getGrains();
        }
        return total;
    }

    /** 
     * Find how many fruit and vegtable calories have been added to the hamper
     * 
     * @return  the amount of fruit and vegtable calories that have beee added 
     *          to the hamper
     */
    public double getAddedFruitVeg(){
        double total = 0;
        for(Food item : items){
            total += item.getFruitVeg();
        }
        return total;
    }

    
    /** 
     * Find how many protein calories have been added to the hamper
     * 
     * @return  the amount of protein calories that have beee added to the hamper
     */
    public double getAddedProtein(){
        double total = 0;
        for(Food item : items){
            total += item.getProtien();
        }
        return total;
    }

    
    /** 
     * Find how many other calories have been added to the hamper
     * 
     * @return  the amount of other calories that have beee added to the hamper
     */
    public double getAddedOther(){
        double total = 0;
        for(Food item : items){
            total += item.getOther();
        }
        return total;
    }

    
   /** 
     * Find how many calories have been added to the hamper
     * 
     * @return  the amount of calories that have beee added to the hamper
     */
    public int getAddedCalories(){
        int total = 0;
        for(Food item : items){
            total += item.getCalories();
        }
        return total;
    }

    
    
    // Methods

    /**
     * Clears all of the food that is currently in the hamper
     */
    public void clearItems(){ this.items.clear(); }

    /** 
     * Adds food to the hamper
     * 
     * @param item      The item to be added
     */
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

    
    /** 
     * @return  the difference between the protein needed and the protein required
     */
    public double calcProDiff(){
        double totalCal = getAddedProtein();
        return totalCal - this.protein;
    }

    
    /** 
     * @return double
     */
    public double calcFruitDiff(){
        double totalCal = getAddedFruitVeg();
        return totalCal - this.fruitVeg;
    }

    
    /** 
     * @return double
     */
    public double calcGrainDiff(){
        double totalCal = getAddedGrain();
        return totalCal - this.wholeGrains;
    }

    
    /** 
     * @return double
     */
    public double calcOtherDiff(){
        double totalCal = getAddedOther();
        return totalCal - this.other;
    }

    
    /** 
     * @return int
     */
    public int calcCalorieDiff(){
        int totalCal = 0;
        if(items.isEmpty()){
            return -100;
        }else{
            totalCal = getAddedCalories();
        }
        return totalCal - this.calories;
    }

    public void sortHamper(){
        Collections.sort(items, Comparator.comparing(Food::getDescription));
    }
}
