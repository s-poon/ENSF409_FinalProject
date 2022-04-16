package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

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
        protien = new ArrayList<>();
        fruitVeg = new ArrayList<>();
        grain = new ArrayList<>();
        other = new ArrayList<>();
        mixed = new ArrayList<>();
        try{
            sortInventoryByType();
        }
        catch(CloneNotSupportedException e){
            System.out.println("Bruh");
            System.exit(1);
        }


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

    public Hamper findPossibleHampers(Client[] list){
        Hamper hamper = new Hamper(list);
        HashSet<Integer> takenValues = new HashSet<>();
        int size = stock.size();
        Random rand = new Random();
        while(true){
            while(hamper.calculateCalorieDiff() < 0){
                int n = rand.nextInt(size);
                while(!takenValues.add(n)){
                    n = rand.nextInt(size);
                }
                hamper.addFood(stock.get(n));
                size --;
            }
            if(
                (hamper.calculateFruitDiff() < 0) &&
                (hamper.calculateGrainDiff() < 0) &&
                (hamper.calculateProtienDiff() < 0) &&
                (hamper.calculateOtherDiff() < 0)
            ){
                break;
            }else{
                hamper.clearItems();
                size = stock.size();
                takenValues.clear();
            }

        }
        return hamper;
    }

    public void sortInventoryByType()throws CloneNotSupportedException{
        Collections.sort(stock, Comparator.comparing(Food::getCalories));
        Collections.reverse(stock);
        for(Food item : stock){
            if(item.getFruitVegPercent() > 60){
                fruitVeg.add((Food)item.clone());
            }else if(item.getGrainsPercent() > 60){
                grain.add((Food)item.clone());
            }else if(item.getProtienPercent() > 60){
                protien.add((Food)item.clone());
            }else if(item.getOtherPercent() > 60){
                other.add((Food)item.clone());
            }else{
                mixed.add((Food)item.clone());
            }
        }
    }

    

}
