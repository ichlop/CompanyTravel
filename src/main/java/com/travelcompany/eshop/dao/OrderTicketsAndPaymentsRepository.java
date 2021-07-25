package com.travelcompany.eshop.dao;

import com.travelcompany.eshop.dao.implementations.DaoRepository;
import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;
import com.travelcompany.eshop.model.PayType;
import com.travelcompany.eshop.model.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class OrderTicketsAndPaymentsRepository implements DaoRepository<Ticket> {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    @Override
    public int addToDb(Connection conn) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Id: ");
        String id = scanner.nextLine();
        System.out.println("Passenger Id: ");
        String passengerId = scanner.nextLine();
        System.out.println("Itinerary Id: ");
        String itineraryId = scanner.nextLine();
        System.out.println("Payment Method: ");
        String paymentMethod = scanner.nextLine();
        System.out.println("Amount Paid: ");
        String amountPaid = scanner.nextLine();

        Statement statement = conn.createStatement();

//        UUID id = UUID.randomUUID();

        String query = "insert into Customer (  id ,passengerId,itineraryId,paymentMethod,amountPaid) "
                + " values ( " + id + passengerId + itineraryId + paymentMethod + amountPaid;

        statement.executeUpdate(query);
        logger.info("Successfully added to DB");
//        conn.close();
        return id.compareTo(id);
    }

    @Override
    public void getFromDb(int id, Connection conn) throws SQLException, ClassNotFoundException {

        String query = "select *  from orderedTicketAndPayments  where id =" + id;
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            System.out.println(rs.getInt("passengerId") + "," + rs.getInt("itineraryId") +
                    "," + rs.getString("paymentMethod") + "," + rs.getDouble("amountPaid"));
        }
//        conn.close();
    }

    @Override
    public void updateDb(int id, Connection conn) throws SQLException {

        // Open a connection
        System.out.println("Print new amountPaid: ");
        Scanner amountPaid = new Scanner(System.in);
        String query = "UPDATE orderedTicketsAndPayments SET amountPaid = '" + amountPaid + "' WHERE id= " + id;
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.executeUpdate(query);
        logger.info("Successfully updated");
//        conn.close();
    }

    @Override
    public boolean deleteFromDb(int id, Connection conn){

        String query = "delete from orderedTicketsAndPayments where id = " + id;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }
        try {
            stmt.execute(query);
        } catch (SQLException ex) {
            ExceptionHandler.handleException(ex,"");
        }
        logger.info("Successfully deleted");
//        conn.close();
        return true;
    }

    @Override
    public List<Ticket> getListFromDb(Connection conn, String query){
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

        List<Ticket> tickets = new ArrayList<>();
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }
            //retrieve data from row
            int id = 0;
            String paymentMethod = null;
            int itineraryId = 0;
            int passengerId = 0;
            double amountPaid = 0;
            try {
                id = rs.getInt("id");
                passengerId = rs.getInt("passengerId");
                itineraryId = rs.getInt("itineraryId");
                paymentMethod = rs.getString("paymentMethod");
                amountPaid = rs.getDouble("amountPaid");
            } catch (SQLException ex) {
                ExceptionHandler.handleException(ex,"");
            }

            //create ticket
            Ticket ticket = new Ticket();
            ticket.setId(id);
            ticket.setPassengerId(passengerId);
            ticket.setItineraryId(itineraryId);
            ticket.setPaymentMethod(PayType.valueOf(paymentMethod));
            ticket.setAmountPaid(amountPaid);


            //add to list
            tickets.add(ticket);
        }

        return tickets;
    }

}
