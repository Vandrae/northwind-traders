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
                        3) Display all categories
                        0) Exit
                        Select an option:
                    """);
            String intSelection = input.nextLine();
            switch (intSelection){
                case "1":
                    DisplayAllProducts(connection);
                    break;
                case "2":
                    DisplayAllCustomers(connection);
                    break;
                case "3":
                   DisplayAllCategories(connection);
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
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

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

    public static void DisplayAllCustomers(Connection connection){
        try{
            String sql = """
                Select
                    ContactName,
                    CompanyName,
                    City,
                    Country,
                    Phone
                From
                    Customers
                order by
                    country
                """;
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String contact = rs.getString("ContactName");
                String name = rs.getString("CompanyName");
                String city = rs.getString("City");
                String country = rs.getString("Country");
                String phone = rs.getString("Phone");
                System.out.println("Customer Name: " + contact);
                System.out.println("Company Name: " + name);
                System.out.println("Customer City: " + city);
                System.out.println("Customer Country: " + country);
                System.out.println("Customer Phone Number: " + phone);
                System.out.println(" ");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void DisplayAllCategories(Connection connection){
        try{
            String sql = """
                Select
                    CategoryID,
                    CategoryName
                From
                    categories
                order by
                    CategoryID
                """;
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String id = rs.getString("CategoryID");
                String name = rs.getString("CategoryName");
                System.out.println("ID: " + id);
                System.out.println("Category Name: " + name);
                System.out.println(" ");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
