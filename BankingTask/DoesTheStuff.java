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

public class DoesTheStuff {
    private String name;
    private int account;
    private String address;
    private String accountType;
    private int balance;
    final int MAXLINES = 100;
    private Scanner kb = new Scanner(System.in);
    ArrayList<BankingTask> info = new ArrayList<BankingTask>();
    int linecount = 0;
    File userInfoList = new File("TestAccounts.csv");
    
    try {
        Scanner reader = new Scanner(thefile); // open the file with the Scanner

        // Read in the file, stop at file end or if we read too many lines
        while (reader.hasNextLine() && linecount < MAXLINES) {
            String line = reader.nextLine();
            CSVlines[linecount] = line;
            linecount++;
        }

    } catch (IOException e) {
        System.out.println(e);
    }

    public void CreateAccount() {
        System.out.println("hello 1");
    }

    public void CloseAccount() {
        System.out.println("hello! 2");
    }

    public void CheckBalance() {
        System.out.println("hello! 3");
    }

    public void DepositOrWithdrawl() {
        System.out.println("hello! 4");
    }
}
