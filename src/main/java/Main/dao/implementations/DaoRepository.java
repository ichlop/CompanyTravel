package Main.dao.implementations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DaoRepository<T> {

    //CRUD
    int addToDb(Connection connection) throws SQLException, ClassNotFoundException;
    void getfromDb(int id, Connection conn) throws SQLException, ClassNotFoundException;
    void updateDb(int id, String newEmail, Connection conn) throws SQLException;
    boolean deleteFromDb(int id, Connection conn) throws SQLException;
    List<T> getListfromDb(Connection conn, String query) throws SQLException;
}
