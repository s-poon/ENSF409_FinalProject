package edu.ucalgary.ensf409;

public class Food {
    // Member Variables
    private String description;
    int wholeGrains;
    int protien;
    int fruitVeg;
    int other;
    int calories;

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
        this.wholeGrains = wholeGrains;
        this.fruitVeg = fruitVeg;
        this.protien = protien;
        this.other = other;
        this.calories = calories;
    }

    // Setters

    // Getters

    // Methods
}
