package com.travelcompany.eshop.mainoperations;

import java.io.File;
import java.sql.Connection;

public interface DataInsert {
    void insertData(File filename, Connection conn);
}
