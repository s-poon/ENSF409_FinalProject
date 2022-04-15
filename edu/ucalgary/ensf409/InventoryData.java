package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InventoryData {
    // Member Variables
    private ArrayList<Food> stock;
    private ArrayList<Food> protien;
    private ArrayList<Food> fruitVeg;
    private ArrayList<Food> grain;
    private ArrayList<Food> other;
    private ArrayList<Food> mixed;
    private Client[] data;
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
    public void setClientStats(){
        adultMale = data[0];
        adultFemale = data[1];
        childOver8 = data[2];
        childUnder8 = data[3];
    }
    // Getters
    public static Client getClient(String type){
        Client myClient = null;
        switch(type){
            case "adultMale":
                myClient = adultMale;
            case "adultFemale":
                myClient = adultFemale;
            case "childOver8":
                myClient = childOver8;
            case "childUnder8":
                myClient = childUnder8;
        }
        return myClient;
    }
    // Methods

    public Hamper fillHamper(){
        hamper = new 

        return new Hamper();
    }

    public void sortInventoryByType(){
        for(Food item : stock){
            if(item.getFruitVegPercent() > 60){
                fruitVeg.add(item);
            }else if(item.getGrainsPercent() > 60){
                grain.add(item);
            }else if(item.getProtienPercent() > 60){
                protien.add(item);
            }else if(item.getOtherPercent() > 60){
                other.add(item);
            }else{
                mixed.add(item);
            }
        }
    }

    public void sortByCalories(ArrayList<Food> list){
        Collections.sort(list, Comparator.comparing(Food::getCalories));
    }
    

}
