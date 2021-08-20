package com.travelcompany.eshop.menu;

import com.travelcompany.eshop.dao.implementations.CustomerRepository;
import com.travelcompany.eshop.dao.implementations.ItineraryRepository;
import com.travelcompany.eshop.dao.implementations.OrderTicketsAndPaymentsRepository;
import com.travelcompany.eshop.exceptionhandle.ExceptionHandler;
import com.travelcompany.eshop.mainoperations.DBoperations.BackupDB;
import com.travelcompany.eshop.mainoperations.DBoperations.CreateTable;
import com.travelcompany.eshop.mainoperations.DBoperations.DBConnector;
import com.travelcompany.eshop.mainoperations.implementations.PassCustomerCSV;
import com.travelcompany.eshop.mainoperations.implementations.PassItineraryCsv;
import com.travelcompany.eshop.mainoperations.implementations.PassTicketsAndPaymentsCSV;
import com.travelcompany.eshop.model.*;
import com.travelcompany.eshop.services.CustomerService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu extends DBConnector {

    public static void mainMenu() {
        DBConnector db = new DBConnector();
        Connection conn = null;
        try {
            conn = db.setUpConnectionWithDB();
        } catch (SQLException exception) {
            ExceptionHandler.handleSQLException(exception);
        } catch (ClassNotFoundException e) {
            ExceptionHandler.handClassNotFoundException(e);
        }

        CreateTable ct = new CreateTable();
        try {
            ct.createTable(conn);
        } catch (SQLException exception) {
            ExceptionHandler.handleSQLException(exception);
        } catch (IOException e) {
            ExceptionHandler.handleIOException(e);
        }

        CustomerService cs = new CustomerService();
        BackupDB backup = new BackupDB();
        CustomerRepository cr = new CustomerRepository();
        String getCustomers = "SELECT * FROM customer";
        List<Customer> customers = null;
        try {
            customers = cr.getListFromDb(conn, getCustomers);
        } catch (SQLException exception) {
            ExceptionHandler.handleSQLException(exception);
        }

        System.out.println("---------------");
        System.out.println("Menu");
        System.out.println("---------------");
        System.out.println(
                "Type '1' to pass the data from csv' s \n" +
                        "Type '2' to add a new customer \n" +
                        "Type '3' to get a customer from DB \n" +
                        "Type '4' to update a customer's info \n" +
                        "Type '5' to delete a customer \n" +
                        "Type '6' for backup\n" +
                        "Type '7' to make a reservation\n" +
                        "Type '8' to see the reports");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                PassCustomerCSV passCData = new PassCustomerCSV();
                PassItineraryCsv passIData = new PassItineraryCsv();
                PassTicketsAndPaymentsCSV passTData = new PassTicketsAndPaymentsCSV();
                try {
                    passCData.insertData(new File("src/main/resources/Customer.csv"), conn);
                    passIData.insertData(new File("src/main/resources/Itineraries.csv"), conn);
                    passTData.insertData(new File("src/main/resources/orderedTicketsAndPayments.csv"), conn);
                } catch (IOException e) {
                    ExceptionHandler.handleIOException(e);
                } catch (SQLException exception) {
                    ExceptionHandler.handleSQLException(exception);                }


                break;
            case 2:
                try {
                    cr.addToDb(conn);
                } catch (SQLException exception) {
                    ExceptionHandler.handleSQLException(exception);
                }
                break;
            case 3:
                System.out.println("Choose customer's id to appear");
                int getChoice = sc.nextInt();
                for (Customer customer: customers) {
                    if (customer.getId() == getChoice) {
                        try {
                            cr.getFromDb(getChoice, conn);
                        } catch (SQLException exception) {
                            ExceptionHandler.handleSQLException(exception);
                        }
                    }
                }
                break;
            case 4:
                System.out.println("Choose customer's id to update");
                int updateChoice = sc.nextInt();
                for (Customer customer: customers) {
                    if (customer.getId() == updateChoice) {
                        try {
                            cr.updateDb(updateChoice, conn);
                        } catch (SQLException exception) {
                            ExceptionHandler.handleSQLException(exception);
                        }
                    }
                }
                break;
            case 5:
                System.out.println("Choose customer's id to delete");
                int deleteChoice = sc.nextInt();
                for (Customer customer: customers) {
                    if (customer.getId() == deleteChoice) {
                        try {
                            cr.deleteFromDb(deleteChoice, conn);
                        } catch (SQLException exception) {
                            ExceptionHandler.handleSQLException(exception);
                        }
                    }
                }
                break;
            case 6:
                try {
                    backup.doTheBackup(conn);
                } catch (SQLException exception) {
                    ExceptionHandler.handleSQLException(exception);
                } catch (FileNotFoundException e) {
                    ExceptionHandler.handFileNotFoundException(e);
                }

                break;
            case 7:
                cs.discounts(2000, PayType.Creditcard, Category.Individual);
                break;
            case 8:
                OrderTicketsAndPaymentsRepository or = new OrderTicketsAndPaymentsRepository();
                String getTicketsQuery = "SELECT * FROM orderedTicketsAndPayments";
                List<Ticket> tickets = null;
                try {
                    tickets = or.getListFromDb(conn, getTicketsQuery);
                } catch (SQLException exception) {
                    ExceptionHandler.handleSQLException(exception);
                }

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
                List<Itinerary> itineraries = null;
                try {
                    itineraries = ir.getListFromDb(conn, getItineraryQuery);
                } catch (SQLException exception) {
                    ExceptionHandler.handleSQLException(exception);
                }

                Map<Object, Long> offerPerDestination = itineraries.stream().collect(Collectors.groupingBy(destination -> destination.getDestinationAirportId(), Collectors.counting()));
                offerPerDestination.forEach((k, v) -> System.out.println("Destination Airport: " + k + ", Total arrivals: " + v));
                System.out.println("-------------------------");
                Map<Object, Long> offerPerDeparture = itineraries.stream().collect(Collectors.groupingBy(depart -> depart.getDepartureAirportId(), Collectors.counting()));
                offerPerDeparture.forEach((k, v) -> System.out.println("Departure Airport: " + k + ", Total arrivals: " + v));
                System.out.println("-------------------------");

                try {
                    backup.streamBackup(offerPerDestination);
                } catch (IOException e) {
                    ExceptionHandler.handleIOException(e);
                }
                break;
            default:
                System.out.println("type a number from 1 to 4");
        }
    }
}
