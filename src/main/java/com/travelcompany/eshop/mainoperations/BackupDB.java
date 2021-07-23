package com.travelcompany.eshop.mainoperations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BackupDB {

    public static void doTheBackup(Connection conn) throws SQLException, FileNotFoundException {

        PrintWriter pw = new PrintWriter("c:\\Users\\chloptsi\\IdeaProjects\\backup\\file1.csv");
        StringBuilder sb = new StringBuilder();

        ResultSet rs = null;

        String query = "select * from Customer";
        PreparedStatement ps = conn.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
            sb.append(rs.getString("id"));
            sb.append(",");
            sb.append(rs.getString("Name"));
            sb.append(",");
            sb.append(rs.getString("Email"));
            sb.append(",");
            sb.append(rs.getString("AddressCity"));
            sb.append(",");
            sb.append(rs.getString("Nationality"));
            sb.append(",");
            sb.append(rs.getString("Category"));
            sb.append("\r\n");
        }

        pw.write(sb.toString());
        pw.close();
        System.out.println("finished");
    }

    public static void doTheStreamBackup() throws IOException {

    }
}
