import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StockPortfolio {
    ArrayList<Stock> stockList;
    double totalStockPrice;

    public StockPortfolio() {
        stockList = new ArrayList<>();
    }

    public StockPortfolio(ArrayList<Stock> stockList) {
        this.stockList = stockList;
    }

    public ArrayList<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(ArrayList<Stock> stockList) {
        this.stockList = stockList;
    }

    public double getTotalStockPrice() {
        return totalStockPrice;
    }

    public void setTotalStockPrice() {
        double totalStockPrice = 0;
        for (Stock stock : stockList) {
            totalStockPrice += stock.getTotalPrice();
        }
        this.totalStockPrice = totalStockPrice;
    }

    void printStockReport() {
        generateTotalStockReport();
        int i = 1;
        for (Stock stock : stockList) {
            System.out.println(i + " \t" + stock);
            i++;
        }
        System.out.println("Total stock price = " + totalStockPrice);
    }

    void updateStockPortfolio() throws IOException {
        JSONArray stockList = new JSONArray();
        for (Stock stock : this.stockList) {
            JSONObject stockObj = new JSONObject();
            stockObj.put("sharePrice", String.valueOf(stock.getSharePrice()));
            stockObj.put("numberOfShares", String.valueOf(stock.getNumberOfShares()));
            stockObj.put("shareName", stock.getShareName());
            stockList.add(stockObj);
        }
        FileWriter fileWriter = new FileWriter("resources/StockPortfolio.json");
        fileWriter.write(stockList.toString());
        fileWriter.close();
    }

    void generateTotalStockReport() {
        int noOfStocks = 0;
        stockList.clear();
        JSONParser parser = new JSONParser();
        try {
            JSONArray stockList = (JSONArray) parser.parse(new FileReader("resources/StockPortfolio.json"));
            noOfStocks = stockList.size();
            for (Object stock : stockList) {
                JSONObject stockDetails = (JSONObject) stock;
                Stock stockPojoObj = new Stock((String) stockDetails.get("shareName"), Integer.parseInt((String) stockDetails.get("numberOfShares")), Double.parseDouble((String) stockDetails.get("sharePrice")));
                stockPojoObj.setTotalPrice();
                this.stockList.add(stockPojoObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTotalStockPrice();
        System.out.println("Total number of stocks : " + noOfStocks);
    }

}