package edu.ucalgary.ensf409;

public class Food {
    // Member Variables
    private String description;
    private double wholeGrains;
    private double protien;
    private double fruitVeg;
    private double other;
    private double calories;

    // Constructor
    Food(
        String description,
        int wholeGrains, 
        int fruitVeg, 
        int protien, 
        int other, 
        int calories
    ){
        this.description = description;
        this.wholeGrains = wholeGrains * 0.01 * calories;
        this.fruitVeg = fruitVeg * 0.01 * calories;
        this.protien = protien * 0.01 * calories;
        this.other = other * 0.01 * calories;
        this.calories = calories;
    }

    // Setters

    // Getters
    public double getGrains(){ return this.wholeGrains; }
    public double getProtien(){ return this.protien; }
    public double getFruitVeg(){ return this.fruitVeg; }
    public double getOther(){ return this.other; }
    public double getCalories(){ return this.calories; }
    // Methods
}
