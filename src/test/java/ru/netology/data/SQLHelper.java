package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {

    private SQLHelper() {
    }

    private static Connection connection;

    @SneakyThrows
    public static Connection getConnection() {
        connection = DriverManager.getConnection(System.getProperty("db.url"), "app", "pass");
        return connection;

    }

    @SneakyThrows
    public static void deleteTable() {
        var runner = new QueryRunner();
        getConnection();

        runner.update(connection, "DELETE FROM payment_entity");
        runner.update(connection, "DELETE FROM order_entity");
        runner.update(connection, "DELETE FROM credit_request_entity");
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        String statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var runner = new QueryRunner();
        getConnection();
        var cardStatus = runner.query(connection, statusSQL, new ScalarHandler<String>());
        return cardStatus;
    }


}