package seoulbus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CsvFilesSaveToDB {
    private final static String filePath = "2021_seoul_bus_onoff.csv";
    public static void main(String[] args) {
        final String jdbcURL = "jdbc:mysql://loccalhost:3306/yscom";
        final String username = "root";
        final String password = "1234";

        final int batchSize = 2_000;    // bus insert

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        try {
//            connection.setAutoCommit(false);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        System.out.println("연결됨");
    }

}
