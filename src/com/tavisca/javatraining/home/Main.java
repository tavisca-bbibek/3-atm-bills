package com.tavisca.javatraining.home;

import com.tavisca.javatraining.home.exception.BillCountExceedsTwentyException;
import com.tavisca.javatraining.home.exception.InsufficientCashException;
import com.tavisca.javatraining.home.exception.InvalidAmountException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inp;
        AtmMachine machine = new AtmMachine();

        do {
            printMessage("Balance: " + machine.getTotalCash());
            System.out.print("Enter amount: ");
            inp = sc.next();
            int amount = Integer.parseInt(inp);
            try {
                machine.withdrawTwentyBills(amount);
                printMessage("Collect cash: " + amount);
            } catch (InvalidAmountException e) {
                printError(e.getMessage());
            } catch (BillCountExceedsTwentyException e) {
                System.out.print("Would you like to withdraw more than 20 bills(y/n): ");
                inp = sc.next();
                if (!inp.equals("y"))
                    continue;
                try {
                    machine.withdraw(amount);
                    printMessage("Withdrawn: " + amount);
                } catch (InvalidAmountException | InsufficientCashException ex) {
                    printError(ex.getMessage());
                }
            } catch (InsufficientCashException e) {
                printError(e.getMessage());
            }
        } while (machine.getTotalCash() != 0.0d);
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    private static void printError(String message) {
        System.err.println("Error: " + message);
    }
}
