// class that holds the Debt object
public class Debt
{
    // variables that composed the card/loan object
    private String name;
    private double balance;
    private double interestRate;
    private double minPayment;
    private double monthlyInterest;

    // non-parametrized constructor
    public Debt(){
        this.name = "N/A";
        this.balance = 0;
        this.interestRate = 0;
        this.minPayment = 0;
        this.monthlyInterest = 0;
    }

    // parameterized constructor
    public Debt(String name, double balance, double interestRate, double minPayment, double monthyInterst){
        this.name = name;
        this.balance = balance;
        this.interestRate = interestRate;
        this.minPayment = minPayment;
        this.monthlyInterest = (balance * (interestRate / 12.0));
    }

    // mutator methods
    public void setName(String name){this.name = name;}
    public void setBalance(double balance)
    {
        this.balance = balance;
    }
    public void setInterestRate(double interestRate){this.interestRate = interestRate;}
    public void setMinPayment(double minPayment){this.minPayment = minPayment;}
    public void setMonthlyInterest(double monthlyInterest){this.monthlyInterest = monthlyInterest;}

    // access methods
    public String getName() {
        return name;
    }
    public double getBalance(){
        return balance;
    }
    public double getInterestRate(){
        return interestRate;
    }
    public double getMinPayment(){
        return minPayment;
    }
    public double getMonthlyInterest() {return monthlyInterest;}

    // method use to remove card/loan if their balance is less or equal to 0
    public static void remove() {
        Debt.remove();
    }
    public String toString(){
        return "Name: "+name+"\n"+
                "Balance: "+balance+"\n"+
                "Interest Rate: "+interestRate+"\n"+
                "Min Payment: "+minPayment+"\n";
    }
    // method use to pay off the balance of the cards
    public void pay(double amount){
        if (balance >= amount){
            balance -= amount;
        }
        else{
            balance = 0;
        }
    }
}
