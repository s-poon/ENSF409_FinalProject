package edu.ucalgary.ensf409;

import java.util.ArrayList;

public class InventoryData {
    // Member Variables
    private ArrayList<Food> stock;
    private static Client[] data;
    private static Client adultMale;
    private static Client adultFemale;
    private static Client childOver8;
    private static Client childUnder8;

    // Constructors
    InventoryData(){
        ReadDataBase myJDBC = new ReadDataBase(
            "jdbc:mysql://localhost:3306/food_inventory", 
            "student", 
            "ensf"
        );
        myJDBC.initializeConnection();
        stock = myJDBC.fillInventory("AVAILABLE_FOOD");
        data = myJDBC.getClientInfo("DAILY_CLIENT_NEEDS");
        setClientStats();

    }
    // Setters
    public static void setClientStats(){
        adultMale = data[0];
        adultFemale = data[1];
        childOver8 = data[2];
        childUnder8 = data[3];
    }
    // Getters
    public ArrayList<Food> getStock(){ return this.stock; }
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
    // Methods
    public void moveItem(){}
    public void sortInventory(String catagory){}
    

}
