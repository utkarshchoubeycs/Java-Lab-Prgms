// Main
package ExceptionHandling;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String cont = "yes";
        Account acc = new Account();
        while(cont.equals("yes")||cont.equals("y")){
            System.out.println("Menu::\n1.Deposit\n2.Withdraw\n3.Current Balance");
            int ch = in.nextInt();
            switch(ch){
                case 1: double amt = in.nextDouble();
                        String curr = in.nextLine();
                        acc.deposit(amt, curr);
                        break;
                case 2: amt = in.nextDouble();
                        acc.withdraw(amt);
                        break;
                case 3: acc.currBalance();
                        break;
                default:System.out.println("\nInvalid choice");
            }
            cont = in.nextLine();
            cont.toLowerCase();
        }
    }
}

//MyException class
package ExceptionHandling;

public class DemonitizationException extends Exception{
    double amount;
    DemonitizationException(double amount){
        this.amount = amount;
    }
    DemonitizationException(String s){
        super(s);
    }
    public String toString(){
        return "Deposit of old currency of Rs."+(int)amount+" crosses Rs.5000 limit";
    }
}

// Account Class
package ExceptionHandling;

public class Account {
    double balance;
    void deposit(double amt, String curr) {
        curr = curr.toUpperCase();
        try {
            if (curr.equals("OLD") && amt > 5000){
                throw new DemonitizationException(amt);
            }
            else{
                System.out.println("Depositing Rs."+amt+" in the Account.");
                balance += amt;
            }
        }
        catch (DemonitizationException ex){
            System.out.println(""+ex);
        }
    }
    void withdraw(double amt){
        try{
            if(balance-amt < 500){
                throw new DemonitizationException("Account has minimum amount");
            }
            else{
                System.out.println("Withdrawing Rs."+amt+" from your account.");
                balance -= amt;
            }
        }
        catch (DemonitizationException ex){
            System.out.println(ex.getMessage());
        }
    }
    void currBalance(){
        System.out.println("The current balance is Rs."+balance+" only.");
    }
}
