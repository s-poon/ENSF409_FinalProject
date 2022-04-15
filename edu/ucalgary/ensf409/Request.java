package edu.ucalgary.ensf409;




public class Request {
    // Member Variables
    private int numAM;
    private int numAF;
    private int numCU8;
    private int numCO8;
    private Client[] clientList;
    private int numHampers;

    // Constructors
    Request(int numAM, int numAF, int numCO8, int numCU8, int numHampers){
        this.numAF = numAF;
        this.numAM = numAM;
        this.numCU8 = numCU8;
        this.numCO8 = numCO8;
        this.numHampers = numHampers;
        this.clientList = new Client[numAF + numAM + numCO8 + numCU8];
        createClientList();
    }
    // Setters

    // Getters
    public int getNumHampers(){ return this.numHampers; }
    

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
