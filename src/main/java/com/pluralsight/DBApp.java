package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBApp {
    public static void main(String[] args) {
        //set up data source
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("Yearup2026");

        //query database
        String sql = """
                Select
                    *
                From
                    products
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()){
            while (rs.next()) {
                int id = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                System.out.println("Product ID: " + id);
                System.out.println("Product Name: " + name);
                System.out.println(" ");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }




    }
}
