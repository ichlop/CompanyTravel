package com.travelcompany.eshop.exceptionhandle;

public class ExceptionHandler {
    public static void handleException(Exception e, String message) {
        System.out.println(message);
        System.out.println("Error was: " + e.getMessage());
        //e.printStackTrace();
        System.out.println("The application will stop now");
        System.exit(1);
    }
}
