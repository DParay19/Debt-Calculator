import java.text.NumberFormat;
import java.util.*;
// class the calculates the card's info after a month
// class that determines the best path a user should take
public class Calculator {
    // method use to calculate the balance of each card/debt for the next month
    public static void nextMonth(LinkedList<Debt> a) {
        // finds balance of each card/loan for the next month
        for (Debt nextMonth : a) {
            double balance = nextMonth.getBalance(); //1000
            double interestRate = nextMonth.getInterestRate(); //.2
            double monthlyInterestRate = interestRate / 12; //.2 / 12 = .016
            double interestAccrued = balance * monthlyInterestRate; //1000*.016 = 16
            nextMonth.setBalance(nextMonth.getBalance() + interestAccrued);
            nextMonth.setMonthlyInterest(interestAccrued);
        }
        // displays the card(s)/loan(s) info after a month
        System.out.println("Card(s)/Loan(s) info after one month (Before payment):");
        for (Debt oneMonth : a) {
            System.out.println("Card/Loan name: " + oneMonth.getName());
            System.out.println("Card/Loan balance: " +
                    NumberFormat.getCurrencyInstance().format(oneMonth.getBalance()));
            System.out.println("Card/Loan interest rate: " + oneMonth.getInterestRate());
            System.out.println("Card/Loan minimum payment: " +
                    NumberFormat.getCurrencyInstance().format(oneMonth.getMinPayment()));
            System.out.println();
        }
    }
    public static Stack<Debt> debtStack(LinkedList<Debt> debtLL) {
        Stack<Debt> stack = new Stack<>();
        for (Debt debt : debtLL) {
            stack.push(debt);
        }
        return stack;
    }

    // method that determines what the user spend based on their input
    public static void getMonthlyCardPlan(LinkedList<Debt> b, double fundsAvailable) {
        //figure out which card would be most interest if not paid
        // that card as much as we can, leaving enough to pay minimums on the others
        //take each debt, calculate interest for 1 month. See which is biggest
//        LinkedList<Debt> beforeChange = b;


        // make array list to store the balances of the cards before
        double[] balanceBefore = new double[b.size()];
        int count1 = 0;
        for (Debt Card : b) {
            balanceBefore[count1] = Card.getBalance();
            count1++;
        }

        // variable to collect the combined min payments from all cards
        double minimumRequirements = 0;

        // hash map that stores the name of the card as the "key" to the monthly increase (values)
        HashMap<Debt, Double> monthlyIncrease = new HashMap<>();

        // variable to calculate the total balance of the cards
        double totalBalance = 0;

        // use an array so that we can compare the interestValues later on
        double[] interestValues = new double[b.size()]; //Making use of Array

        // loop that gets total balance, maps the respect values to their keys, and gets interestValues later on
        int count = 0;
        for (Debt card : b) {
            minimumRequirements += card.getMinPayment();
            double increaseValue = card.getMonthlyInterest();
            monthlyIncrease.put(card, increaseValue); //[Card1, 500],[Card2,399],[Card3,200]
            totalBalance += card.getBalance();
            interestValues[count] = increaseValue;
            count++;
        }

        // if user has enough funds to pay off the minimum
        if (fundsAvailable > minimumRequirements) {
            // displays current trajectory
            System.out.println("You have enough funds to meet your minimums.");
            System.out.println("Here are your debt projections without paying this month:");
            for (Debt item : monthlyIncrease.keySet()) {
                System.out.println(item.getName() + " will have an increase of " + monthlyIncrease.get(item));
            }

            //Making use of bubble sort
            BubbleSort.bubbleSort(interestValues);
            System.out.println("Your sorted increases from lowest to highest will be: ");
            printArray(interestValues);
            System.out.println("\n\n");
            System.out.println("//////////////////////////////////////////////");

            // Making use of the binary tree.
            BT debtTree = new BT();
            for (Debt key : monthlyIncrease.keySet()) {
                debtTree.insert(monthlyIncrease.get(key), key);
            }

            for (Debt item : b) {
                fundsAvailable -= item.getMinPayment();
                double balanceAfterMinPayment = item.getBalance() - item.getMinPayment();
                if (balanceAfterMinPayment < 0) { // 20 - 50 = -30
                    fundsAvailable -= balanceAfterMinPayment; // 300 - (-30) = 330
                    item.setBalance(0);
                } else {
                    item.setBalance(balanceAfterMinPayment);
                }
            }
            // Display the remaining funds available after paying off the minimums
            System.out.println("Here is shown how the balances would look, " +
                    "if you just pay the minimum payments on each.");
            System.out.println("After paying off the minimums, you have "
                    + NumberFormat.getCurrencyInstance().format(fundsAvailable) + " available.");
            System.out.println("These are your current balances:");
            // Displays the balance of the cards after min payment
            for (Debt a : b) {
                System.out.println(a.getName() + " balance "
                        + NumberFormat.getCurrencyInstance().format(a.getBalance()));
            }

            System.out.println("This is NOT the method we would recommend.");
            System.out.println();
            System.out.println("//////////////////////////////////////////////");
            System.out.println();

            System.out.println("We would instead recommend this.");

            // algorithm that determines which cards to put the most money to, and to which put just the minimum.
            while (fundsAvailable > 0) {
                double highestCard = 0;
                Debt highestDebt1 = null;
                for (Debt payOff : b) {
                    double comparison = payOff.getMonthlyInterest();
                    if (comparison > highestCard) {
                        highestCard = comparison;
                        highestDebt1 = payOff;
                    }
                }
                double holder = highestDebt1.getBalance(); // 400 = 400
                highestDebt1.pay(fundsAvailable); // 400 - 150 = 250
                fundsAvailable -= holder; // 150 - 400 = -250

                if (highestDebt1.getBalance() == 0) {
                    highestDebt1.setMonthlyInterest(0);
                }
            }
        }

        // if user does not have enough funds to pay off the minimum
        else if (fundsAvailable < minimumRequirements) {
            System.out.println("You do not have enough funds to meet your minimums.");
            System.out.println("Please contact your credit card companies immediately to " +
                    "notify them of your financial situation.");
            System.exit(0);
        }
        // if user has enough money to pay off all card/loan balances
        else if (fundsAvailable > totalBalance) {
            System.out.println("You have enough funds available to pay off all cards");
            System.out.println("After paying off your cards, you will have " +
                    NumberFormat.getCurrencyInstance().format(fundsAvailable - totalBalance) + " remaining");
            System.exit(0);
        }

        int count2 = 0;
        double[] balanceAfter = new double[b.size()];
        for (Debt Card : b) {
            balanceAfter[count2] = Card.getBalance();
            count2++;
        }

        // use the alternativeDebts to compare the balances of the cards in linkedList 'b'
        System.out.println("After performing our calculations, this is what we have determined:");
        int count3 = 0;
        for (Debt comparison : b) {
            System.out.println("Spend " + NumberFormat.getCurrencyInstance().format(balanceBefore[count3]
                    - balanceAfter[count3]) + " on " + comparison.getName());
            count3++;
        }
    }
    // method the prints out the array
    public static void printArray(double[] a) {
        for (double num : a) {
            System.out.println(num);
        }
    }

    public static void calculateDifference(LinkedList<Debt> before, LinkedList<Debt> after) {
        double[] balanceBefore = new double[before.size()];
        int count1 = 0;
        for (Debt Card : before) {
            balanceBefore[count1] = Card.getBalance();
        }

        double[] balanceAfter = new double[after.size()];
        int count2 = 0;
        for (Debt Card : after) {
            balanceAfter[count2] = Card.getBalance();
        }
        System.out.println("After performing our calculations, this is what we have determined:");
        int count3 = 0;
        for (Debt comparison : after) {
            System.out.println(balanceBefore[count3]);
            System.out.println(balanceAfter[count3]);
            System.out.println("Spend " + (balanceBefore[count3] - balanceAfter[count3])
                    + " on " + comparison.getName());
            count3++;
        }
    }
}