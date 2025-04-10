/**
 * Write a description of class DoesTheStuff here.
 *
 * @author (Gabriel Gibson)
 * @version (3/04/25)
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Random;

public class DoesTheStuff
{
    private String name;
    private String accountNumber;
    private String address;
    private String accountType;
    // all the private info of the users

    private String[] values;
    int maxLines = 20;
    //max amount of accounts for the file
    private Scanner kb = new Scanner(System.in);
    //scanner for keyboard
    int lineCount;
    // defining lineCount
    File userInfoList = new File("TestAccounts.csv");
    // defining the Account file as a variable
    File totalCashInfo = new File("TotalMoneyDetails.csv");
    private String[][] allLinesAllElements;

    private BankingTask bankingTask;
    // defining 2d arrays and loops for file checking, and also defing where the values will be
    // kept with the 2d array, "allLinesAllElements".

    String newValue;
    String replaceName;
    String replaceAccountNumber;
    //for replacing values in file or checking that what I am replacing is in the correct place

    float withdrawlTotalAmount = 0;
    float depositTotalAmount  = 0;
    float totalCash = 0;
    public DoesTheStuff(BankingTask bankingTask) { 
        this.bankingTask = bankingTask; // Assign BankingTask instance
    }

    public void DefineFiles(){
        String CSVlines[] = new String[maxLines];
        ArrayList<String[]> tempList = new ArrayList<>();
        //defines the temporary list that will be used to add my values to the 2d array
        lineCount = 0;
        try {
            Scanner reader = new Scanner(userInfoList); // open the file with the Scanner
            // Read in the file, stop at file end or if we read too many lines
            while (reader.hasNextLine()  && lineCount < maxLines){
                String line=reader.nextLine();                  
                CSVlines[lineCount]=line;
                //System.out.println(CSVlines[linecount]);
                lineCount++;

            }
            // defines The File 
            for (int i = 0; i<lineCount;i++){
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
        //Catches error
    }

    public void CreateAccount(){
        String[] typingChoices = {this.name,this.address};
        String[] numberChoices = { this.accountNumber,this.accountType};
        //holds the private Strings in an one for a loop for when the user is inputing with keyboard
        // and the other for when the user is chooisng from multiple options 

        String[] namesOfChoices = {"name","address","accountNumber","accountType",};
        // used for the for loop to show the user what they are writing for.

        String oldContent = "";
        //where all the old content goes.
        System.out.println("Create a new account,with new details, or the same deatils but a diffrent account type");

        for (int i = 0; i<2;i++){
            System.out.println("what is your "+namesOfChoices[i]);
            String ChoiceOne = kb.nextLine();
            //defines the name
            if (ChoiceOne.equals("")){
                System.out.println("Try Again!");
                CreateAccount();
            }
            
            System.out.println("are you sure?");
            System.out.println("type yes to confirm, no to retry, and menu to go back to the menu");
            String yesOrNoChoice = kb.nextLine().toUpperCase();
            //makes  sure user is alright with name choice
            
            if (yesOrNoChoice.equals("YES") || yesOrNoChoice.equals("Y") || yesOrNoChoice.equals("YEP")){
                typingChoices[i] = ChoiceOne.replace(","," ");
                // replaces any commas with spaces

                typingChoices[i] = typingChoices[i].replaceAll("\\s+", " "); 
                // replace any double spaces with a normal space

                typingChoices[i] = typingChoices[i].trim(); 
                //removes any spaces at the front of the name
                System.out.println(typingChoices[i]);
                // shows user improved name
            } else if (yesOrNoChoice.equals("MENU") || (yesOrNoChoice.equals("M"))){
                   System.out.println('\u000C');
                   
                   bankingTask.Menu();
                   return;
                   
            }else {
                System.out.println("Input was no, or not identifed");
                System.out.println("Try again");
                i = i -1;
                //runs the loop again incase yes was not identifed when typed in or the user-
                // typed in no
            }
        }

        numberChoices[0] = "08-0101-0";
        //defines the first seven digits as most accounts made all have the same numbers at the start

        Random randNumber = new Random();
        for (int b = 0; b<6;b++){
            int number = randNumber.nextInt(10);
            Integer.toString(number);
            numberChoices[0] = numberChoices[0]+number;
            //System.out.println(numberChoices[0]);
        }
        // runs a for loop that generates 6 random numbers to add to the unique account number for the-
        // account

        numberChoices[0] = numberChoices[0] + "-00";
        // adds the last two digits that are added to all numbers made in create account

        System.out.println("Choose which account type you want by pressing the corrosponding");
        System.out.println("number on your keyboard");
        System.out.println("1: Everyday");
        System.out.println("2: Savings");
        System.out.println("3: Current");
        //shows the user the three account choices. 
        boolean choiceTwoTrue = true;
        while (choiceTwoTrue == true){
            String choiceTwo = kb.nextLine().toUpperCase();
            if (choiceTwo.equals("1")||choiceTwo.equals("ONE")){
                numberChoices[1] = "Everyday";
                choiceTwoTrue = false;
            } else if (choiceTwo.equals("2")||choiceTwo.equals("TWO")){
                numberChoices[1] = "Savings"; 
                choiceTwoTrue = false;
            } else if (choiceTwo.equals("3")||choiceTwo.equals("THREE")){
                numberChoices[1] = "current"; 
                choiceTwoTrue = false;
            } else {
                System.out.println("Input not reconised try again");
                //runs if input is not identifed in  if statemnt 
            }
        }
        //runs a while loop that will run again if the user has not chosen one of the three choices

        try {
            Scanner reader = new Scanner(userInfoList);
            //read the csv file
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                // System.out.println(line);
                oldContent = oldContent + line + System.lineSeparator();
            }
            //reads all the lines in the and add it to the oldContent variable

            FileWriter writerAccount = new FileWriter(userInfoList);
            //defines the writer for the file

            writerAccount.write(oldContent);
            //writes all old content first before writing the new content
            for (String i : typingChoices){
                writerAccount.write(i+",");
            }
            //runs a for each loop to write the first two users choice in the first array

            for (String i : numberChoices){
                writerAccount.write(i+",");
            }
            //runs a for each loop to write for the last two user choices in the second array

            String startBalance = "0";
            writerAccount.write(startBalance);
            // As all new accounts start with a balance of 0, this is just writing for the balance-
            // and making it 0 dollars

            // System.out.println("worked");
            writerAccount.flush();
            writerAccount.close();
            //closes writer
        } catch (IOException er){ 
            er.printStackTrace();
            //catches error
        }

        System.out.println("YOUR ACCOUNT NUMBER IS:");
        System.out.println(numberChoices[0]);
        System.out.println("PLEASE WRITE THIS DOWN AS YOU WILL NEED IT FOR ALL ACTIONS LIKE BALANCE, DEPOSIT, ETC.");
        System.out.println();
        //Printed text to try show the user the importance of their unique account number-
        //and to get them to write it down as they will need it to access all servises in the program.

        System.out.println("Do you want to try and make a new Acoount again?. TYPE yes to do so, otherwise type no to go back to the menu");
        String menuChoiceOne = kb.nextLine().toUpperCase();
        //users input that chooses the choice

        if (menuChoiceOne.equals("YES") || menuChoiceOne.equals("Y") || menuChoiceOne.equals("YEP")){
            CreateAccount();
            //runs the method again
        } else if (menuChoiceOne.equals("NO") || menuChoiceOne.equals("N") || menuChoiceOne.equals("NAH")){
            System.out.println('\u000C');
            bankingTask.Menu();
            // closes the program and returns to menu
        }  else{ 
            System.out.println('\u000C');
            System.out.println("Input not recognised, going back to the menu");
            bankingTask.Menu();
            //if the input is not identifed it will just revert to going back to the menu.
        }
        //if statement used in all methods to give the user to redo the action and go back to the menu.

    }

    public void CloseAccount(){
        String oldContent = "";
        System.out.println("whats the name of the account you want to remove");
        String chooseAccountRemoval = kb.nextLine();
        System.out.println(chooseAccountRemoval);
        //users input when deleting their account
        System.out.println("what is the account Number of the account you wish to remove");
        String accountNumberRemoval = kb.nextLine();
        System.out.println(accountNumberRemoval);
        //extra security step to making sure only the user of the account can do this.

        try {
            Scanner reader = new Scanner(userInfoList);
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                System.out.println(line);
                oldContent = oldContent + line + System.lineSeparator();
                //reads the input and puts it all in the variable oldContent.
            }

            String[] lines = oldContent.split(System.lineSeparator());
            //puts the old content into an array holding each line
            ArrayList<String> updatedLines = new ArrayList<>();
            // defines where all the lines not being removed will go
            for (int i = 0; i < lines.length; i++) {
                String[] elements = lines[i].split(",");
                //splits the line into all the elements of the line.

                if (!elements[0].equals(chooseAccountRemoval) && !elements[2].equals(accountNumberRemoval)) {
                    updatedLines.add(lines[i]);
                    //adds the wanted lines to the array

                } else  {
                    System.out.println("For security Reasons what is the address you have put for your account");
                    String password = kb.nextLine();
                    //extra security to make sure only the owner of the account can delete there own account.
                    if (password.equals(elements[1])){
                        System.out.println("account Removed!");
                        //The line was not added to the updatedLines array and will hence not be-
                        // written in the file.
                    } else {
                        System.out.println("try again");
                        CloseAccount();
                        //if the password is incorect they will have to reinput all the information-
                        //to try and delete the account.
                    }

                } 
                //if loop that checks if the account name and account number is correct before deleting the account.
            }  
            //for loop goes through each line
            String newContent = String.join(System.lineSeparator(), updatedLines);
            //adds all the content  that wasn't removed to a string that will be used to write back into the file.

            FileWriter writerAccount = new FileWriter(userInfoList);
            //writer for the file
            writerAccount.write(newContent);
            //writes all the content from the old csv file besides the account that has been removed.

            writerAccount.flush();
            writerAccount.close();
            //closes writer
        } catch (IOException er){ 

            er.printStackTrace();
            //catches error
        }

    }

    public void ChangeVariable(){
        String oldContent = "";
        BufferedReader reader = null;
        //reads the file
        FileWriter writer = null;
        //writer for the file
        try
        {
            reader = new BufferedReader(new FileReader(userInfoList));
            //defines the reader

            //Reading all the lines of input text file into oldContent

            String line = reader.readLine();
            //initilazies line

            while (line != null) 
            {

                oldContent = oldContent + line + System.lineSeparator();
                //adds content from the file into the variable oldContent.

                line = reader.readLine();
                //reads the line until theres is nothing left to read i.e null.

                // System.out.println(line);
            }

            // System.out.println(replaceValue);
            // System.out.println(newValue);
            String[] lines = oldContent.split(System.lineSeparator());
            // splits the oldcontent into lines which include name accountNumber etc
            for (int i = 0; i < lines.length; i++) {
                String[] elements = lines[i].split(",");
                //splits the line into all the elements of the line.

                if (elements[0].equals(replaceName) && elements[2].equals(replaceAccountNumber)){
                    elements[4] = newValue; 
                    //
                    //replaces the foruth value (balance) with the newValue assigned in
                    // The deposit/withdrawl class/
                    lines[i] = String.join(",", elements);
                    // joins the elements back into lines.
                }
                // goes through each element of the line.
            }  
            //for loop goes through each line

            String newContent = String.join(System.lineSeparator(), lines);
            // adds the lines into the newContent string 
            //Rewriting the input text file with newContent

            writer = new FileWriter(userInfoList);
            //defines the fileWriter
            writer.write(newContent);
            //writes new content back to the file
        }
        catch (IOException e){}

        try
        {
            //Closing the resources

            reader.close();

            writer.close();
            //closes the writer.
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void CheckBalance(){
        int b = 0;
        // int value for the while loop that checks for the correct name and accountnumber

        System.out.println("enter in your name to check your balance");

        String balanceName = kb.nextLine();
        System.out.println("And your account number");
        String balanceAccountNumber = kb.nextLine();
        boolean balanceNameCheck = true;
        // the name the user will input to find there account

        while (balanceNameCheck == true){
            if (balanceName.equals(allLinesAllElements[b][0])&& balanceAccountNumber.equals(allLinesAllElements[b][2])){
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
        String menuChoiceThree= kb.nextLine().toUpperCase();
        //users input that chooses the choice
        if (menuChoiceThree.equals("YES") || menuChoiceThree.equals("Y") || menuChoiceThree.equals("YEP")){
            CheckBalance();
            //runs the method again
        } else if (menuChoiceThree.equals("NO") || menuChoiceThree.equals("N") || menuChoiceThree.equals("NAH")){
            System.out.println('\u000C');
            bankingTask.Menu();
            // closes the program and returns to menu
        } else{ 
            System.out.println('\u000C');
            System.out.println("Input not recognised, going back to the menu");
            bankingTask.Menu();
        }
        //if statement used in all methods to give the user to redo the action and go back to the menu.

    }

    public void DepositOrWithdrawl(){
        int d = 0;
        // int value for the while loop that checks for the name 
        System.out.println("enter in your name of the account you want to use");
        String depositname = kb.nextLine();

        System.out.println("Enter in your account number");
        String depositAccountNumber = kb.nextLine();

        boolean depositNameCheck = true;
        //runs nameCheck loop
        boolean allowDepositWithdrawl = false;
        // runs the Deposit/Withdrawl loop
        float depositWithdrawlNumber = 0;
        //defines the float value that will be used to remove/add amounts of money
        String numberConverter;
        // the name the user will input to find there account
        while (depositNameCheck == true){
            if (depositname.equals(allLinesAllElements[d][0]) && depositAccountNumber.equals(allLinesAllElements[d][2])){
                numberConverter = allLinesAllElements[d][4];
                depositWithdrawlNumber = Float.valueOf(numberConverter);
                depositNameCheck = false;
                allowDepositWithdrawl = true;
                replaceName = allLinesAllElements[d][0];
                replaceAccountNumber = allLinesAllElements[d][2];
                // if the name is found a while loop is turned on that will allow the user to deposit/withdrawl
            } else{
                d++;
                if (d == allLinesAllElements.length){
                    System.out.println("Name not identifed");
                    depositNameCheck = false;
                    //turns off the loop if the name is not found
                }
            }
        }

        while (allowDepositWithdrawl == true){
            System.out.println("Type 1 to deposit and 2 to withdrawl");
            String depositChoice = kb.nextLine().toUpperCase();
            if (depositChoice.equals("1")|| depositChoice.equals("ONE")){
                System.out.println("what amount do you want to deposit");
    
                String stringTempDepositAmount = kb.nextLine();
                try {
                    Integer.parseInt(stringTempDepositAmount);
                    System.out.println("string is number");
                } catch (NumberFormatException e) {
                    System.out.println("Try again");
                    DepositOrWithdrawl();
                }
                kb.nextLine();
                Float depositAmount = Float.valueOf(stringTempDepositAmount);
                
                //Amount the users wants to add

                System.out.print("Your "+allLinesAllElements[d][3]+" account was at " +allLinesAllElements[d][4]);
                //before the added values

                depositWithdrawlNumber = depositWithdrawlNumber + depositAmount;
                newValue = String.valueOf(depositWithdrawlNumber);
                depositTotalAmount = depositTotalAmount + depositAmount;
                ChangeVariable();


                // adds the amount the user has inputed

                numberConverter = String.valueOf(depositWithdrawlNumber);
                //converts the float back to the String

                allLinesAllElements[d][4] = numberConverter;
                // adds the new number to the recorded values 

                System.out.print(" ,Now it is at "+allLinesAllElements[d][4]);

                allowDepositWithdrawl = false;
            } else if (depositChoice.equals("2") || depositChoice.equals("TWO")){
                System.out.println("what amount do you want to Withdrawl");

                String stringTempWithdrawlAmount = kb.nextLine();
                kb.nextLine(); 
                try {
                    Integer.parseInt(stringTempWithdrawlAmount);
                    System.out.println("string is number");
                } catch (NumberFormatException e) {
                    System.out.println("Try again");
                    DepositOrWithdrawl();
                }
                //checks uf tge strings are numbers or not if not it will-
                //reset the method in the catch statement.
                Float withdrawlAmount = Float.valueOf(stringTempWithdrawlAmount);
                //throws away character return
                //Amount the users wants to add

                if (withdrawlAmount >= 5000){
                    System.out.println('\u000C');
                    System.out.println("Withdrawl amount is too much, try again");
                    bankingTask.RunArt();
                    DepositOrWithdrawl();
                }
                //checks if the withdrawl amount is too much.

                String replaceName = allLinesAllElements[d][0];
                depositWithdrawlNumber = depositWithdrawlNumber - withdrawlAmount;
                // adds the amount the user has inputed

                if (allLinesAllElements[d][3].equals("Savings") || allLinesAllElements[d][3].equals("Everyday")){
                    if (depositWithdrawlNumber <= 0){
                        System.out.println("Your account is at or under zero, and you can no longer withdrawl");
                        System.out.println("till you deposit more money");
                        DepositOrWithdrawl();
                    }
                } 

                if (allLinesAllElements[d][3].equals("Current")){
                    if (depositWithdrawlNumber <= -1000){
                        System.out.println("Your account is at or under -1000 dollars, and you can no longer withdrawl");
                        System.out.println("till you deposit more money");
                        DepositOrWithdrawl();
                    }
                }
                newValue = String.valueOf(depositWithdrawlNumber);
                withdrawlTotalAmount = withdrawlTotalAmount + withdrawlAmount;

                ChangeVariable();

                numberConverter = String.valueOf(depositWithdrawlNumber);
                //converts the float back to the String

                System.out.print("Your "+allLinesAllElements[d][3]+" account was at " +allLinesAllElements[d][4]);
                //before the added values

                allLinesAllElements[d][4] = numberConverter;
                // adds the new number to the recorded values 

                System.out.print(" ,Now it is at "+allLinesAllElements[d][4]);

                allowDepositWithdrawl = false;

            }
        }
        System.out.println();

        System.out.println("Do you want to try and Depost/Withdrawl again?. TYPE yes to do so, otherwise type no to go back to the menu");
        String menuChoiceFour = kb.nextLine().toUpperCase();

        //users input that chooses the choice
        if (menuChoiceFour.equals("YES") || menuChoiceFour.equals("Y") || menuChoiceFour.equals("YEP")){
            DepositOrWithdrawl();
            //runs the method again
        } else if (menuChoiceFour.equals("NO") || menuChoiceFour.equals("N") || menuChoiceFour.equals("NAH")){
            System.out.println('\u000C');
            bankingTask.Menu();

            // closes the program and returns to menu
        }  else{ 
            System.out.println('\u000C');
            System.out.println("Input not recognised, going back to the menu");
            bankingTask.Menu();
            //if the input is not regonised it will go back to the menu
        }
        //if statement used in all methods to give the user to redo the action and go back to the menu.
    } 

    public void Exit(){
        String oldContent = "";
        System.out.println("Type in the date, e.g (15/06/25)");
        String date = kb.nextLine();
        //You put in the date so you know on what day how much was deposited/withdrawled
        
        System.out.println("The Total amount of Deposits For the day is "+depositTotalAmount+".");
        System.out.println();
        System.out.println("The Total Amount of withdrawls for the day is "+withdrawlTotalAmount+".");
        //tells the user the total Information
        for (int i=0; i<allLinesAllElements.length;i++){
                totalCash = totalCash + Float.parseFloat(allLinesAllElements[i][4]);
            }
        //goes through the balances in all accounts and adds them up into total cash
            
        System.out.println();
        System.out.println("the Total amount of cash in the bank "+totalCash+".");
        //shows the total cash to the user
        
        String[] totalStringChoice = {Float.toString(depositTotalAmount),Float.toString(withdrawlTotalAmount),Float.toString(totalCash)};
        //turns all the float values into strings
        
        try {
            Scanner reader = new Scanner(totalCashInfo);
            //file reader for a new csv file
            //read the csv file
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                // System.out.println(line);
                oldContent = oldContent + line + System.lineSeparator();
            }
            //reads all the lines in  and add it to the oldContent variable

            FileWriter writerAccount = new FileWriter(totalCashInfo);
            //writes to a new csv file
            writerAccount.write(oldContent);
            writerAccount.write("\n");
            writerAccount.write("The Total amount of Deposits For "+date+" is "+totalStringChoice[0]+"\n");
            writerAccount.write("The Total amount of withdrawls For "+date+" is "+totalStringChoice[1]+"\n");
            writerAccount.write("The Total amount of cash in the bank for "+date+" is "+totalStringChoice[2]+"\n");
            //writes for total depoist, total withdrawl, and total cash.

            // System.out.println("worked");
            writerAccount.flush();
            writerAccount.close();
            //closes writer
        } catch (IOException er){ 
            er.printStackTrace();
            //catches error
        }

    }
}