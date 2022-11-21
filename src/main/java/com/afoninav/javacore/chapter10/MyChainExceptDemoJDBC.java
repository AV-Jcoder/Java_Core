package com.afoninav.javacore.chapter10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Как в этом примере связать между собой
 * два исклюючения?
 *
 */
public class MyChainExceptDemoJDBC {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/alexdb";
        String login = "alex";
        String pass = "qwerty";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, pass);
            System.out.println("connect ok");
        } catch (SQLException e) {
            System.out.println("SQL error");
            throw e;
        } finally {
            try {
                connection.close();
            } catch (NullPointerException e) {
                System.out.println("Connection close error NPE");
//                e.initCause(???) // где взять ссылку которое возникло до?
                System.out.println(e.getCause()); // тут ничего нет.
                throw e;
            }
        }
    }
}
