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

/**
 * An instance of this class is used to represent the caloric properties of an
 * item from the inventory.  Converts each catagory from percentage to calorie
 * amount
 * 
 * @author Tammy Pham, Steven Poon, Bill Thai and Alex Yeap
 * @version 1.4
 * @since 1.0
 */

public class Food{
/*************************** Member Variables *********************************/
    private int itemID;
    private String description;
    private double wholeGrains;
    private double protein;
    private double fruitVeg;
    private double other;
    private int calories;

/***************************** Constructor ************************************/
    /**
     * Creates a food object that holds the caloric information of the item
     * 
     * @param itemID        The ID number of the item
     * @param description   The description of the item
     * @param wholeGrains   The percentage of grain 
     * @param fruitVeg      The percentage of fruit and vegtables 
     * @param protein       The percentage of protein
     * @param other         The percentage of other
     * @param calories      The calories in the item  
     */
    Food(
        int itemID,
        String description,
        int wholeGrains, 
        int fruitVeg, 
        int protein, 
        int other, 
        int calories
    ){
        this.itemID = itemID;
        this.description = description;
        this.wholeGrains = wholeGrains * 0.01 * calories;
        this.fruitVeg = fruitVeg * 0.01 * calories;
        this.protein = protein * 0.01 * calories;
        this.other = other * 0.01 * calories;
        this.calories = calories;
    }

/******************************* Getters **************************************/
    /**
     * @return The item ID of the food
     */    
    public int getItemID(){ return this.itemID; }

    /**
     * @return The description of the food
     */
    public String getDescription(){ return this.description; }

    /**
     * @return The grain component of the food
     */
    public double getGrains(){ return this.wholeGrains; }

    /**
     * @return The protein component of the food
     */
    public double getProtein(){ return this.protein; }

    /**
     * @return The fruit and vegtable component of the food
     */
    public double getFruitVeg(){ return this.fruitVeg; }

    /**
     * @return The other component of the food
     */
    public double getOther(){ return this.other; }

    /**
     * @return The calories in the food
     */
    public int getCalories(){ return this.calories; }
}
