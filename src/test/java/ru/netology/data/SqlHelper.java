package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlHelper {

    private SqlHelper() {

    }

    @SneakyThrows(SQLException.class)
    public static Connection getConn(){
      final Connection connection = DriverManager.getConnection(
             "jdbc:mysql://localhost:3306/app", "app", "pass");
      return connection;
     }

    @SneakyThrows(SQLException.class)
    public static Connection getConnection() {
        String dbUrl = System.getProperty("db.url");
        String login = System.getProperty("login");
        String password = System.getProperty("password");
        final Connection connection = DriverManager.getConnection(dbUrl, login, password);
        return connection;
    }

    @SneakyThrows(SQLException.class)
    public static String getStatusDebitCard() {
        val statusSql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (val connection = getConn();
             val statusStmt = connection.createStatement();) {
            try (val rs = statusStmt.executeQuery(statusSql)) {
                if (rs.next()) {
                    val status = rs.getString(1);
                    return status;
                }
                return null;
            }
        }
    }

    @SneakyThrows(SQLException.class)
    public static String getStatusCreditCard() {
        val statusSql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (val connection = getConn();
             val statusStmt = connection.createStatement();) {
            try (val rs = statusStmt.executeQuery(statusSql)) {
                if (rs.next()) {
                    val status = rs.getString(1);
                    return status;
                }
                return null;
            }
        }
    }


    @SneakyThrows
    public static void cleanData() {
        val pays = "DELETE FROM payment_entity";
        val credits = "DELETE FROM credit_request_entity";
        val orders = "DELETE FROM order_entity";
        try (val conn = SqlHelper.getConn();
             val prepareStatPay = conn.createStatement();
             val prepareStatCredit = conn.createStatement();
             val prepareStatOrder = conn.createStatement();
        ) {
            prepareStatPay.executeUpdate(pays);
            prepareStatCredit.executeUpdate(credits);
            prepareStatOrder.executeUpdate(orders);
        }


    }

}
