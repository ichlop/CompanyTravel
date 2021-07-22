package Main.mainoperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BackupDB {

    public static void doTheBackup() throws SQLException, ClassNotFoundException, FileNotFoundException {

            PrintWriter pw= new PrintWriter(new File("C:\\Users\\MIRITPC\\Desktop\\csv\\books_table1.csv"));
            StringBuilder sb=new StringBuilder();

            Connection connection=null;
            DBConnector obj_DB_Connection=new DBConnector();
            connection=obj_DB_Connection.setUpConnectionWithDB();
            ResultSet rs=null;

            String query="select * from books";
            PreparedStatement ps=connection.prepareStatement(query);
            rs=ps.executeQuery();

            while(rs.next()){
                sb.append(rs.getString("book_sl_no"));
                sb.append(",");
                sb.append(rs.getString("book_title"));
                sb.append(",");
                sb.append(rs.getString("category_name"));
                sb.append(",");
                sb.append(rs.getString("author_name"));
                sb.append(",");
                sb.append(rs.getString("publisher_name"));
                sb.append("\r\n");
            }

            pw.write(sb.toString());
            pw.close();
            System.out.println("finished");


    }
}
