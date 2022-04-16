package edu.ucalgary.ensf409;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.FlowLayout;
import javax.swing.*;

public class GUIRequest extends JFrame implements ActionListener, MouseListener{
    // Member Variables
    private int[] adultMales;
    private int[] adultFemales;
    private int[] childUnder8;
    private int[] childOver8;
    private int numWeeks;
    private int j;

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

    private JFrame frame;
    private JButton submitInfo;
    private JButton nextInfo;
    // Constructor
    public GUIRequest(){
        super("Create a Request");
        setupGUI();
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j = 0;
    }

    // Setters

    // Getters

    // Methods
    public void setupGUI(){
        initializeVars();

        amInput.addMouseListener(this);
        afInput.addMouseListener(this);
        cu8Input.addMouseListener(this);
        co8Input.addMouseListener(this);
        weeksInput.addMouseListener(this);

        submitInfo = new JButton("Submit");
        nextInfo = new JButton("Next");
        submitInfo.addActionListener(this);
        nextInfo.addActionListener(this);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new FlowLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

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

        buttonPanel.add(submitInfo);
        buttonPanel.add(nextInfo);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(clientPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent event){
        JButton actionSource = (JButton) event.getSource();
        int aM = Integer.parseInt(amInput.getText());
        int aF = Integer.parseInt(afInput.getText());
        int cU = Integer.parseInt(cu8Input.getText());
        int cO = Integer.parseInt(co8Input.getText());
        numWeeks = Integer.parseInt(weeksInput.getText());
        if(actionSource.equals(submitInfo)){
            if(validateInput(aM, aF, cO, cU)){
                for(int i = 0; i < numWeeks; i ++){
                    adultMales[j] = aM;
                    adultFemales[j] = aF;
                    childUnder8[j] = cU;
                    childOver8[j] = cO;
                    j ++;
                }
            }
            Request request = new Request(
                adultMales, 
                adultFemales, 
                childOver8, 
                childUnder8, 
                j
            );
            int worked = request.fillHampers();
            if(worked != 0){
                JOptionPane.showMessageDialog(this, 
                "Inventory is too low to complete this order");
                j = 0;
            }

        }else if(actionSource.equals(nextInfo)){
            if(validateInput(aM, aF, cO, cU)){
                for(int i = 0; i < numWeeks; i ++){
                    adultMales[j] = aM;
                    adultFemales[j] = aF;
                    childUnder8[j] = cU;
                    childOver8[j] = cO;
                    j ++;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "worked");
        
    }

    public boolean validateInput(
        int adultMale, 
        int adultFemale,
        int childOver8,
        int childUnder8
        ){
        boolean allInputValid = true;
        if(adultMale < 0 || adultMale > 10){
            allInputValid = false;
            JOptionPane.showMessageDialog(
                this, adultMale 
                + " is an invalid number.  Please enter a number between 0-10"
            );
        }
        if(adultFemale < 0 || adultFemale > 10){
            allInputValid = false;
            JOptionPane.showMessageDialog(
                this, adultFemales
                + " is an invalid number.  Please enter a number between 0-10"
            );
        }
        if(childOver8 < 0 || childOver8 > 10){
            allInputValid = false;
            JOptionPane.showMessageDialog(
                this, childOver8
                + " is an invalid number.  Please enter a number between 0-10"
            );
        }
        if(childUnder8 < 0 || childUnder8 > 10){
            allInputValid = false;
            JOptionPane.showMessageDialog(
                this, childUnder8
                + " is an invalid number.  Please enter a number between 0-10"
            );
        }
        if(numWeeks <= 0 || numWeeks + j > 10){
            allInputValid = false;
            JOptionPane.showMessageDialog(this, 
                "Too many hampers "+ 
                "please submit order form before adding more orders"
            );
            j = 0;
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

    public void mouseEntered(MouseEvent event){}

    public void mouseExited(MouseEvent event){}

    public void mousePressed(MouseEvent event){}

    public void mouseReleased(MouseEvent event){}

    public static void main(String[] args){
        InventoryData dataBase = new InventoryData();
        EventQueue.invokeLater(() -> {
            new GUIRequest().setVisible(true);
        });
    }

    public void initializeVars(){
        adultMales = new int[10];
        adultFemales = new int[10];
        childUnder8 = new int[10];
        childOver8 = new int[10];
        frame = new JFrame();
        instructions = new JLabel("Please enter information to get a hamper");
        amLabel = new JLabel("Adult Males:");
        afLabel = new JLabel("Adult Females:");
        cu8Label = new JLabel("Childern Under 8:");
        co8Label = new JLabel("Childern Over 8:");
        weeksLabel = new JLabel("Number of Weeks");

        amInput = new JTextField("1", 5);
        afInput = new JTextField("1", 5);
        cu8Input = new JTextField("2", 5);
        co8Input = new JTextField("1", 5);  
        weeksInput = new JTextField("1", 5);
    }

}
