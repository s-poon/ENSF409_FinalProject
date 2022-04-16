package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;

public class ReadDataBase{
    // Member Variables
    private final String DBURL;
    private final String USERNAME;
    private final String PASSWORD;   

    private Connection dbConnect;
    private ResultSet results; 
    // Constructors
    ReadDataBase(String url, String user, String pw){
        this.DBURL = url;
        this.USERNAME = user;
        this.PASSWORD = pw;
    }
    // Setters

    // Getters
    String getDburl(){ return this.DBURL; }
    String getUsername(){ return this.USERNAME; }
    String getPassword(){ return this.PASSWORD; }

    // Methods
    public void initializeConnection(){
        try{
            dbConnect = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

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