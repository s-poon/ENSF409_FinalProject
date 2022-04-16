package edu.ucalgary.ensf409;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;

public class InventoryData {
    // Member Variables
    private ArrayList<Food> stock;
    private ArrayList<Food> usedFood = new ArrayList<>();
    private Client[] data;
    private static Client adultMale;
    private static Client adultFemale;
    private static Client childOver8;
    private static Client childUnder8;
    private HashSet<Integer> takenValues;
    private Hamper hamper;
    ReadDataBase myJDBC;

    // Constructors
    InventoryData(){
        myJDBC = new ReadDataBase(
            "jdbc:mysql://localhost:3306/food_inventory", 
            "student", 
            "ensf"
        );
        myJDBC.initializeConnection();
        stock = myJDBC.fillInventory("AVAILABLE_FOOD");
        data = myJDBC.getClientInfo("DAILY_CLIENT_NEEDS");
        setClientStats();
        sortInventory();
 
    }

    // Setters
    public void setClientStats(){
        adultMale = data[0];
        adultFemale = data[1];
        childOver8 = data[2];
        childUnder8 = data[3];
    }

    // Getters
    // public ReadDataBase dataBase(){ return this.myJDBC; }
    public ArrayList<Food> getUsedFood(){ return this.usedFood; }
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
        Hamper[] hampers = new Hamper[3];
        HashSet<Integer> values = new HashSet<>();
        Random rand = new Random();
        int size = stock.size(), i = 0, minDelta = 1000;;
        hamper = new Hamper(list);
        if(checkInventory(hamper) != 0){ 
            return null; 
        }
        System.out.println("hello there");
        takenValues = new HashSet<>();
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
        for(i = 0; i < 3; i ++){
            if(minDelta > hampers[i].calcCalorieDiff()){
                minDelta = hampers[i].calcCalorieDiff();
                hamper = hampers[i];
            }
        }
        removeFromStock();
        return hamper;
    }

    public void removeFromStock(){
        for(Food item : hamper.getItems()){
            usedFood.add(item);
            stock.remove(item);

        }
    }

    public void sortInventory(){
        Collections.sort(stock, Comparator.comparing(Food::getCalories));
        Collections.reverse(stock);
    }

    public int checkInventory(Hamper hamper){

        double fruit = calcFruit();
        double other = calcOther();
        double grain = calcGrain();
        double protein = calcPro();

        if(hamper.getProtien() > calcPro()){
            System.out.println(hamper.getProtien());
            System.out.println(protein);
            return 1;
        }
        if(hamper.getFruitVeg() > calcFruit()){
            return 2;
        }
        if(hamper.getOther() > calcOther()){
            return 3;
        }
        if(hamper.getGrain() > calcGrain()){
            return 4;
        }
        return 0;
    }

    public double calcPro(){
        double totalCal = 0;
        for(Food item : stock){
            totalCal += item.getProtien();
        }
        return totalCal;
    }

    public double calcFruit(){
        double totalCal = 0;
        for(Food item : stock){
            totalCal += item.getFruitVeg();
        }
        return totalCal;
    }

    public double calcGrain(){
        double totalCal = 0;
        for(Food item : stock){
            totalCal += item.getGrains();
        }
        return totalCal;
    }

    public double calcOther(){
        double totalCal = 0;
        for(Food item : stock){
            totalCal += item.getOther();
        }
        return totalCal;
    }
}
