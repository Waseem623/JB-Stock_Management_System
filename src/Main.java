import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final int ONE = 1;
    Scanner sc;
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.sc = new Scanner(System.in);
        UserAccounts userAccounts = new UserAccounts();
        System.out.println("Enter your choice : \n1 : Login \n2 : Register");
        int choice = main.sc.nextInt();
        if (choice == ONE) {
            System.out.println("Enter user name");
            String userName = main.sc.next();
            System.out.println("Enter password");
            String password = main.sc.next();
            User user = userAccounts.validateUser(userName, password);
            if (user != null) {
                System.out.println("Enter your choice : \n1 : Buy shares \n0 : Exit");
                int choiceInput = main.sc.nextInt();
                switch (choiceInput) {
                    case ONE:
                        main.buyShares(user);
                        userAccounts.updateUserAccounts();
                        System.out.println(userAccounts.usersList.get(userAccounts.usersList.indexOf(user)));
                        break;
                    default:
                        break;
                }
            }
            else System.out.println("Invalid credentials");
        }
    }

    private void buyShares(User user) throws IOException {
        StockPortfolio stockPortfolio = new StockPortfolio();
        stockPortfolio.printStockReport();
        System.out.println("Which you want to buy : ");
        int buyInput = sc.nextInt();
        Stock stock = stockPortfolio.stockList.get(buyInput-ONE);
        if(stock.buyShare(user)){
            stockPortfolio.updateStockPortfolio();
            stockPortfolio.printStockReport();
        }
    }
}