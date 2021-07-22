package Main;

import Main.mainoperations.BackupDB;
import Main.mainoperations.DBConnector;
import Main.mainoperations.CreateTable;
import Main.dao.CustomerRepository;
import Main.mainoperations.PassTheDataFromCSV;
import org.h2.tools.Server;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MainTravelCompany {

//    private static Server server;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

//        server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
//        server.start();

        DBConnector db = new DBConnector();
        db.setUpConnectionWithDB();
        Connection conn = db.setUpConnectionWithDB();

        CreateTable ct = new CreateTable();
        ct.createTable(conn);

        PassTheDataFromCSV passdt = new PassTheDataFromCSV();
        String query1 = "INSERT INTO Customer ( Id,cName,Email,AddressCity,Nationality,Category) VALUES (?, ?, ?, ?, ?, ?)";
        passdt.insertData(new File("src/main/resources/Customer.csv"), conn, query1);
        String query2 = "INSERT INTO Customer ( Id,DepartureAirportId,DestinationAirportId,Departuredate, airlines) VALUES (?, ?, ?, ?, ?)";
        passdt.insertData(new File("src/main/resources/Itineraries.csv"), conn, query2);
        String query3 = "INSERT INTO Customer ( Id,PassengerId,ItineraryId,PaymentMethod,Amount) VALUES (?, ?, ?, ?, ?)";
        passdt.insertData(new File("src/main/resources/orderedTicketsAndPayments.csv"), conn, query3);
//
//        CustomerRepository cr = new CustomerRepository();
//        cr.addToDb(conn);
//        cr.getfromDb(5,conn);
//        cr.getfromDb(conn);
////      Stream<String> stringStream = customers.stream();
//
//
//        BackupDB backup = new BackupDB();
//        backup.doTheBackup();
    }
}