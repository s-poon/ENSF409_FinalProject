package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

public class InventoryData {
    // Member Variables
    private ArrayList<Food> stock;
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
        sortInventoryByType();
 
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

    public Hamper findPossibleHampers(Client[] list){
        Hamper hamper = new Hamper(list);
        Hamper[] hampers = new Hamper[3];
        HashSet<Integer> takenValues = new HashSet<>();
        int size = stock.size(), i = 0;
        Random rand = new Random();
        while(true){
            while(hamper.calcCalorieDiff() < 100){
                int n = rand.nextInt(size);
                while(!takenValues.add(n)){
                    n = rand.nextInt(size);
                }
                hamper.addFood(stock.get(n));
                size --;
            }
            if(
                (hamper.calcCalorieDiff() < 300) &&
                (hamper.calcFruitDiff() > 0) &&
                (hamper.calcGrainDiff() > 0) &&
                (hamper.calcProDiff() > 0) &&
                (hamper.calcOtherDiff() > 0)
            ){
                if(i == 3){
                    break;
                }
                hampers[i] = hamper;
                i ++;
            }else{
                hamper.clearItems();
                size = stock.size();
                takenValues.clear();
            }
        }
        int minDelta = 1000;
        for(i = 0; i < 3; i ++){
            if(minDelta > hampers[i].calcCalorieDiff()){
                minDelta = hampers[i].calcCalorieDiff();
                hamper = hampers[i];
            }
        }
        return hamper;
    }

    public void sortInventoryByType(){
        Collections.sort(stock, Comparator.comparing(Food::getCalories));
        Collections.reverse(stock);
    }
}
