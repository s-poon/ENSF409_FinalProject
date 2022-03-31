package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.FlowLayout;
import javax.swing.*;

public class GUIRequest extends JFrame implements ActionListener, MouseListener{
    // Member Variables
    private int adultMales;
    private int adultFemales;
    private int childUnder8;
    private int childOver8;
    private int numWeeks;

    private JLabel instructions;
    private JLabel amLabel;
    private JLabel afLabel;
    private JLabel cu8Label;
    private JLabel co8Label;
    private JLabel weeksLabel;

    private JTextField amInput;
    private JTextField afInput;
    private JTextField cu8Input;
    private JTextField co8Input;
    private JTextField weeksInput;

    // Constructor
    public GUIRequest(){
        super("Create a Request");
        setupGUI();
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Setters

    // Getters

    // Methods
    public void setupGUI(){
        instructions = new JLabel("Please enter information to get a hamper");
        amLabel = new JLabel("Adult Males:");
        afLabel = new JLabel("Adult Females:");
        cu8Label = new JLabel("Childern Under 8:");
        co8Label = new JLabel("Childern Over 8:");
        weeksLabel = new JLabel("Number of Weeks");

        amInput = new JTextField("0", 5);
        afInput = new JTextField("0", 5);
        cu8Input = new JTextField("0", 5);
        co8Input = new JTextField("0", 5);  
        weeksInput = new JTextField("0", 5);

        amInput.addMouseListener(this);
        afInput.addMouseListener(this);
        cu8Input.addMouseListener(this);
        co8Input.addMouseListener(this);
        weeksInput.addMouseListener(this);

        JButton submitInfo = new JButton("Submit");
        submitInfo.addActionListener(this);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout());

        headerPanel.add(instructions);
        clientPanel.add(amLabel);
        clientPanel.add(amInput);
        clientPanel.add(afLabel);
        clientPanel.add(afInput);
        clientPanel.add(cu8Label);
        clientPanel.add(cu8Input);
        clientPanel.add(co8Label);
        clientPanel.add(co8Input);
        clientPanel.add(weeksLabel);
        clientPanel.add(weeksInput);
        submitPanel.add(submitInfo);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.PAGE_END);
    }

    public void actionPerformed(ActionEvent event){
        adultMales = Integer.parseInt(amInput.getText());
        adultFemales = Integer.parseInt(afInput.getText());
        childUnder8 = Integer.parseInt(cu8Input.getText());
        childOver8 = Integer.parseInt(co8Input.getText());
        numWeeks = Integer.parseInt(weeksInput.getText());
        
        if(validateInput()){
            // String petID = idProcessing();
            Request creatRequest = new Request(
                adultMales, 
                adultFemales, 
                childOver8, 
                childUnder8, 
                numWeeks
            );
            JOptionPane.showMessageDialog(this, "worked");
        }
    }

    public boolean validateInput(){
        boolean allInputValid = true;
        if(adultMales < 0 || adultMales > 5){
            allInputValid = false;
            JOptionPane.showMessageDialog(this, adultMales + " is an invalid");
        }
        if(adultFemales < 0 || adultFemales > 5){
            allInputValid = false;
            JOptionPane.showMessageDialog(this, adultFemales + " is an invalid");
        }
        if(childOver8 < 0 || childOver8 > 5){
            allInputValid = false;
            JOptionPane.showMessageDialog(this, childOver8 + " is an invalid");
        }
        if(childUnder8 < 0 || childUnder8 > 5){
            allInputValid = false;
            JOptionPane.showMessageDialog(this, childUnder8 + " is an invalid");
        }
        return allInputValid;
        
    }

    public void mouseClicked(MouseEvent event){
        
        if(event.getSource().equals(amInput))
            amInput.setText("");

        if(event.getSource().equals(afInput))
            afInput.setText("");

        if(event.getSource().equals(cu8Input))
            cu8Input.setText("");

        if(event.getSource().equals(co8Input))
            co8Input.setText("");         
    }

    public void mouseEntered(MouseEvent event){
        
    }

    public void mouseExited(MouseEvent event){
        
    }

    public void mousePressed(MouseEvent event){
        
    }

    public void mouseReleased(MouseEvent event){
        
    }

    public static void main(String[] args){
        InventoryData dataBase = new InventoryData();
        EventQueue.invokeLater(() -> {
            new GUIRequest().setVisible(true);
        });
    }

}
