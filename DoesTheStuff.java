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
        int MAXLINES = 20;
        
        private Scanner kb = new Scanner(System.in);
        int linecount = 0;
        File userInfoList = new File("TestAccounts.csv");
        private String[][] allLinesAllElements;
        
        private BankingTask bankingTask;
        // defining 2d arrays and loops for file checking, and also defing where the values will be
        // kept with the 2d array, "allLinesAllElements".
        
        String replaceValue;
        String newValue;
        String replaceName;
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
            //Catches error
        }
    
        public void CreateAccount(){
            String[] typingChoices = {this.name,this.address};
            String[] numberChoices = { this.accountNumber,this.accountType};
            //holds the private Strings in an array
            String[] namesOfChoices = {"name","address","accountNumber","accountType",};
            // used for the for loop to show the user what they are writing for
            String oldContent = "";
            //where all the old content goes.
            
            for (int i = 0; i<2;i++){
                System.out.println("what is your "+namesOfChoices[i]);
                String ChoiceOne = kb.nextLine();
                System.out.println("are you sure?");
                String yesOrNoChoice = kb.nextLine().toUpperCase();
                if (yesOrNoChoice.equals("YES") || yesOrNoChoice.equals("Y") || yesOrNoChoice.equals("YEP")){
                    typingChoices[i] = ChoiceOne;
                    System.out.println(typingChoices[i]);
                } else {
                    System.out.println("Input was no, or not identifed");
                    System.out.println("Try again");
                    i = i -1;
                }
            }
            
            numberChoices[0] = "08-0101-0";
            Random randNumber = new Random();
            for (int b = 0; b<6;b++){
                int number = randNumber.nextInt(10);
                 Integer.toString(number);
                 numberChoices[0] = numberChoices[0]+number;
                System.out.println(numberChoices[0]);
            }
            numberChoices[0] = numberChoices[0] + "-00";
            
            System.out.println("Choose which account type you want by pressing the corrosponding");
            System.out.println("number on your keyboard");
            System.out.println("1: Everyday");
            System.out.println("2: Savings");
            System.out.println("3: Current");
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
                
                }
            }
            try {
                Scanner reader = new Scanner(userInfoList);
                while (reader.hasNextLine()){
                    String line = reader.nextLine();
                    System.out.println(line);
                    oldContent = oldContent + line + System.lineSeparator();
                }
                FileWriter writerAccount = new FileWriter(userInfoList);
                writerAccount.write(oldContent);
                for (String i : typingChoices){
                  writerAccount.write(i+",");
                }
                for (String i : numberChoices){
                  writerAccount.write(i+",");
                }
                
                writerAccount.write("0");
                
                System.out.println("worked");
                writerAccount.flush();
                writerAccount.close();
            } catch (IOException er){ 
                er.printStackTrace();
                
            
            
            
            }
            
            System.out.println("Do you want to try and make a new Acoount again?. TYPE yes to do so, otherwise type no to go back to the menu");
            
            String menuChoiceOne = kb.nextLine().toUpperCase();
            System.out.println("working");
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
                ChangeVariable();
            }
        }
    
        public void CloseAccount(){
            System.out.println("hello! 2");
        }
        
        public void ChangeVariable(){
            String oldContent = "";
            BufferedReader reader = null;
            int lineCountTwo = 0;
            FileWriter writer = null;
             try
            {
                reader = new BufferedReader(new FileReader(userInfoList));
                 
                //Reading all the lines of input text file into oldContent
                 
                String line = reader.readLine();
                 
                while (line != null) 
                {
                    
                    oldContent = oldContent + line + System.lineSeparator();
                    
                    line = reader.readLine();
                    
                    // System.out.println(line);
                }
                 
                //Replacing oldString with newString in the oldContent
                // System.out.println(replaceValue);
                // System.out.println(newValue);
                String[] lines = oldContent.split(System.lineSeparator());
                // splits the oldcontent into lines which include name accountNumber etc
                  for (int i = 0; i < lines.length; i++) {
                    String[] elements = lines[i].split(",");
                    //splits the line into all the elements of the line.
                    
                    if (elements[0].equals(replaceName)) {
                        elements[4] = newValue;  //
                        System.out.println(elements[0]);
                        System.out.println(elements[4]);
                        //replaces the foruth value (balance) with the newValue assigned in
                        // The deposit/withdrawl class,
                        lines[i] = String.join(",", elements);
                        // joins the elements back into lines
                    }
                    // goes through each element
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
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
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
        }
    
        public void DepositOrWithdrawl(){
            int d = 0;
            // int value for the while loop that checks for the name 
            System.out.println("enter in your name of the account you want to use");
            
            String depositname = kb.nextLine();
            boolean depositNameCheck = true;
            //runs nameCheck loop
            boolean allowDepositWithdrawl = false;
            // runs the Deposit/Withdrawl loop
            float depositWithdrawlNumber = 0;
            //defines the float value that will be used to remove/add amounts of money
            String numberConverter;
            // the name the user will input to find there account
            while (depositNameCheck == true){
                if (depositname.equals(allLinesAllElements[d][0])){
                    numberConverter = allLinesAllElements[d][4];
                    depositWithdrawlNumber = Float.valueOf(numberConverter);
                    depositNameCheck = false;
                    allowDepositWithdrawl = true;
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
    
                    float depositAmount = kb.nextFloat();
                    kb.nextLine();
                    //Amount the users wants to add
    
                    System.out.print("Your "+allLinesAllElements[d][3]+" was at " +allLinesAllElements[d][4]);
                    //before the added values
                    replaceName = allLinesAllElements[d][0].replaceAll("\\.$", "").trim();
                    
                    
                    
                    
                    depositWithdrawlNumber = depositWithdrawlNumber + depositAmount;
                    newValue = String.valueOf(Math.round(depositWithdrawlNumber));
                    ChangeVariable();
                    
                    // adds the amount the user has inputed
    
                    numberConverter = Float.toString(depositWithdrawlNumber);
                    //converts the float back to the String
    
                    allLinesAllElements[d][4] = numberConverter;
                    // adds the new number to the recorded values 
    
                    System.out.print(". Now it is at "+allLinesAllElements[d][4]);
                    
                    allowDepositWithdrawl = false;
                } else if (depositChoice.equals("2") || depositChoice.equals("TWO")){
                    System.out.println("what amount do you want to Withdrawl");
    
                    float withdrawlAmount = kb.nextFloat();
                    kb.nextLine(); 
                    
                    //throws away character return
                    //Amount the users wants to add
                    
                    if (withdrawlAmount >= 5000){
                        System.out.println('\u000C');
                        System.out.println("Withdrawl amount is too much, try again");
                        bankingTask.RunArt();
                        DepositOrWithdrawl();
                    }
                    
                    String replaceName = allLinesAllElements[d][0].replaceAll("\\.$", "").trim();
                    depositWithdrawlNumber = depositWithdrawlNumber - withdrawlAmount;
                    // adds the amount the user has inputed
                    newValue = String.valueOf(Math.round(depositWithdrawlNumber));
                    ChangeVariable();
                    System.out.println(newValue);
                    numberConverter = Float.toString(depositWithdrawlNumber);
                    //converts the float back to the String
                    
                    System.out.print("Your "+allLinesAllElements[d][3]+" was at " +allLinesAllElements[d][4]);
                    //before the added values
                    
            
                    allLinesAllElements[d][4] = numberConverter;
                    // adds the new number to the recorded values 
    
                    System.out.print(". Now it is at "+allLinesAllElements[d][4]);
                    
                    allowDepositWithdrawl = false;
                    
                }
            }
            System.out.println();
               
           
            System.out.println("Do you want to try and Depost/Withdrawl again?. TYPE yes to do so, otherwise type no to go back to the menu");
            
            String menuChoiceFour = kb.nextLine().toUpperCase();
            System.out.println("working");
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
            }
        } 
    }