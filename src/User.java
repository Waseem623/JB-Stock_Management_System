import java.util.ArrayList;

public class User {
    String userName;
    String password;
    Double balance;
    ArrayList<Stock> stockList;

    public User() {
        stockList = new ArrayList<>();
    }

    public User(String userName, String password, Double balance, ArrayList<Stock> stockList) {
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.stockList = stockList;
    }

    public User(String userName, String password, double balance) {
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        stockList = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ArrayList<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(ArrayList<Stock> stockList) {
        this.stockList = stockList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    boolean withdraw(Double debitAmount) {
        boolean successWithdraw = false;
        if (debitAmount > balance) {
            System.out.println("Debit amount exceeded balance amount");
        } else {
            balance = balance - debitAmount;
            successWithdraw = true;
        }
        return successWithdraw;
    }

    @Override
    public String toString() {
        return "\nUser Name = " + userName + "\nBalance amount = " + balance + "\nStock available = \n" + stockList;
    }


}