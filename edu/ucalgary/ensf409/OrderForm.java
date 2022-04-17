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

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * 
 * 
 * @author Group 24
 * @version 3.4
 * @since 1.0
 */

public class OrderForm{

    public void writeTofile(Hamper[] hampers){
        LocalDateTime now = LocalDateTime.now();

        try{
            String format = new String(
                String.valueOf(now.getYear())
                + "_"
                + String.valueOf(now.getMonthValue())
                + "_"
                + String.valueOf(now.getDayOfMonth())
                + "_"
                + String.valueOf(now.getHour())
                + "_"
                + String.valueOf(now.getMinute())
                + "_"
                + String.valueOf(now.getSecond())
            );
            FileWriter writer = new FileWriter(format + ".txt");
            writer.write("Group 24 Food Bank\n");
            writer.write("Order Form\n\n");
            writer.write("Name: \n");
            writer.write("Date: \n\n");
            writer.write("Original Request\n");
            for(int i = 0; i < hampers.length; i ++){
                writer.write("Hamper " + (i + 1) + ": ");
                if(Request.getNumAM(i) != 0){
                    writer.write(Request.getNumAM(i) + " Adult Male");
                }
                if(Request.getNumAF(i) != 0){
                    writer.write(", " + Request.getNumAF(i) + " Adult Female");
                }
                if(Request.getNumCO(i) != 0){
                    writer.write(", " + Request.getNumCO(i) + " Child Over 8");
                }
                if(Request.getNumCU(i) != 0){
                    writer.write(", " + Request.getNumCU(i) + " Child Under 8");
                }
                writer.write("\n");
            }
            writer.write("\n");
            for(int i = 0; i < hampers.length; i ++){
                writer.write("Hamper " + (i + 1) + " Items:\n");
                for(Food item : hampers[i].getItems()){
                    writer.write(item.getItemID() + "\t\t");
                    writer.write(item.getDescription() + "\n");
                }
                writer.write("\n");
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
