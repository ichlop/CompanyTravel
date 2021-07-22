package Main;

import Main.dao.ItineraryRepository;
import Main.mainoperations.DBConnector;
import Main.mainoperations.CreateTable;
import Main.dao.CustomerRepository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class MainTravelCompany {

//    private static Server server;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

//        server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
//        server.start();

        DBConnector db = new DBConnector();
        Connection conn = db.setUpConnectionWithDB();

        CreateTable ct = new CreateTable();
        ct.createTable(conn);

//        PassTheDataFromCSV passdt = new PassTheDataFromCSV();
//        passdt.insertData(new File("src/main/resources/Customer.csv"), conn);
//        String query2 = "INSERT INTO Itinerary ( Id,DepartureAirportId,DestinationAirportId,Departuredate, airlines) VALUES (?, ?, ?, ?, ?)";
//        passdt.insertData(new File("src/main/resources/Itineraries.csv"), conn, query2);
//        String query3 = "INSERT INTO OrderedTicketAndPayment ( Id,PassengerId,ItineraryId,PaymentMethod,Amount) VALUES (?, ?, ?, ?, ?)";
//        passdt.insertData(new File("src/main/resources/orderedTicketsAndPayments.csv"), conn, query3);
//
        CustomerRepository cr = new CustomerRepository();
        cr.addToDb(conn);
        cr.getfromDb(5,conn);

//      ItineraryRepository ir = new ItineraryRepository();
//      String query = "select PassengerId,AmountPaid from OrderedTicketsAndPayments";
//      List<String> list1 = ir.getListfromDb(conn,query);
//      Stream<String> stringStream = list1.stream();
//      TODO: count orderds per id and export total payments for each one
//      stringStream.forEach(System::println);

//      String query = "select DepartureAirportId,DestinationAirportId from Itinerary";
//      List<String> list2 = ir.getListfromDb(conn,query);
//      Stream<String> stringStream = list2.stream();
//      TODO: count destination and departures

//      String query = "select Id,PassengerId from OrderedTicketsAndPayments";
//      List<String> list3 = ir.getListfromDb(conn,query);
//      Stream<String> stringStream = list3.stream();
//      todo: foreach id counter for each PassengerId counter+1

//     String query = "select Id,PassengerId from OrderedTicketsAndPayments";
//     List<String> list4 = ir.getListfromDb(conn,query);
//     Stream<String> stringStream = list4.stream();
//     todo: from 0-customerId.length; if customerId.counter==0; sout customerId

//     todo save in excel



//        BackUp
//        BackupDB backup = new BackupDB();
//        backup.doTheBackup();
    }
}