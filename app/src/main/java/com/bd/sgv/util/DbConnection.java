package com.bd.sgv.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DbConnection{
    private static DbConnection instance;
    private Connection connection;
    private String url;
    private String user;
    private String password;

    private DbConnection(){
        try{
            loadProperties(); Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("ligação com BD estabelecida!");
        } catch(ClassNotFoundException | SQLException e){
            System.err.println(" Erro na ligação: " + e.getMessage()); e.printStackTrace();
        }
    }

    private void loadProperties(){
        try(InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")){
            Properties prop = new Properties(); prop.load(input); this.url = prop.getProperty("db.url");
            this.user = prop.getProperty("db.user"); this.password = prop.getProperty("db.password");
        } catch(Exception e){           
            this.url = "jdbc:sqlserver://localhost:1433;databaseName=ProjetoFinalBD;encrypt=false"; this.user = "sa";
            this.password = "YourPassword123";
        }
    }

    public static DbConnection getInstance(){
        if(instance == null){
            synchronized(DbConnection.class){
                if(instance == null){
                    instance = new DbConnection();
                }
            }
        } return instance;
    }

    public Connection getConnection(){
        try{
            if(connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch(SQLException e){
            e.printStackTrace();
        } return connection;
    }

    public void closeConnection(){
        try{
            if(connection != null && !connection.isClosed()){
                connection.close(); System.out.println("ligação fechada.");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
