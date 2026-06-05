package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBApp {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        //set up data source
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("Yearup2026");


        try (Connection connection = dataSource.getConnection()) {
            System.out.println("""
                    What do you want to do?
                        1) Display all products
                        2) Display all customers
                        0) Exit
                        Select an option:
                    """);
            String intSelection = input.nextLine();
            switch (intSelection){
                case "1":
                    DisplayAllProducts(connection);
                    break;
                case "2":
                    break;
                case "0":
                    break;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }





    }

    public static void DisplayAllProducts(Connection connection){
        try {
            String sql = """
                    Select
                        ProductID,
                        ProductName,
                        UnitPrice,
                        UnitsInStock
                    from
                        products
                    """;
            PreparedStatement stmnt = connection.prepareStatement(sql);
            ResultSet rs = stmnt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                String price = rs.getString("UnitPrice");
                String stock = rs.getString("UnitsInStock");
                System.out.println("Product ID: " + id);
                System.out.println("Product Name: " + name);
                System.out.println("Product Price: " + price);
                System.out.println("# of Product in stock: " + stock);
                System.out.println(" ");
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
