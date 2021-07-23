package com.travelcompany.eshop;

import com.travelcompany.eshop.dao.CustomerRepository;
import com.travelcompany.eshop.dao.OrderTicketsAndPaymentsRepository;
import com.travelcompany.eshop.mainoperations.CreateTable;
import com.travelcompany.eshop.mainoperations.DBConnector;
import com.travelcompany.eshop.mainoperations.PassTheDataFromCSV;
import com.travelcompany.eshop.mainoperations.PassTheTicketData;
import com.travelcompany.eshop.model.Ticket;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MainTravelCompany {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        DBConnector db = new DBConnector();
        Connection conn = db.setUpConnectionWithDB();

        CreateTable ct = new CreateTable();
        ct.createTable(conn);

        //todo: να μη τα δειχνει 3πλα
        PassTheDataFromCSV passdt = new PassTheDataFromCSV();
//        passdt.insertData(new File("src/main/resources/Customer.csv"), conn);
//        passdt.insertData(new File("src/main/resources/Itineraries.csv"), conn);
//        PassTheTicketData passtickets = new PassTheTicketData();
//        passtickets.insertTicketData(new File("src/main/resources/orderedTicketsAndPayments.csv"), conn);

        CustomerRepository cr = new CustomerRepository();
//        cr.addToDb(conn);//todo: doesnt work
//        cr.getFromDb(6,conn);
//        cr.updateDb(4,conn);
//        cr.deleteFromDb(3, conn);


        OrderTicketsAndPaymentsRepository or = new OrderTicketsAndPaymentsRepository();
        String getTicketsQuery = "SELECT * FROM orderedTicketsAndPayments";
        List<Ticket> tickets = or.getListFromDb(conn, getTicketsQuery);



////        BackUp
//        BackupDB backup = new BackupDB();
//        backup.doTheBackup(conn);
    }
}
//     todo: check if table exists
//     todo: streams
//     todo: save streams in excel
//     todo: exception handling
//     todo: JUnit