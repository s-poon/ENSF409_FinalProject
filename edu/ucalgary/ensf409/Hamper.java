/**
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
 * @author Tammy Pham, Steven Poon, Bill Thai and Alex Yeap
 * @version 2.5
 * @since 1.0
 */
public class Hamper extends InventoryData{
/*************************** Member Variables *********************************/
    private double wholeGrains;
    private double fruitVeg;
    private double protein;
    private double other;
    private int calories;
    private final Client[] clients;
    private ArrayList<Food> items;

/***************************** Constructor ************************************/
    /**
     *Creates a hamper based on the client list provided
     * 
     * @param clients   An array of type client.  Represents the clients that
     *                  will be recieving the hamper
     * @see Clients
     */
    Hamper(Client[] clients){
        this.clients = clients;
        calcCaloriesNeeded();
        items = new ArrayList<>();
    }
    
/****************************** Getters ***************************************/
    /**
     * @return      Returns the amount of grains the hamper needs based on the 
     *              clients recieving the hamper          
     */
    public double getGrain(){ return this.wholeGrains; }

    /**
     * @return      Returns the amount of fruits and vegtables the hamper needs 
     *              based on the clients recieving the hamper          
     */
    public double getFruitVeg(){ return this.fruitVeg; }

    /**
     * @return      Returns the amount of protein the hamper needs based on the 
     *              clients recieving the hamper          
     */
    public double getProtien(){ return this.protein; }

    /**
     * @return      Returns the amount of other calories the hamper needs based 
     *              on the clients recieving the hamper          
     */
    public double getOther(){ return this.other; }

    /**
     * @return      Returns the total amount of calories the hamper needs based 
     *              on the clients recieving the hamper          
     */
    public int getCalories(){ return this.calories; }

    /**
     * @return      Returns the items currently in the hamper 
     * @see ArrayList 
     */
    public ArrayList<Food> getItems(){ return this.items; }

    /** 
     * @return  The amount of grain calories that have been added to the hamper
     */
    public double getAddedGrain(){
        double total = 0;
        for(Food item : items){
            total += item.getGrains();
        }
        return total;
    }

    /** 
     * @return  The amount of fruit and vegtable calories that have been added 
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
     * @return  The amount of protein calories that have been added to the 
     *          hamper
     */
    public double getAddedProtein(){
        double total = 0;
        for(Food item : items){
            total += item.getProtein();
        }
        return total;
    }

    /** 
     * @return  The amount of other calories that have been added to the hamper
     */
    public double getAddedOther(){
        double total = 0;
        for(Food item : items){
            total += item.getOther();
        }
        return total;
    }

   /** 
     * @return  The amount of calories that have been added to the hamper
     */
    public int getAddedCalories(){
        int total = 0;
        for(Food item : items){
            total += item.getCalories();
        }
        return total;
    }
    
/****************************** Methods ***************************************/
    /**
     * Clears all of the food that is currently in the hamper
     */
    public void clearItems(){ this.items.clear(); }

    /** 
     * Adds a food object to the hamper
     * 
     * @param item      The item to be added
     * @see Food
     */
    public void addFood(Food item){
        items.add(item);
    }
    
    /**
     * Calculates the amount of calories the hamper must have based on the 
     * clients that will recieve it
     */
    public void calcCaloriesNeeded(){
        for(int i = 0; i < clients.length; i ++){
            wholeGrains += clients[i].getGrains();
            fruitVeg += clients[i].getFruitVeg();
            protein += clients[i].getProtein();
            other += clients[i].getOther();
            calories += clients[i].getCalories();
        }
    }

    /** 
     * @return  The difference between the protein added and protein required
     */
    public double calcProDiff(){
        double added = getAddedProtein();
        return added - this.protein;
    }

    
    /** 
     * @return  The difference between the fruit and vegtable added, and fruit 
     *          and vegtable required
     */
    public double calcFruitDiff(){
        double added = getAddedFruitVeg();
        return added - this.fruitVeg;
    }

    
    /** 
     * @return  The difference between the grain added and grain required
     */
    public double calcGrainDiff(){
        double added = getAddedGrain();
        return added - this.wholeGrains;
    }
    
    /** 
     * @return  The difference between the other calories added and other 
     *          calories required
     */
    public double calcOtherDiff(){
        double added = getAddedOther();
        return added - this.other;
    }
    
    /** 
     * @return  The difference between the total calories added and total 
     *          calories required 
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

    /**
     * Sorts the items in the hamper based on the name of the food
     */
    public void sortHamper(){
        Collections.sort(items, Comparator.comparing(Food::getDescription));
    }
}
