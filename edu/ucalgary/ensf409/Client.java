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
 * An object from this class is used to hold the calorie requirments for each
 * type of client that the food bank will serve
 * 
 * @author Tammy Pham, Steven Poon, Bill Thai and Alex Yeap
 * @version 1.5
 * @since 1.0
 */
public class Client {
/*************************** Member Variables *********************************/
    private String type;
    private int clientID;
    private double grains;
    private double protien;
    private double fruitVeg;
    private double other;
    private double calories;

/***************************** Constructors************************************/
    /**
     * Takes the needs of the client and converts each catagory from percentages
     * to calorie values
     * 
     * @param type      The type of client it is i.e, adult female, adult male,
     *                  etc...
     * @param clientID  The client ID number
     * @param grains    The percentage of grain needed in their diet
     * @param protien   The percentage of protien needed in their diet
     * @param fruitVeg  The percentage of fruits and vegtables needed in their 
     *                  diet
     * @param other     The percentage of other needed in their diet
     * @param calories  The calories needed in their diet
     */
    Client(
        String type,
        int clientID,
        int grains,
        int protien,
        int fruitVeg,
        int other,
        int calories
    ){
        this.calories = calories;
        this.type = type;
        this.clientID = clientID;
        this.grains = grains * 0.01 * this.calories;
        this.protien = protien * 0.01 * this.calories;
        this.fruitVeg = fruitVeg * 0.01 * this.calories;
        this.other = other * 0.01 * this.calories;
    }

/******************************* Getters **************************************/
    /**
     * @return The client ID
     */
    public int getClientID(){ return this.clientID; }

    /**
     * @return  The grain calories the client needs in a week
     */
    public double getGrains(){ return this.grains; }

    /**
     * @return  The protein calories the client needs in a week
     */
    public double getProtein(){ return this.protien; }

    /**
     * @return  The fruit and vegtable calories the client needs in a week
     */
    public double getFruitVeg(){ return this.fruitVeg; }

    /**
     * @return  The other calories the client needs in a week
     */
    public double getOther(){ return this.other; }

    /**
     * @return  The total calories the client needs in a week
     */
    public double getCalories(){ return this.calories; }

    /**
     * @return  The type of client
     */
    public String getType(){ return this.type; }
}
