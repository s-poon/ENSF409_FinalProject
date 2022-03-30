package edu.ucalgary.ensf409;

public class Request {
    // Member Variables
    private String[] clients;
    private Client[] clientList;
    private int numHampers;
    Hamper[] hampers;

    // Constructors
    Request(String[] clients, int numHampers){
        this.clients = clients;
        this.numHampers = numHampers;
        this.hampers = new Hamper[numHampers];
        createClientList();
    }
    // Setters

    // Getters

    // Methods
    public void createClientList(){
        for(int i = 0; i < clients.length; i ++){
            clientList[i] = InventoryData.getClient(clients[i]);
        }
    }

    public void fillHampers(){
        for(int i = 0; i < numHampers; i ++){
            hampers[i] = new Hamper(clientList);
        }
    }
}
