/** Objective: To help individuals pay off their credit card(s)/loan(s).
 * This is a Java project that calculates and displays the best possible path
 * a user should take based off various factors.
 * Such as balance, interest rate, minimum payment, and user's funds available.
 * This project incorporates nearly all data structures available on Java
 * Such as Arrays, LinkedList, Stack, Queue, HashMap, and Binary Tree.
 * As well as using bubbleSort as a basic sorting algorithm
 * Note: This project only calculates on a monthly basis
 * @author Dylan Paray (dparay@nyit.edu)
 * @author Diego Montas Frias (dmontasf@nyit.edu)
 */
// allow access to all classes and interfaces in the java.util package
import java.util.*;

// class that the user will interact with
public class Main {
    /* Make a LinkedList that holds all card/loans info
     Also make it so that it is accessible across multiple classes
     */
    public static LinkedList<Debt> debts = new LinkedList<>();
    // Make a Hash-map that has name of the cards/loans as the key to the interest rates (mapped values)

    // Main method that user interacts
    public static void main(String[] args) {
        Queue<String> startingInstructions = new LinkedList<>();
        startingInstructions.add("We will help manage your debts");
        startingInstructions.add("To do this we will need information about your debts.");
        startingInstructions.add("Please read the following prompts and correctly enter the information:");
        for (String instruction : startingInstructions) {
            System.out.println(instruction);
        }
        System.out.println();

        // Call Scanner object from Scanner Class to read user input and set it to keyboard
        Scanner keyboard = new Scanner(System.in);

        // Asks the user how many cards/loans they currently have
        System.out.println("How many credit card(s)/loan(s)");

        // set variable size that ask how many cards/loans the user wants to put in
        int size = -1;

        // create a-while loop that repeatedly asks the user to enter a valid number if they enter an invalid number.
        while (size <= 0) {
            try {
                size = keyboard.nextInt();
                if (size <= 0) {
                    // throws an exception when a user's enter a valid format, but invalid number choice for variable size
                    throw new InvalidException();
                }
                // use InputMismatchExcpetion to catch invalid input format for variable and allows the user to enter a valid one
                // prevents the program from simply crashing
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format for balance.");
                System.out.println("Please try again:");
                // use to prevent an infinite loop of the following two messages.
                keyboard.nextLine();
            } catch (InvalidException e) {
                // catch the exception and displays the message from the InvalidException class
                System.out.println(e.getMessage());
                System.out.println("Please try again!");
            }
        }
        keyboard.nextLine();
        // For loop that takes in user's card/loan info.
        for (int i = 0; i < size; i++) {
            // try-catch block use to give user's unlimited times to enter valid info
            // purpose is to anticipate user error
            try {
                System.out.println("Card/Loan number " + (i + 1) + " name:");
                String name = "";
                // while loop to check if variable name for card # is empty
                while (name.isEmpty()) {
                    try {
                        name = keyboard.nextLine();
                        if (name.isEmpty()) {
                            throw new IllegalArgumentException("Name cannot be empty.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Please try again!");
                    }
                }
                System.out.println("Card/Loan number " + (i + 1) + " balance:");
                double balance = -1.0;
                // while loop to check if variable balance is less than 0
                while (balance < 0) {
                    // try block use to give users the ability to enter as many times as necessary
                    try {
                        balance = keyboard.nextDouble();
                        if (balance < 0) {
                            throw new IllegalArgumentException("Balance must be greater than or equal to 0.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input format for balance.");
                        System.out.println("Please try again!");
                        keyboard.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Please try again");
                    }
                }
                System.out.println("Card/Loan number " + (i + 1) + " yearly interest rate: ");
                double interestRate = -1.0;
                while (interestRate < 0) {
                    try {
                        interestRate = (keyboard.nextDouble() / 100);
                        if (interestRate < 0) {
                            throw new IllegalArgumentException("Yearly interest rate must be greater than or equal to 0.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input format for yearly interest rate.");
                        System.out.println("Please try again!");
                        keyboard.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Please try again");
                    }
                }
                System.out.println("Card/Loan number " + (i + 1) + " minimum payment:");
                double minPayment = -1.0;
                while (minPayment < 0) {
                    try {
                        minPayment = keyboard.nextDouble();
                        keyboard.nextLine();
                        if (minPayment < 0) {
                            throw new IllegalArgumentException("Minimum payment must be greater than or equal to 0.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input format for minimum payment.");
                        System.out.println("Please try again!");
                        keyboard.nextLine();
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Please try again!");
                    }
                }
                // add user info to the linked list
                debts.add(new Debt(name, balance, interestRate, minPayment, interestRate));
            } catch (Exception e) {
                System.out.println("An error occurred while reading input.");
                System.out.println("Please try again.");
            }
        }

        // Ask how much money the user can pay off the loans
        System.out.println("How much money do you have available by a per month basis.");
        // set variable moneyAvaiable that ask how much money the user has available to pay off their loans
        double moneyAvailable = -1.0;
        // create a-while loop that repeatedly asks the user to enter a valid number if they enter an invalid number.
        while (moneyAvailable < 0) {
            try {
                moneyAvailable = keyboard.nextDouble();
                if (moneyAvailable < 0) {
                    throw new IllegalArgumentException("Money available must be greater than or equal to 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input format for balance.");
                System.out.println("Please try again!");
                keyboard.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again!");
            }
        }
        // use stack to determine highest yearly interest
        Stack<Debt> myDebtStack = Calculator.debtStack(debts);
        Debt highestDebt = null;
        double highestInterest = Integer.MIN_VALUE;
        while (!myDebtStack.isEmpty()) {
            double nextPeekedInterest = myDebtStack.peek().getInterestRate() * myDebtStack.peek().getBalance();
            if (nextPeekedInterest > highestInterest) {
                highestInterest = nextPeekedInterest;
                highestDebt = myDebtStack.pop();
            } else {
                myDebtStack.pop();
            }
        }
        System.out.println(highestDebt.getName() + " has the highest yearly incoming interest of " + highestInterest);
        System.out.println();

        // call method that determines the balance of the cards/loans for next month before plan
        // create a linkedlist that has the same data as the original linked list
        Calculator.nextMonth(debts);
        // call method that determines the best path the user should take
        Calculator.getMonthlyCardPlan(debts, moneyAvailable);

        System.out.println();
        System.out.println("There are the information on your cards after the payment plan:");
        for (Debt card : debts) {
            System.out.println("Card/Loan name: " + card.getName());
            System.out.println("Card/Loan balance: " + card.getBalance());
            System.out.println("Card/Loan interest rate: " + card.getInterestRate());
            System.out.println("Card/Loan minimum payment: " + card.getMinPayment());
            System.out.println();
        }
    }
}