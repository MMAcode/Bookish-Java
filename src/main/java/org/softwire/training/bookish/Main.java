package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;

import io.github.cdimascio.dotenv.Dotenv;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.softwire.training.bookish.models.database.Book;

public class Main {

    public static void main(String[] args) throws SQLException {
        String hostname = "localhost";
        String database = "library";
        String user = "root";
//        String password = System.getenv("DB_PASS");
        String password = Dotenv.load().get("DB_PASS");
        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";
        connectionString += "&allowPublicKeyRetrieval=true";


        jdbcMethod(connectionString);
//        jdbiMethod(connectionString);
    }

    private static void jdbcMethod(String connectionString) throws SQLException {
        System.out.println("JDBC method...");

        // TODO: print out the details of all the books (using JDBC)
        // See this page for details: https://docs.oracle.com/javase/tutorial/jdbc/basics/processingsqlstatements.html

        Connection connection = DriverManager.getConnection(connectionString);

        Statement stmt = null;
//        String query = "select COF_NAME, SUP_ID, PRICE, " +
//                "SALES, TOTAL " +
//                "from " + dbName + ".COFFEES";
        String query = "SELECT * FROM books";

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String bookName = rs.getString("title");
//                int supplierID = rs.getInt("SUP_ID");
//                float price = rs.getFloat("PRICE");
//                int sales = rs.getInt("SALES");
//                int total = rs.getInt("TOTAL");
                System.out.println(bookName);
            }
        } catch (SQLException e) {
//            JDBCTutorialUtilities.printSQLException(e);
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    private static void jdbiMethod(String connectionString) {
        System.out.println("\nJDBI method...");

        // TODO: print out the details of all the books (using JDBI)
        // See this page for details: http://jdbi.org
        // Use the "Book" class that we've created for you (in the models.database folder)

        Jdbi jdbi = Jdbi.create(connectionString);

        List<Book> books = (List<Book>) jdbi.withHandle(handle ->
                handle
                        .registerRowMapper(ConstructorMapper.factory(Book.class))
                        .createQuery("SELECT * FROM books")
                        .mapTo(Book.class)
                        .collect(Collectors.toList())
        );

        books.forEach(System.out::println);


    }
}
