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
import java.util.HashSet;
import java.util.Random;

/**
 * InventoryData is the class that handles creating the hampers and managing the
 * stock.
 * 
 * @author Tammy Pham, Steven Poon, Bill Thai and Alex Yeap
 * @version 3.4
 * @since 1.0
 */

public class InventoryData {
/*************************** Member Variables *********************************/
    private ArrayList<Food> stock;
    private ArrayList<Food> usedFood = new ArrayList<>();
    private Client[] data;
    private static Client adultMale;
    private static Client adultFemale;
    private static Client childOver8;
    private static Client childUnder8;
    private HashSet<Integer> takenValues;
    private Hamper hamper;
    private ReadDataBase myJDBC;

/***************************** Constructor ************************************/
    /**
     * Reads the database and fills the inventory and gets the client 
     * information
     */
    InventoryData(){
        myJDBC = new ReadDataBase(
            "jdbc:mysql://localhost:3306/food_inventory", 
            "student", 
            "ensf"
        );
        myJDBC.initializeConnection();
        stock = myJDBC.fillInventory("AVAILABLE_FOOD");
        data = myJDBC.getClientInfo("DAILY_CLIENT_NEEDS");
        setClientStats();
        sortInventory();
 
    }

/****************************** Setters ***************************************/
    /**
     * Sets the client statistics for each type of client and stores it
     */
    public void setClientStats(){
        adultMale = data[0];
        adultFemale = data[1];
        childOver8 = data[2];
        childUnder8 = data[3];
    }

/****************************** Getters ***************************************/
    /**
     * @return  The instance of ReadDataBase being used to fill the inventory
     * @see ReadDataBase
     */
    public ReadDataBase getDataBase(){ return this.myJDBC; }

    /**
     * @return The food that has been added the hampers
     * @see Food
     * @see ArrayList
     */
    public ArrayList<Food> getUsedFood(){ return this.usedFood; }
    
    /** 
     * @param type      The type of client ie adult female, adult male, ...
     * @return          A client that has the information of the input
     * @see Client
     */
    public static Client getClient(String type){
        Client myClient = null;
        switch(type){
            case "adultMale":
                myClient = adultMale;
                break;
            case "adultFemale":
                myClient = adultFemale;
                break;
            case "childOver8":
                myClient = childOver8;
                break;
            case "childUnder8":
                myClient = childUnder8;
                break;
        }
        return myClient;
    }

    /** 
     * @return  The amount of protein in stock
     */
    public double getProteinInStock(){
        double totalCal = 0;
        for(Food item : stock){
            totalCal += item.getProtein();
        }
        return totalCal;
    }

    /** 
     * @return  The amount of fruits and vegetables in stock
     */
    public double getFruitInStock(){
        double totalCal = 0;
        for(Food item : stock){
            totalCal += item.getFruitVeg();
        }
        return totalCal;
    }

    /** 
     * @return  The amount of grain in stock
     */
    public double getGrainInStock(){
        double totalCal = 0;
        for(Food item : stock){
            totalCal += item.getGrains();
        }
        return totalCal;
    }

    
    /** 
     * @return  The amount of other in stock
     */
    public double getOtherInStock(){
        double totalCal = 0;
        for(Food item : stock){
            totalCal += item.getOther();
        }
        return totalCal;
    }
    
/****************************** Methods ***************************************/
    /** 
     * Uses an algorithm to find the best hamper and then removes the items from
     * stock. 
     * <p>
     * The algorithm works by selecting random food from the inventory.  Once it
     * has satisfied the total calorie requirments, it checks that all of the
     * other fields have been filled as well.  If they have not the hamper is 
     * discarded and a new one is tried until all of the fields have been 
     * filled.  It is then added to an array and the program finds 2 more.  Once
     * three potential hampers have been found it choses the one with the least
     * waste and returns it.
     * 
     * @param list      An array of clients that represents the clients that 
     *                  will be recieving the hamper
     * @return          A hamper with the least waste that fills the requirments
     *                  of the clients recieving it
     * @see Hamper
     * @see Client
     */
    public Hamper findPossibleHampers(Client[] list){
        Hamper[] hampers = new Hamper[3];
        Random rand = new Random();
        int size = stock.size();
        int i = 0;
        int minDelta = 1000;
        this.hamper = new Hamper(list);
        /*  Uses a hashtable to keep track of the indices of the food removed 
         *  from stock
         */
        takenValues = new HashSet<>();
        // Check if the inventory can handle the hamper
        if(checkInventory(this.hamper) != 0){ 
            return null; 
        }
        while(true){
            while(this.hamper.calcCalorieDiff() < 100){
                // Find a random index to add
                int n = rand.nextInt(size);
                // Ensure that the item is not already taken
                while(!takenValues.add(n)){
                    n = rand.nextInt(size);
                }
                // Add the item to the hamper
                this.hamper.addFood(stock.get(n));
            }
            // Check if all requirments are satisfied 
            if(
                (this.hamper.calcCalorieDiff() < 300) &&
                (this.hamper.calcFruitDiff() > 0) &&
                (this.hamper.calcGrainDiff() > 0) &&
                (this.hamper.calcProDiff() > 0) &&
                (this.hamper.calcOtherDiff() > 0)
            ){
                // Add hamper to array
                hampers[i] = this.hamper;
                i ++;
                takenValues.clear();
                // If there are already 3 hampers in array, break
                if(i == 3){
                    break;
                }
            }else{
                // Empty hamper and taken values
                this.hamper.clearItems();
                takenValues.clear();
            }
        }
        // Check to find hamper with the least waste
        for(i = 0; i < 3; i ++){
            if(minDelta > hampers[i].calcCalorieDiff()){
                minDelta = hampers[i].calcCalorieDiff();
                this.hamper = hampers[i];
            }
        }
        // Remove the items added
        removeFromStock();
        return this.hamper;
    }

    /**
     * Removes the items that have been added to the hamper from stock and add 
     * them to usedFood
     */
    public void removeFromStock(){
        for(Food item : this.hamper.getItems()){
            this.usedFood.add(item);
            this.stock.remove(item);
        }
    }

    /**
     * Sorts the stock by total calories in decending order
     */
    public void sortInventory(){
        Collections.sort(stock, Comparator.comparing(Food::getCalories));
        Collections.reverse(stock);
    }

    
    /** 
     * Checks to see if there is enough stock to fill the hamper
     * 
     * @param hamper    The hamper that is about to be created
     * @return          Returns 0 if there is enough inventory to fill the 
     *                  hamper
     */
    public int checkInventory(Hamper hamper){
        if(hamper.getProtien() > getProteinInStock()){
            return 1;
        }
        if(hamper.getFruitVeg() > getFruitInStock()){
            return 2;
        }
        if(hamper.getOther() > getOtherInStock()){
            return 3;
        }
        if(hamper.getGrain() > getGrainInStock()){
            return 4;
        }
        return 0;
    }
}
