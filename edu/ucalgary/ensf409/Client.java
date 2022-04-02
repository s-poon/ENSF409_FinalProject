package edu.ucalgary.ensf409;

public class Client {
    // Member Variables
    private String type;
    private int clientID;
    private double grains;
    private double protien;
    private double fruitVeg;
    private double other;
    private double calories;

    // Constructors
    Client(
        String type,
        int clientID,
        int grains,
        int protien,
        int fruitVeg,
        int other,
        int calories
    ){
        this.type = type;
        this.clientID = clientID;
        this.grains = grains * 0.01 * calories;
        this.protien = protien * 0.01 * calories;
        this.fruitVeg = fruitVeg * 0.01 * calories;
        this.other = other * 0.01 * calories;
        this.calories = calories;
    }

    // Setters
    // Getters
    public String getType(){ return this.type; }
    public int getID(){ return this.clientID; }
    public double getGrains(){ return this.grains; }
    public double getProtien(){ return this.protien; }
    public double getFruitVeg(){ return this.fruitVeg; }
    public double getOther(){ return this.other; }
    public double getCalories(){ return this.calories; }

    // Methods

}
