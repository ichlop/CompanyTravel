package Main.dao;

import Main.dao.implementations.DaoRepository;
import Main.model.Customer;

import java.sql.*;
import java.util.List;
import java.util.Scanner;


public class CustomerRepository implements DaoRepository {

    private Connection conn;

    public void CustomerRepositoryImpl(Connection conn) {
        this.conn = conn;
    }


    public int createTableCustomers() throws SQLException {

        String query = "create table Customer ( id int primary key auto_increment , firstName varchar(100), LastName varchar(100), email varchar(100) ) ";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.executeUpdate();
        return 1;
    }

//    @Override
//    public int addtoDb(Customer customer) throws SQLException {
//
//        String query = "insert into Customer ( CName, Email, AddressCity, Nationality, Category ) values(?,?,?,?,?)";
//
//        PreparedStatement statement = conn.prepareStatement(query);
//        statement.setString(1, customer.getName());
//        statement.setString(2, customer.getEmail());
//        statement.setString(3, customer.getAddressCity());
//        statement.setString(4, customer.getNationality());
//        statement.setString(5, customer.getCategory());
//
//        statement.executeUpdate();
//
//        int last_inserted_id = -1;
//        ResultSet rs = statement.getGeneratedKeys();
//        if (rs.next()) {
//            last_inserted_id = rs.getInt(1);
//        }
//
//        return last_inserted_id;
//    }

    @Override
    public int addtoDb(Object o) throws SQLException {
        return 0;
    }

    @Override
    public Customer getfromDb(int id) throws SQLException {
        String query = "select CName, Email, AddressCity, Nationality, Category  from Customer  ";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet rs = statement.executeQuery();

        rs.next();
        Customer customer = new Customer();
        customer.setName(rs.getString(1));
        customer.setEmail(rs.getString(2));
        customer.setAddressCity(rs.getString(3));
        customer.setNationality(rs.getString(4));
//       customer.setCategory(rs.getString(5));
        return customer;

    }

    @Override
    public void updatetoDb(int id, String newEmail,Connection conn) throws SQLException {

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
            PreparedStatement preparedStmt = conn.prepareStatement(query);
//            preparedStmt.setInt(1, 3);

            preparedStmt.execute();
            conn.close();
            return true;

    }

    @Override
    public List<Customer> getfromDb() {
        return null;
    }

}
