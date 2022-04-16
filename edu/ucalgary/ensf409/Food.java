package edu.ucalgary.ensf409;

public class Food implements Cloneable{
    // Member Variables
    private int itemID;
    private String description;
    private double wholeGrains;
    private double protien;
    private double fruitVeg;
    private double other;
    private int calories;
    private int wholeGrainsPercent;
    private int protienPercent;
    private int fruitVegPercent;
    private int otherPercent;

    // Constructor
    Food(
        int itemID,
        String description,
        int wholeGrains, 
        int fruitVeg, 
        int protien, 
        int other, 
        int calories
    ){
        this.itemID = itemID;
        this.wholeGrainsPercent = wholeGrains;
        this.fruitVegPercent = fruitVeg;
        this.protienPercent = protien;
        this.otherPercent = other;
        this.description = description;
        this.wholeGrains = wholeGrains * 0.01 * calories;
        this.fruitVeg = fruitVeg * 0.01 * calories;
        this.protien = protien * 0.01 * calories;
        this.other = other * 0.01 * calories;
        this.calories = calories;
    }

    // Setters

    // Getters
    public int getGrainsPercent(){ return this.wholeGrainsPercent; }
    public int getProtienPercent(){ return this.protienPercent; }
    public int getFruitVegPercent(){ return this.fruitVegPercent; }
    public int getOtherPercent(){ return this.otherPercent; }

    public int getItemID(){ return this.itemID; }
    public String getDescription(){ return this.description; }
    public double getGrains(){ return this.wholeGrains; }
    public double getProtien(){ return this.protien; }
    public double getFruitVeg(){ return this.fruitVeg; }
    public double getOther(){ return this.other; }
    public int getCalories(){ return this.calories; }

    // // Methods
    @Override
    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}
