package warehouser;

import java.sql.*;
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
             for (int i = 0; i < 10000; i ++) {
                 String uuid = UUID.randomUUID().toString();
                 Random random = new Random();
                 int rand = random.nextInt(1000) + 1;
                 preparedStatement.setString(1, uuid);
                 preparedStatement.setString(2, "Торвар" + i);
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
        String sql = String.format("SELECT title FROM main where nickname = '%s'", itemName);

        try {
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                String str = rs.getString(1);
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Такого товара нет";
    }

}
