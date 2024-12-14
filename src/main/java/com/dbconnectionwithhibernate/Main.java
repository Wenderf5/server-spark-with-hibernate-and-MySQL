package com.dbconnectionwithhibernate;

import com.dbconnectionwithhibernate.routes.Routes;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        try {
            port(8080);
            Routes.start();
            System.out.println("Server is running on port 8080!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
