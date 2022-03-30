package edu.ucalgary.ensf409;

import java.sql.*;

public class ReadDataBase{
    // Member Variables
    public final String DBURL;
    public final String USERNAME;
    public final String PASSWORD;   

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
    public void close(){
        try{
            results.close();
            dbConnect.close();
            System.out.println("Files were sucessfully closed");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}