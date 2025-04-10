/**
 * Write a description of class BankingTask here.
 *
 * Gabriel Gibson
 * 
 * Date last changed: 2/04/25
 */

import java.util.Scanner;

public class BankingTask {
    private DoesTheStuff doesTheStuff;
    private Scanner kb = new Scanner(System.in);
    
    public BankingTask() {
        this.doesTheStuff = new DoesTheStuff(this); // Pass 'this' to DoesTheStuff
        Menu();
    }
    
    public void Menu() {
        RunArt();
        System.out.println("Hello, welcome to your banking terminal");
        System.out.println("What would you like to do?");
        System.out.println();
        System.out.println("TYPE IN A NUMBER FROM ONE TO FOUR TO DO ANYTHING LISTED BELOW");
        System.out.println("|||| 1: CREATE ACCOUNT ||||");
        System.out.println();
        System.out.println("|||| 2: CLOSE ACCOUNT ||||");
        System.out.println();
        System.out.println("|||| 3: CHECK BALANCE ||||");
        System.out.println();
        System.out.println("|||| 4: DEPOSIT/WITHDRAW ||||");
        System.out.println();
        System.out.println("|||| 5: EXIT ||||");

        String choose = kb.nextLine().toUpperCase();
         doesTheStuff.DefineFiles();
        switch (choose) {
            case "ONE":
            case "1":
                doesTheStuff.CreateAccount();
                break;

            case "TWO":
            case "2":
                doesTheStuff.CloseAccount();
                break;

            case "THREE":
            case "3":
                doesTheStuff.CheckBalance();
                break;

            case "FOUR":
            case "4":
                doesTheStuff.DepositOrWithdrawl();
                break;
            case "FIVE":
            case "5":
                System.out.println("are you sure?");
                System.out.println("Type yes or no");
                String exitChoice = kb.nextLine().toUpperCase();
                if (exitChoice.equals("YES") ||exitChoice.equals("Y") || exitChoice.equals("YEP")){
                     System.exit(0);
                } else if (exitChoice.equals("NO")||exitChoice.equals("N")||exitChoice.equals("NAH")){
                    System.out.println('\u000C');
                    Menu();
                } else{
                    System.out.println('\u000C');
                    System.out.println("Try again");
                    Menu();
                }
                

            default:
                System.out.println('\u000C');
                System.out.println("Input not identified. Please try again");
                Menu();
        }
       
    }

    public void RunArt() {
        System.out.println("/$$   /$$                                   /$$ /$$         /$$$$$$$                      /$$");
        System.out.println("| $$  /$$/                                  |__/|__/        | $$__  $$                    | $$");
        System.out.println("| $$ /$$/   /$$$$$$  /$$  /$$  /$$  /$$$$$$  /$$ /$$        | $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$");
        System.out.println("| $$$$$/   |____  $$| $$ | $$ | $$ |____  $$| $$| $$ /$$$$$$| $$$$$$$  |____  $$| $$__  $$| $$  /$$/");
        System.out.println("| $$  $$    /$$$$$$$| $$ | $$ | $$  /$$$$$$$| $$| $$|______/| $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/");
        System.out.println("| $$\\  $$  /$$__  $$| $$ | $$ | $$ /$$__  $$| $$| $$        | $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$");
        System.out.println("| $$ \\  $$|  $$$$$$$|  $$$$$/$$$$/|  $$$$$$$| $$| $$        | $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$");
        System.out.println("|__/  \\__/ \\_______/ \\_____/\\___/  \\_______/|__/|__/        |_______/  \\_______/|__/  |__/|__/  \\__/");
    }
}