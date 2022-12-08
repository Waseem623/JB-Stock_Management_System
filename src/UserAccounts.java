import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserAccounts {
    ArrayList<User> usersList;

    public UserAccounts() {
        usersList = new ArrayList<>();
    }

    public ArrayList<User> getUsersList() {
        JSONParser parser = new JSONParser();
        try {
            JSONArray userAccountList = (JSONArray) parser.parse(new FileReader("resources/UserAccounts.json"));
            for (Object userAccount : userAccountList) {
                JSONObject user = (JSONObject) userAccount;
                User userPojoObj = new User((String) user.get("userName"), (String) user.get("password"), Double.parseDouble((String) user.get("balance")));
                JSONArray stockList = (JSONArray) user.get("stock");
                for (Object stock : stockList) {
                    JSONObject stockDetails = (JSONObject) stock;
                    Stock stockPojoObj = new Stock((String) stockDetails.get("shareName"), Integer.parseInt((String) stockDetails.get("numberOfShares")), Double.parseDouble((String) stockDetails.get("sharePrice")));
                    stockPojoObj.setTotalPrice();
                    userPojoObj.stockList.add(stockPojoObj);
                }
                usersList.add(userPojoObj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void setUsersList(ArrayList<User> usersList) {
        this.usersList = usersList;
    }

    public User validateUser(String userName, String password) {
        getUsersList();
        for (User user : usersList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    void updateUserAccounts() throws IOException {
        JSONArray userList = new JSONArray();
        for (User user : this.usersList) {
            JSONObject userObj = new JSONObject();
            JSONArray stockList = new JSONArray();
            for (Stock stock : user.stockList) {
                JSONObject stockObj = new JSONObject();
                stockObj.put("sharePrice", String.valueOf(stock.getSharePrice()));
                stockObj.put("numberOfShares", String.valueOf(stock.getNumberOfShares()));
                stockObj.put("shareName", stock.getShareName());
                stockList.add(stockObj);
            }
            userObj.put("stock", stockList);
            userObj.put("balance", String.valueOf(user.getBalance()));
            userObj.put("password",user.getPassword());
            userObj.put("userName", String.valueOf(user.getUserName()));
            userList.add(userObj);
        }
        FileWriter fileWriter = new FileWriter("resources/UserAccounts.json");
        fileWriter.write(userList.toString());
        fileWriter.close();
    }
}