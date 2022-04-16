

package edu.ucalgary.ensf409;

public class Request extends InventoryData{
    // Member Variables
    private int numAM;
    private int numAF;
    private int numCU8;
    private int numCO8;
    private Client[] clientList;
    private int numHampers;
    private Hamper[] hampers;

    // Constructors
    Request(int numAM, int numAF, int numCO8, int numCU8, int numHampers){
        this.numAF = numAF;
        this.numAM = numAM;
        this.numCU8 = numCU8;
        this.numCO8 = numCO8;
        this.numHampers = numHampers;
        this.clientList = new Client[numAF + numAM + numCO8 + numCU8];
        this.hampers = new Hamper[numHampers];
        createClientList();
        for(int i = 0; i < numHampers; i ++){
            hampers[i] = findPossibleHampers(clientList);
            System.out.println("Cal " + hampers[i].getCalories());
            System.out.println("Pro " + hampers[i].getProtien());
            System.out.println("Fruit " + hampers[i].getFruitVeg());
            System.out.println("Grain " + hampers[i].getGrain());
            System.out.println("Other " + hampers[i].getOther());
            System.out.println();
            System.out.println("Cal Added " + hampers[i].getAddedCalories());
            System.out.println("Pro Added " + hampers[i].getAddedProtien());
            System.out.println("Fruit Added " + hampers[i].getAddedFruitVeg());
            System.out.println("Grain Added " + hampers[i].getAddedGrain());
            System.out.println("Other Added " + hampers[i].getAddedOther());
            System.out.println();
            System.out.println("Cal delta " + hampers[i].calcCalorieDiff());
            System.out.println();
        }
        

        
    }
    // Setters

    // Getters
    public int getNumHampers(){ return this.numHampers; }
    public int getNumAM(){ return this.numAM; }
    

    // Methods
    public void createClientList(){
        int j = 0;
        for(int i = 0; i < numAM; i ++){ 
            clientList[j] = InventoryData.getClient("adultMale");
            j ++;
        }
        for(int i = 0; i < numAF; i ++){
            clientList[j] = InventoryData.getClient("adultFemale");
            j ++;
        }
        for(int i = 0; i < numCO8; i ++){
            clientList[j] = InventoryData.getClient("childOver8");
            j ++;
        }
        for(int i = 0; i < numCU8; i ++){
            clientList[j] = InventoryData.getClient("childUnder8");
            j ++;
        }
    }

}
