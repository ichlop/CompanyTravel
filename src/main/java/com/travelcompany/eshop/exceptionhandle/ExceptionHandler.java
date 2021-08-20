package com.travelcompany.eshop.exceptionhandle;

public class ExceptionHandler {
    public static void handleSQLException(Exception e) {
        System.out.println("Cannot connect with DB");
        System.out.println("Error was: " + e.getMessage());
        System.out.println("The application will stop now");
        System.exit(1);
    }

    public static void handleIOException(Exception e) {
        System.out.println("Cannot insert data from the source");
        System.out.println("Error was: " + e.getMessage());
        System.out.println("The application will stop now");
        System.exit(1);
    }

    public static void handClassNotFoundException(Exception e) {
        System.out.println("Cannot connect with DB path");
        System.out.println("Error was: " + e.getMessage());
        System.out.println("The application will stop now");
        System.exit(1);
    }

    public static void handFileNotFoundException(Exception e) {
        System.out.println("Cannot find the file from the source");
        System.out.println("Error was: " + e.getMessage());
        System.out.println("The application will stop now");
        System.exit(1);
    }

}
