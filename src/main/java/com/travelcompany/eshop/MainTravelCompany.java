package com.travelcompany.eshop;

import com.travelcompany.eshop.dao.implementations.CustomerRepository;
import com.travelcompany.eshop.dao.implementations.ItineraryRepository;
import com.travelcompany.eshop.dao.implementations.OrderTicketsAndPaymentsRepository;
import com.travelcompany.eshop.mainoperations.DBoperations.BackupDB;
import com.travelcompany.eshop.mainoperations.DBoperations.CreateTable;
import com.travelcompany.eshop.mainoperations.DBoperations.DBConnector;
import com.travelcompany.eshop.mainoperations.implementations.PassCustomerCSV;
import com.travelcompany.eshop.mainoperations.implementations.PassItineraryCsv;
import com.travelcompany.eshop.mainoperations.implementations.PassTicketsAndPaymentsCSV;
import com.travelcompany.eshop.model.Category;
import com.travelcompany.eshop.model.Itinerary;
import com.travelcompany.eshop.model.PayType;
import com.travelcompany.eshop.model.Ticket;
import com.travelcompany.eshop.services.CustomerService;

import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainTravelCompany {

    public static void main(String[] args) {

        DBConnector db = new DBConnector();
        Connection conn = db.setUpConnectionWithDB();

        CreateTable ct = new CreateTable();
        ct.createTable(conn);

        BackupDB backup = new BackupDB();

        CustomerService cs = new CustomerService();

        CustomerRepository cr = new CustomerRepository();
        System.out.println("Menu");
        System.out.println(
                "Type '1' to pass the data from csv' s \n" +
                        "Type '2' to add a new customer \n" +
                        "Type '3' to get a customer from DB \n" +
                        "Type '4' to update a customer's info \n" +
                        "Type '5' to delete a customer \n" +
                        "Type '6' for backup\n" +
                        "Type '7' to make a reservation");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                PassCustomerCSV passCData = new PassCustomerCSV();
                passCData.insertData(new File("src/main/resources/Customer.csv"), conn);
                PassItineraryCsv passIData = new PassItineraryCsv();
                passIData.insertData(new File("src/main/resources/Itineraries.csv"), conn);
                PassTicketsAndPaymentsCSV passTData = new PassTicketsAndPaymentsCSV();
                passTData.insertData(new File("src/main/resources/orderedTicketsAndPayments.csv"), conn);
                break;
            case 2:
                cr.addToDb(conn);
                break;
            case 3:
                System.out.println("Choose customer's id to appear");
                int getChoice = sc.nextInt();
                for (int i = 0; i < 100; i++) { //todo: get id.length
                    if (getChoice == i) {
                        cr.getFromDb(i, conn);
                    }
                }
                break;
            case 4:
                System.out.println("Choose customer's id to update");
                int updateChoice = sc.nextInt();
                for (int i = 0; i < 100; i++) { //todo: get id.length
                    if (updateChoice == i) {
                        cr.updateDb(i, conn);
                    }
                }
                break;
            case 5:
                System.out.println("Choose customer's id to delete");
                int deleteChoice = sc.nextInt();
                for (int i = 0; i < 100; i++) { //todo: get id.length
                    if (deleteChoice == i) {
                        cr.deleteFromDb(i, conn);
                    }
                }
                break;
            case 6:
                backup.doTheBackup(conn);
                break;
            case 7:
                cs.discounts(2000, PayType.Creditcard, Category.Individual);
                break;
            default:
                System.out.println("type a number from 1 to 4");
        }

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

        backup.streamBackup(offerPerDestination);

    }
}
