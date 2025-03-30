/**
 * Write a description of class BankingTask here.
 *
 * Gabriel Gibson
 * Version One
 * Date last changed: 24/03/25
 */

import java.util.Scanner;

public class BankingTask {
    private DoesTheStuff doesTheStuff = new DoesTheStuff();
    private Scanner kb = new Scanner(System.in);

    public void BankingTask() {
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

            default:
                System.out.println("Input not identified. Please try again");
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
