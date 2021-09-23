package com.travelcompany.eshop.mainoperations.datapass.implementation;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface DataInsert {
    void insertData(File filename, Connection conn) throws IOException, SQLException;
}
