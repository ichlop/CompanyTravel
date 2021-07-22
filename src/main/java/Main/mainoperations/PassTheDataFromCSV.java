package Main.mainoperations;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class PassTheDataFromCSV {

    private static final Logger logger = LoggerFactory.getLogger(PassTheDataFromCSV.class);

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    public void insertData(File filename, Connection conn, String query) throws IOException, SQLException {

        File csvFilePath = filename;
        int batchSize = 20;

        PreparedStatement statement = conn.prepareStatement(query);
        BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

        String lineText = null;
        int count = 0;

        lineReader.readLine(); // skip header line
        int id = 0;
        String cName = null;
        String email = null;
        String addressCity = null;
        String nationality = null;
        String category = null;
        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");
            id =  ID_GENERATOR.getAndIncrement();
            cName = data[1];
            email = data[2];
            addressCity = data[3];
            nationality = data[4];
            category = data[5];
        }
        statement.setInt(1, id);
        statement.setString(2, cName);
        statement.setString(3, email);
        statement.setString(4, addressCity);
        statement.setString(5, nationality);
        statement.setString(6, category);
        statement.addBatch();
        if (count % batchSize == 0) {
            statement.executeBatch();
            lineReader.close();
        }

        statement.executeBatch();

        conn.commit();
        conn.close();

//        FileReader filereader = new FileReader(filename);
//        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
//        CSVReader csvReader = new CSVReaderBuilder(filereader)
//                .withCSVParser(parser)
//                .build();
//        logger.info("Successfully passed the data of " + filename);
    }
}
