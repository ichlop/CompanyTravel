package Main.dao;

import Main.dao.implementations.DaoRepository;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;


public class CustomerRepository implements DaoRepository<String> {


    @Override
    public int addToDb(Connection conn) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Name: ");
        String cName = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("AddressCity: ");
        String addressCity = scanner.nextLine();
        System.out.println("Nationality: ");
        String nationality = scanner.nextLine();
        System.out.println("Category: ");
        String category = scanner.nextLine();

        Class.forName("com.mysql.jc.jdbc.Driver");
        Statement statement = conn.createStatement();

        UUID id = UUID.randomUUID();

        String query = "insert into Customer ( id int primary key auto_increment , CName , email, ,AddressCity, Nationality, Category ) "
                + " values ( " + id + cName + email + addressCity + nationality + category;

        statement.executeUpdate(query);

        return id.compareTo(id);
    }

    @Override
    public void getfromDb(int id, Connection conn) throws SQLException, ClassNotFoundException {

        String query = "select *  from Customer  ";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getString("CName") + "," + rs.getString("Email") +
                    "," + rs.getString("Address") + "," + rs.getString("Nationality") +
                    "," + rs.getString("Category"));
        }
    }

    @Override
    public void updateDb(int id, String newEmail, Connection conn) throws SQLException {

        // Open a connection
        Statement stmt = conn.createStatement();
        Scanner email = new Scanner(System.in);
        String sql = "UPDATE Customer SET Email = " + email.nextLine() + " WHERE id in (100, 101)";
        stmt.executeUpdate(sql);
        ResultSet rs = stmt.executeQuery(sql);
        rs.close();
    }

    @Override
    public boolean deleteFromDb(int id, Connection conn) throws SQLException {

        String query = "delete from Customer where id = ?";
        Statement stmt = conn.createStatement();

        stmt.execute(query);
        conn.close();
        return true;
    }

    @Override
    public List<String> getListfromDb(Connection conn, String query) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        List<String> customers = null;
        while (rs.next()) {
            customers = Arrays.asList("id");
        }

        return customers;
    }

}
