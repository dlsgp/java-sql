package seoulbus;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CsvFilesSaveToDB {
    private final static String filePath = "C:/java-sql/2021_seoul_bus_onoff.csv";
    public static <CSVRecord> void main(String[] args) {
        final String jdbcURL = "jdbc:mysql://localhost:3306/yscom";
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



        StringBuffer sb = new StringBuffer("null");

        for(int i = 1; i<=55; i++)
            sb.append(",? ");
        sb.setLength((sb.length()-1));

        String sql = "insert into tbl_seoul_bus_onoff values (" + sb.toString() + ");";
        System.out.println(sql);

        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(sql);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        int columSize = 55;

        List<CSVRecord> records = null;
        try{
            records = getCsvRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int row = 0; row<records.size(); row++){
            CSVRecord data = records.get(row);
            for(int fieldIndex = 0; fieldIndex < columSize; fieldIndex ++ ){
                try{
                    statement.setString(fieldIndex + 1 );
                }
            }
        }


    }

    public static List <CSVRecord> getCsvRecords() throws I0Exception {
        File targetFile = new File(filePath);
        int sampleDateRow = 0;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(targetFile)){
            List <CSVRecord> records;
            try(CSVParser parser = CSVFormat.EXCEL.withFirstRecordAsHeader().withQuote('"').parchase(bufferedReader)){
            records = parser.getRecords();
            }
        }
        return null;
    }
}
