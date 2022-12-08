import java.util.Scanner;

public class Stock {
    String shareName;
    int numberOfShares;
    double sharePrice;
    double totalPrice;

    public Stock(String shareName, int numberOfShares, double sharePrice) {
        this.shareName = shareName;
        this.numberOfShares = numberOfShares;
        this.sharePrice = sharePrice;
        this.setTotalPrice();
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = numberOfShares * sharePrice;
    }

    public boolean buyShare(User user) {
        boolean successBuy = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("How many shares do you want to buy ? :");
        int noOfSharesToBuy = sc.nextInt();
        Double priceToPay = sharePrice * noOfSharesToBuy;
        if (user.withdraw(priceToPay)) {
            this.numberOfShares -= noOfSharesToBuy;
            user.stockList.add(new Stock(this.shareName, noOfSharesToBuy,this.sharePrice));
            setTotalPrice();
            successBuy = true;
        }
        return successBuy;
    }

    @Override
    public String toString() {
        return "Share name = " + shareName + "\tNumber of shares = " + numberOfShares + "\tShare price = " + sharePrice + "\tTotal price = " + totalPrice + "\n";
    }
}