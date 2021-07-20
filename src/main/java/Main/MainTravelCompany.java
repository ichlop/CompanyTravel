package Main;

import Main.dao.ConnectToDB;
import Main.dao.CreateTable;
import Main.dao.PassTheDataFromCSV;
import org.h2.tools.Server;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MainTravelCompany {

    private static Server server;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        server = Server.createTcpServer("-tcpAllowOthers", "-tcpDaemon");
        server.start();

        ConnectToDB db = new ConnectToDB();
        db.setUpConnectionWithDB();
        Connection conn = db.setUpConnectionWithDB();

        CreateTable ct = new CreateTable();
        ct.createTable(conn);

        PassTheDataFromCSV passdt = new PassTheDataFromCSV();
        passdt.passTheData(new File("src/main/resources/Customer.csv"));
        passdt.passTheData(new File("src/main/resources/Itineraries.csv"));
        passdt.passTheData(new File("src/main/resources/orderedTicketsAndPayments.csv"));
    }
}
