package Main.dao;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class PassTheDataFromCSV {

    private static final Logger logger = LoggerFactory.getLogger(PassTheDataFromCSV.class);

    public void passTheData(File filename) throws IOException {

        FileReader filereader = new FileReader(filename);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();
        logger.info("Successfully passed the data of " +filename);

//        InputStream inputStream = CreateTable.class.getClassLoader()
//                .getResourceAsStream("sql.properties");

//        Scanner input = new Scanner((new File("C:\\Users\\chloptsi\\IdeaProjects\\TravelCompany\\src\\main\\resources\\" + filename)));

    }
//    private void runInsertCommands(Statement statement, String... commands) throws SQLException {
//        for (String command : commands) {
//            //logger.info("Insert command was successful with {} row(s) affected.", statement.executeUpdate(command));
//        }
//    }

//    private void insertData(Connection connection) {
//        try (Statement statement = connection.createStatement()) {
//
//            //@formatter:off
//            // Classic insert statements
//            runInsertCommands(statement,
//                    sqlCommands.getProperty("insert.table.001"),
//                    sqlCommands.getProperty("insert.table.002"),
//                    sqlCommands.getProperty("insert.table.003"),
//                    sqlCommands.getProperty("insert.table.004"));
//            //@formatter:on
//
//            // Enable transaction support
//            connection.setAutoCommit(false);
//
//            // Insert data in batch mode
//            statement.addBatch(sqlCommands.getProperty("insert.table.005"));
//            statement.addBatch(sqlCommands.getProperty("insert.table.006"));
//            statement.addBatch(sqlCommands.getProperty("insert.table.007"));
//            statement.addBatch(sqlCommands.getProperty("insert.table.008"));
//            statement.addBatch(sqlCommands.getProperty("insert.table.009"));
//            statement.addBatch(sqlCommands.getProperty("insert.table.010"));
//
//            int[] rowsAffectedArray = statement.executeBatch();
//            logger.info("Insert commands were successful with {} row(s) affected.",
//                    Arrays.stream(rowsAffectedArray).summaryStatistics().getSum());
//        } catch (SQLException ex) {
//            logger.error("Error while inserting data.", ex);
//        }
//    }


}
