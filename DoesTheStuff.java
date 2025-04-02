/**
 * Write a description of class DoesTheStuff here.
 *
 * @author (Gabriel Gibson)
 * @version (30/03/25)
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
    private BankingTask bankingTask;
    // defining 2d arrays and loops for file checking, and also defing where the values will be
    // kept with the 2d array, "allLinesAllElements".
    public DoesTheStuff(BankingTask bankingTask) { 
        this.bankingTask = bankingTask; // Assign BankingTask instance
    }
    public void DefineFiles(){
        String CSVlines[] = new String[MAXLINES];
        ArrayList<String[]> tempList = new ArrayList<>();
        //defines the temporary list that will be used to add my values to the 2d array
        linecount = 0;
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
            //defines the templists values and adds it to the 2d array, with the first
            // variable being the amount of rows, and the second being the info name, adress etc.


        } catch (IOException e) {System.out.println(e);}
    }

    public void CreateAccount(){
        System.out.println("hello 1");
    }

    public void CloseAccount(){
        System.out.println("hello! 2");
    }

    public void CheckBalance(){
        int b = 0;
        // int value for the while loop that chec
        System.out.println("enter in your name to check your balance");

        String balanceName = kb.nextLine();
        boolean balanceNameCheck = true;
        // the name the user will input to find there account

        while (balanceNameCheck == true){
            if (balanceName.equals(allLinesAllElements[b][0])){
                System.out.println("your "+allLinesAllElements[b][3]+ " Acoount has a balance of "+allLinesAllElements[b][4]);
                // prints out the balance of the name chosen as the balances will always
                // be the fifth value in the array
                balanceNameCheck = false;
                // turns off the loop
            } else{
                b++;
                if (b == allLinesAllElements.length){
                    System.out.println("Name not identifed");
                    balanceNameCheck = false;
                    //turns off the loop if the name is not found
                }
            }
        }

        System.out.println("Do you want to check/recheck your balance, Type yes to do so, otherwise type no to go back to the menu");
        String balanceChoice= kb.nextLine().toUpperCase();
        //users input that chooses the choice
        if (balanceChoice.equals("YES") || balanceChoice.equals("Y") || balanceChoice.equals("YEP")){
            CheckBalance();
            //runs the method again
        } else if (balanceChoice.equals("NO") || balanceChoice.equals("N") || balanceChoice.equals("NAH")){
            System.out.println('\u000C');
            bankingTask.Menu();
            // closes the program and returns to menu
        }

    }

    public void DepositOrWithdrawl(){
        int d = 0;
        // int value for the while loop that checks for the name 
        System.out.println("enter in your name of the account you want to use");

        String depositname = kb.nextLine();
        boolean depositNameCheck = true;
        float depositWithdrawlNumber = 0;
        String numberConverter;
        // the name the user will input to find there account
        while (depositNameCheck == true){
            if (depositname.equals(allLinesAllElements[d][0])){
                numberConverter = allLinesAllElements[d][4];
                depositWithdrawlNumber = Float.valueOf(numberConverter);
                depositNameCheck = false;
            } else{
                d++;
                if (d == allLinesAllElements.length){
                    System.out.println("Name not identifed");
                    depositNameCheck = false;
                    
                    
                    //turns off the loop if the name is not found
                }
            }
        }
        System.out.println("Type 1 to deposit and 2 to withdrawl");
        String depositChoice = kb.nextLine().toUpperCase();
        if (depositChoice.equals("1")|| depositChoice.equals("ONE")){
            System.out.println("what amount do you want to deposit");
            float depositAmount = kb.nextFloat();
            //Amount the users wants to add
            System.out.print("Your account was at " +allLinesAllElements[d][4]);
            //before the added values
            depositWithdrawlNumber = depositWithdrawlNumber + depositAmount;
            // adds the amount the user has inputed
            numberConverter = Float.toString(depositWithdrawlNumber);
            //converts the float back to the String
            allLinesAllElements[d][4] = numberConverter;
            // adds the new number to the recorded values 
            System.out.print(". Now it is at "+allLinesAllElements[d][4]);
        } else if (depositChoice.equals("2") || depositChoice.equals("TWO")){
            System.out.println("what amount do you want to Withdrawl");
            float withdrawlAmount = kb.nextFloat();
            //Amount the users wants to add
            System.out.print("Your account was at " +allLinesAllElements[d][4]);
            //before the added values
            depositWithdrawlNumber = depositWithdrawlNumber - withdrawlAmount;
            // adds the amount the user has inputed
            numberConverter = Float.toString(depositWithdrawlNumber);
            //converts the float back to the String
            allLinesAllElements[d][4] = numberConverter;
            // adds the new number to the recorded values 
            System.out.print(". Now it is at "+allLinesAllElements[d][4]);
            
        }
    } 
}
