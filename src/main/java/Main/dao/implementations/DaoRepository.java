package Main.dao.implementations;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DaoRepository<T> {

    //CRUD
    int addtoDb(T t) throws SQLException;
    T getfromDb(int id) throws SQLException;
    void updatetoDb(int id, String newEmail, Connection conn) throws SQLException;
    boolean deleteFromDb(int id, Connection conn) throws SQLException;
    List<T> getfromDb();
}
