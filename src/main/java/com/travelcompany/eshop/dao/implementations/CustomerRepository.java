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
    public int addToDb(Connection conn) throws SQLException {

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

        String query = "insert into Customer ( name , email, addressCity, nationality, category ) "
                + " values ( '" + name + "','" + email + "','" + addressCity + "','" + nationality + "','" + category + "')";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(query);


        statement.executeUpdate(query);

        logger.info("Successfully added to DB");

        return id.compareTo(id);
    }

    @Override
    public void getFromDb(int id, Connection conn) throws SQLException {

        String query = "select *  from customer  where id =" + id;
        Statement statement = null;

        statement = conn.createStatement();

        ResultSet rs = null;

        rs = statement.executeQuery(query);


        while (true) {

            if (!rs.next()) break;


            System.out.println(rs.getInt("Id") + rs.getString("Name") + "," + rs.getString("Email") +
                    "," + rs.getString("AddressCity") + "," + rs.getString("Nationality") +
                    "," + rs.getString("Category"));

        }

        rs.close();

    }

    @Override
    public void updateDb(int id, Connection conn) throws SQLException {

        // Open a connection
        System.out.println("Print new email: ");
        Scanner scan = new Scanner(System.in);
        String email = scan.next();
        String query = "UPDATE Customer SET Email = '" + email + "' WHERE id= " + id;
        Statement stmt = null;

        stmt = conn.createStatement();


        stmt.executeUpdate(query);

        logger.info("Successfully updated");
    }

    @Override
    public boolean deleteFromDb(int id, Connection conn) throws SQLException {

        String query = "delete from Customer where id = " + id;
        Statement stmt = null;

        stmt = conn.createStatement();


        stmt.execute(query);

        logger.info("Successfully deleted");

        conn.close();

        return true;
    }

    @Override
    public List<Customer> getListFromDb(Connection conn, String query) throws SQLException {
        Statement statement = null;

        statement = conn.createStatement();

        ResultSet rs = null;

        rs = statement.executeQuery(query);


        List<Customer> customers = new ArrayList<>();
        while (true) {

            if (!rs.next()) break;

            //retrieve data from row
            int id = 0;
            String name = null;
            String email = null;
            String addressCity = null;
            String nationality = null;
            String category = null;


            id = rs.getInt("id");
            rs.getString("name");
            rs.getString("email");
            rs.getString("addressCity");
            rs.getString("nationality");
            rs.getString("category");


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