package com.travelcompany.eshop;

import com.travelcompany.eshop.dao.CustomerRepository;
import com.travelcompany.eshop.dao.ItineraryRepository;
import com.travelcompany.eshop.dao.OrderTicketsAndPaymentsRepository;
import com.travelcompany.eshop.mainoperations.*;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.Ticket;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainTravelCompany {

    public static void main(String[] args) throws SQLException, IOException {

        DBConnector db = new DBConnector();
        Connection conn = db.setUpConnectionWithDB();

        CreateTable ct = new CreateTable();
        ct.createTable(conn);

//        PassDataFromCSV passData = new PassDataFromCSV();
//        passData.insertCustomerData(new File("src/main/resources/Customer.csv"), conn);
//        passData.insertItineraryData(new File("src/main/resources/Itineraries.csv"), conn);
//        passData.insertTicketData(new File("src/main/resources/orderedTicketsAndPayments.csv"), conn);

//        CustomerRepository cr = new CustomerRepository();
//        cr.addToDb(conn);
//        cr.getFromDb(6,conn);
//        cr.updateDb(4,conn);
//        cr.deleteFromDb(3, conn);

        OrderTicketsAndPaymentsRepository or = new OrderTicketsAndPaymentsRepository();
        String getTicketsQuery = "SELECT * FROM orderedTicketsAndPayments";
        List<Ticket> tickets = or.getListFromDb(conn, getTicketsQuery);

        //List of the total number and cost of tickets for all customers
        Map<Integer, Double> idAndAmountpaid = tickets.stream().collect(Collectors.groupingBy(ticket -> ticket.getPassengerId(), Collectors.summingDouble(ticket -> ticket.getAmountPaid())));

        idAndAmountpaid.forEach((k, v) -> System.out.println("Passenger Id: " + k + ", Amount Paid: " + v));
        System.out.println("-------------------------");
        Map<Integer, Long> idAndCountCustomers = tickets.stream().collect(Collectors.groupingBy(ticket -> ticket.getPassengerId(), Collectors.counting()));
        idAndCountCustomers.forEach((k, v) -> System.out.println("Passenger Id: " + k + ", Total Orders: " + v));
        System.out.println("-------------------------");


        //List of the total offered itineraries per destination and departure
        ItineraryRepository ir = new ItineraryRepository();
        String getItineraryQuery = "SELECT * FROM itinerary";
        List<Itinerary> itineraries = ir.getListFromDb(conn, getItineraryQuery);

        Map<Object, Long> offerPerDestination = itineraries.stream().collect(Collectors.groupingBy(destination -> destination.getDestinationAirportId(), Collectors.counting()));
        offerPerDestination.forEach((k, v) -> System.out.println("Destination Airport: " + k + ", Total arrivals: " + v));
        System.out.println("-------------------------");
        Map<Object, Long> offerPerDeparture = itineraries.stream().collect(Collectors.groupingBy(depart -> depart.getDepartureAirportId(), Collectors.counting()));
        offerPerDeparture.forEach((k, v) -> System.out.println("Departure Airport: " + k + ", Total arrivals: " + v));
        System.out.println("-------------------------");

//        BackUp
        BackupDB backup = new BackupDB();
        backup.streamBackup(offerPerDestination);
        backup.doTheBackup(conn);
    }
}
