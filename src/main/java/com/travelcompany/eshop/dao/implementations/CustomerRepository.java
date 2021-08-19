package com.travelcompany.eshop.dao.implementations;

import com.travelcompany.eshop.dao.DaoRepository;
import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;
import com.travelcompany.eshop.model.Category;
import com.travelcompany.eshop.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerRepository implements DaoRepository<Customer> {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    @Override
    public int addToDb(Connection conn) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Id: ");
        String id = scanner.nextLine();
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("AddressCity: ");
        String addressCity = scanner.nextLine();
        System.out.println("Nationality: ");
        String nationality = scanner.nextLine();
        System.out.println("Category: ");
        String category = scanner.nextLine();

        String query = "insert into Customer ( id , name , email, addressCity, nationality, category ) "
                + " values ( '" + id + "','" + name + "','" + email + "','" + addressCity + "','" + nationality + "','" + category + "')";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(query);
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex, "");
        }

        try {
            statement.executeUpdate(query);
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex, "");
        }
        logger.info("Successfully added to DB");

        return id.compareTo(id);
    }

    @Override
    public void getFromDb(int id, Connection conn){

        String query = "select *  from customer  where id =" + id;
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");

        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(query);
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }

        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
            try {
                System.out.println(rs.getInt("Id") + rs.getString("Name") + "," + rs.getString("Email") +
                        "," + rs.getString("AddressCity") + "," + rs.getString("Nationality") +
                        "," + rs.getString("Category"));
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
        }
        try {
            rs.close();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");

        }
    }

    @Override
    public void updateDb(int id, Connection conn) {

        // Open a connection
        System.out.println("Print new email: ");
        Scanner scan = new Scanner(System.in);
        String email = scan.next();
        String query = "UPDATE Customer SET Email = '" + email + "' WHERE id= " + id;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex, "");

        }
        try {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex, "");
        }
        logger.info("Successfully updated");
    }

    @Override
    public boolean deleteFromDb(int id, Connection conn) {

        String query = "delete from Customer where id = " + id;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex, "");
        }
        try {
            stmt.execute(query);
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex, "");
        }
        logger.info("Successfully deleted");
        try {
            conn.close();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex, "");
        }
        return true;
    }

    @Override
    public List<Customer> getListFromDb(Connection conn, String query){
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(query);
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }

        List<Customer> customers = new ArrayList<>();
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException ex) {
            }
            //retrieve data from row
            int id = 0;
            String name = null;
            String email = null;
            String addressCity = null;
            String nationality =null;
            String category = null;

            try {
                id = rs.getInt("id");
                rs.getString("name");
                rs.getString("email");
                rs.getString("addressCity");
                rs.getString("nationality");
                rs.getString("category");
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }

            //create customer
            Customer customer = new Customer();
            customer.setId(id);
            customer.setName(name);
            customer.setEmail(email);
            customer.setAddressCity(addressCity);
            customer.setNationality(nationality);
            customer.setCategory(Category.valueOf(category));
            //add to list
            customers.add(customer);
        }

        return customers;
    }
}