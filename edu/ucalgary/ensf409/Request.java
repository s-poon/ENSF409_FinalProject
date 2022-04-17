/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

package edu.ucalgary.ensf409;

/**
 * 
 * 
 * @author Group 24
 * @version 3.4
 * @since 1.0
 */

public class Request extends InventoryData{
    // Member Variables
    private static int[] numAM;
    private static int[] numAF;
    private static int[] numCU8;
    private static int[] numCO8;
    private Client[][] clientList;
    private int numHampers;
    private Hamper[] hampers;

    // Constructors
    Request(int numAMIn[], int numAFIn[], int numCO8In[], int numCU8In[], int numHampers){
        numAF = numAFIn;
        numAM = numAMIn;
        numCU8 = numCU8In;
        numCO8 = numCO8In;
        this.numHampers = numHampers;
        this.hampers = new Hamper[numHampers];
        this.clientList = new Client[numHampers][40];
    }
    
    // Setters

    // Getters
    public int getNumHampers(){ return this.numHampers; }
    public static int getNumAM(int i){ return numAM[i]; }
    public static int getNumAF(int i){ return numAF[i]; }
    public static int getNumCU(int i){ return numCU8[i]; }
    public static int getNumCO(int i){ return numCO8[i]; }
    
    
    /** 
     * @param none
     * @return int
     */
    // Methods
    public int fillHampers(){
        for(int i = 0; i < this.numHampers; i ++){
            this.clientList[i] = new Client[numAF[i] + numAM[i] + numCO8[i] + numCU8[i]];
            createClientList(i);
            hampers[i] = findPossibleHampers(clientList[i]);
            if(hampers[i] == null){
                return 1;
            }
            hampers[i].sortHamper();
        }
        OrderForm obe = new OrderForm();
        obe.writeTofile(hampers);
        myJDBC.removeItems(getUsedFood());
        return 0;
    }

    
    /** 
     * @param k
     */
    public void createClientList(int k){
        int j = 0;
        for(int i = 0; i < numAM[k]; i ++){ 
            clientList[k][j] = InventoryData.getClient("adultMale");
            j ++;
        }
        for(int i = 0; i < numAF[k]; i ++){
            clientList[k][j] = InventoryData.getClient("adultFemale");
            j ++;
        }
        for(int i = 0; i < numCO8[k]; i ++){
            clientList[k][j] = InventoryData.getClient("childOver8");
            j ++;
        }
        for(int i = 0; i < numCU8[k]; i ++){
            clientList[k][j] = InventoryData.getClient("childUnder8");
            j ++;
        }
    }

}
