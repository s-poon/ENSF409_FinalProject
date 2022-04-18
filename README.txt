This file contains the instructions on how to run the program.

1.  In command prompt, ensure that you are in the directory that you extracted
    the program to.

2.  To compile the code type:

    javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/*.java

3.  To run the program type:
    
    java -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/FoodBank

4.  The GUI will appear.  Enter in the number of people you would like and the 
    number of weeks they will be recieving hampers.  If that is the only order
    you will be making, hit submit.  If not, hit next and the textfields will
    clear themselves and you can add the next order.  Continue to do this until
    either you are finished or you have reached the maximum hampers (10) for an
    order. 

5.  After you hit submit the program will find the best hampers, remove
    the food from the database, and then create a .txt file with the order
    information.

6.  To close the program hit the exit in the top left corner, or ctrl + C, in 
    the command line.