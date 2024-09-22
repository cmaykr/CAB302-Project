package org.example.productpage2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {
        private static Connection instance = null;
        private SqliteConnection(){
            String url = "jdbc:sqlite:product.db";
            try{
                instance = DriverManager.getConnection(url);
            }catch(SQLException sqlEx){
                System.err.println(sqlEx);
            }
        }
        public static Connection getInstance(){
            if(instance == null){
                new SqliteConnection();
            }return instance;
        }
}