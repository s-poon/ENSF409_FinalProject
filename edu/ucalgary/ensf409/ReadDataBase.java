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

import java.sql.*;
import java.util.ArrayList;

/**
 * This class handles reading from the database.  It will get the client
 * information and add food to the inventory.
 * 
 * @author Tammy Pham, Steven Poon, Bill Thai and Alex Yeap
 * @version 3.4
 * @since 1.0
 */

public class ReadDataBase{
/*************************** Member Variables *********************************/
    private final String DBURL;
    private final String USERNAME;
    private final String PASSWORD;   
    private Connection dbConnect;
    private ResultSet results; 

/***************************** Constructor ************************************/
    /**
     * Accepts the database to be accessed, the username, and password
     * 
     * @param url       The database to be accessed
     * @param user      The username
     * @param pw        The password
     */
    ReadDataBase(String url, String user, String pw){
        this.DBURL = url;
        this.USERNAME = user;
        this.PASSWORD = pw;
    }

/****************************** Methods ***************************************/
    /** 
     * Initializes the connection to the database.
     */
    public void initializeConnection(){   
        try{
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        }
        catch(SQLException e){
            System.out.println("Could not connect to Database");
            e.printStackTrace();
        }
    }

    
    /** 
     * Removes items that have been added to hampers from the database
     * 
     * @param items     The items that are to be removed from the database
     * @see Food
     * @see ArrayList
     */
    public void removeItems(ArrayList<Food> items){
        try{
            for(Food item : items){
                String query = "DELETE FROM available_food WHERE itemid = ?";
                PreparedStatement prepStmt = dbConnect.prepareStatement(query);
                int thing = item.getItemID();
                prepStmt.setString(1, String.valueOf(thing));
                prepStmt.executeUpdate();
                prepStmt.close();
            }
            
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    
    /** 
     * Takes the food from the table, makes each one a food object, and then
     * adds it to an arraylist.  Once all of the items have been read and added, 
     * the arraylist is returned.
     * 
     * @param tableName     The name of the table where the available food is
     *                      kept
     * @return              An ArrayList of type food that contains the food
     *                      object representation of the items in the table
     * @see ArrayList
     * @see Food
     */
    public ArrayList<Food> fillInventory(String tableName){
        ArrayList<Food> temp = new ArrayList<Food>();
        try{
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + tableName);
            while(results.next()){
                Food itemToAdd = new Food(
                    results.getInt("ItemID"),
                    results.getString("Name"),
                    results.getInt("GrainContent"),
                    results.getInt("FVContent"),
                    results.getInt("ProContent"),
                    results.getInt("Other"),
                    results.getInt("Calories")
                );
                temp.add(itemToAdd);
            }
            myStmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return temp;
    }

    /** 
     * Takes the client from the table, makes each one a client object, and then
     * adds it to an array.  Once all of the items have been read and added the 
     * array is returned.
     * 
     * @param tableName     The name of the table where the client information 
     *                      is located
     * @return              A client array that contatins the information for 
     *                      each client type
     * @see Client
     */
    public Client[] getClientInfo(String tableName){
        Client[] temp = new Client[4];
        int i = 0;
        try{
            Statement myStmt = dbConnect.createStatement();
            results = myStmt.executeQuery("SELECT * FROM " + tableName);
            while(results.next()){
                Client clientToAdd = new Client(
                    results.getString("Client"),
                    results.getInt("ClientID"),
                    results.getInt("WholeGrains"),
                    results.getInt("FruitVeggies"),
                    results.getInt("Protein"),
                    results.getInt("Other"),
                    results.getInt("Calories")
                );
                temp[i] = clientToAdd;
                i ++;
            }
            myStmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return temp;
    }
    
    /**
     * Closes the database
     */
    public void close(){
        try{
            results.close();
            dbConnect.close();
            System.out.println("DataBase was sucessfully closed");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}