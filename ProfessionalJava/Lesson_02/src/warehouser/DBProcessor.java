package warehouser;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class DBProcessor {


    private static Connection connection;
    private static Statement stmt;

    public static void connection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTable () {
        String newTable = "CREATE TABLE IF NOT EXISTS Warehouse (\n" +
                    "    id varchar(255) NOT NULL,\n" +
                    "    prodid INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                    "    title varchar(255) NOT NULL,\n" +
                    "    cost INTEGER NOT NULL" +
                    ")";
        try {
            stmt.execute(newTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void fillTable () {

        PreparedStatement preparedStatement = null;
        try {
             preparedStatement = connection.prepareStatement(
                "INSERT INTO Warehouse (id, title, cost)" +
                     "VALUES (?, ?, ?);");
             for (int i = 0; i < 10; i ++) {
                 String uuid = UUID.randomUUID().toString();
                 Random random = new Random();
                 int rand = random.nextInt(1000) + 1;
                 preparedStatement.setString(1, uuid);
                 preparedStatement.setString(2, "Товар" + i);
                 preparedStatement.setInt(3, rand);
                 preparedStatement.addBatch();
             }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void emptyTable () {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM Warehouse ");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String findItem (String itemName) {
        String sql = String.format("SELECT cost FROM Warehouse where title = '%s'", itemName);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static boolean changePrice (String itemName, int newCost) {
        boolean res;
        String sql = String.format("UPDATE Warehouse SET cost = %s  WHERE title = '%s' ", newCost, itemName);
        int rs = 0;
        try {
            rs = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
        if (rs != 0 ) {
            res = true;
        } else {
            res  = false;
        }
        return res;
    }

    public static ArrayList<Item> findItemByPrice(int minPrice, int maxPrice) {
        ArrayList<Item> result = new ArrayList<>();


        String sql = String.format("SELECT title, cost FROM Warehouse where cost BETWEEN %s and %s", minPrice, maxPrice);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            int i = 0;

            while (rs.next()) {
                Item newItem = new Item();
                newItem.setCost(rs.getInt(2));
                newItem.setTitle(rs.getString(1));
                result.add(newItem);
            }
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
        return result;
    }
}
