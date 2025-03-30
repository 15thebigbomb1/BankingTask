/**
 * Write a description of class DoesTheStuff here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class DoesTheStuff
{

    private String name;
    private int account;
    private String address;
    private String accountType;
    private int balance;
    // all the private info of the users
    private String[] values;
    
    int MAXLINES = 20;
    final int VALUESPERLINE= 5;
    private Scanner kb = new Scanner(System.in);
    ArrayList<BankingTask> info = new ArrayList<BankingTask>();
    int linecount = 0;
    File userInfoList = new File("TestAccounts.csv");
    private String[][] allLinesAllElements;
    // defining 2d arrays and loops for file checking, and also defing where the values will be
    // kept with the 2d array, "allLinesAllElements".
    public void DefineFiles(){
        String CSVlines[] = new String[MAXLINES];
        ArrayList<String[]> tempList = new ArrayList<>();
        //defines the temporary list that will be used to add my values to the 2d array
        try {
            Scanner reader = new Scanner(userInfoList); // open the file with the Scanner
            // Read in the file, stop at file end or if we read too many lines
            while (reader.hasNextLine()  && linecount < MAXLINES){
                String line=reader.nextLine();                  
                CSVlines[linecount]=line;
                //System.out.println(CSVlines[linecount]);
                linecount++;

            }
            // defines The File 
            for (int i = 0; i<linecount;i++){
                values = CSVlines[i].split(",");
                tempList.add(values);
            }
            //Separates the arry at every comma of the csv line
            //Adds to a tempoary array that then gets converted to a 2d array
            //This 2d arrays first value is the name and the second values are all the info
            allLinesAllElements = tempList.toArray(new String[tempList.size()][]);
            //defines the templists values and adds it to the 2d array, and also defining
            //the amount of names that will be in the first variable of 2d array
            
                        

        } catch (IOException e) {System.out.println(e);}
    }

    public void CreateAccount(){
        System.out.println("hello 1");
    }

    public void CloseAccount(){
        System.out.println("hello! 2");
    }

    public void CheckBalance(){
        boolean nameCheck = true;
        int b = 0;
        // int value for the while loop that chec
        System.out.println("enter in your name to check your balance");
        
        name = kb.nextLine();
        // the name the user will input to find there account
        
        while (nameCheck == true){
            if (name.equals(allLinesAllElements[b][0])){
                System.out.println(allLinesAllElements[b][4]);
                // prints out the balance of the name chosen as the balances will always
                // be the fifth value in the array
                nameCheck = false;
                // turns off the loop
            } else{
                b++;
                if (b == allLinesAllElements.length){
                    nameCheck = false;
                    System.out.println("Try later");
                    //turns off the loop if the name is not found
                }
            }
            
        }
    }

    public void DepositOrWithdrawl(){
        System.out.println("hello! 4");
    }
}