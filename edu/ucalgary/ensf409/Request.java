/**
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
 * Request  
 * 
 * @author Tammy Pham, Steven Poon, Bill Thai and Alex Yeap
 * @version 3.4
 * @since 1.0
 */

public class Request extends InventoryData{
/*************************** Member Variables *********************************/
    private static int[] numAM;
    private static int[] numAF;
    private static int[] numCU8;
    private static int[] numCO8;
    private Client[][] clientList;
    private int numHampers;
    private Hamper[] hampers;

/***************************** Constructor ************************************/
    /**
     * Sets the variables and initializes the arrays
     * 
     * @param numAMIn       The number of adult males for each hamper
     * @param numAFIn       The number of adult females for each hamper
     * @param numCO8In      The number of childern over 8 for each hamper
     * @param numCU8In      The number of childern under 8 for each hamper
     * @param numHampers    The number of hampers to be made
     */
    Request(
        int numAMIn[], 
        int numAFIn[], 
        int numCO8In[], 
        int numCU8In[], 
        int numHampers
    ){
        numAF = numAFIn;
        numAM = numAMIn;
        numCU8 = numCU8In;
        numCO8 = numCO8In;
        this.numHampers = numHampers;
        this.hampers = new Hamper[numHampers];
        this.clientList = new Client[numHampers][40];
    }

/******************************* Getters **************************************/
    /**
     * @return  The number of hampers in the order
     */
    public int getNumHampers(){ return this.numHampers; }

    /**
     * @param i     Which hamper in the order
     * @return      The number of adult males in the order at i
     */
    public static int getNumAM(int i){ return numAM[i]; }

    /**
     * @param i     Which hamper in the order
     * @return      The number of adult females in the order at i
     */
    public static int getNumAF(int i){ return numAF[i]; }

    /**
     * @param i     Which hamper in the order
     * @return      The number of childern under 8 in the order at i
     */
    public static int getNumCU(int i){ return numCU8[i]; }

    /**
     * @param i     Which hamper in the order
     * @return      The number of childern over 8 in the order at i
     */
    public static int getNumCO(int i){ return numCO8[i]; }
    
/****************************** Methods ***************************************/
    /**
     * Takes the information about the order and creates the client list for 
     * each hamper.  It then calls upon the 
     *        
     * @return      returns 0 if filling the hamper and creating the file was 
     *              sucessful, returns 1 otherwise
     */
    public int fillHampers(){
        for(int i = 0; i < this.numHampers; i ++){
            this.clientList[i] = new Client[
                numAF[i] 
                + numAM[i] 
                + numCO8[i]
                + numCU8[i]
            ];
            createClientList(i);
            hampers[i] = findPossibleHampers(clientList[i]);
            if(hampers[i] == null){
                return 1;
            }
            hampers[i].sortHamper();
        }
        OrderForm obe = new OrderForm();
        obe.writeTofile(hampers);
        getDataBase().removeItems(getUsedFood());
        return 0;
    }
    
    /** 
     * Adds the number of each client type to the clientList at the specified
     * index
     * 
     * @param k     The index of which hamper is to be filled   
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
