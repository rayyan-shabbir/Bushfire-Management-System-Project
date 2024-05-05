package com.bushfire.registration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/map")
public class MapServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Initialize arrays to store data from three tables
        String[][] dronesData = null;
        String[][] reportsData = null;
        String[][] trucksData = null;

        // Retrieve data from drones table
        try {
            dronesData = retrieveDataFromTable("SELECT dname, dcoordinates FROM drones");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Retrieve data from reports table
        try {
            reportsData = retrieveDataFromTable("SELECT rname, rcoordinates FROM reports");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Retrieve data from trucks table
        try {
            trucksData = retrieveDataFromTable("SELECT tname, tcoordinates FROM trucks");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Set request attributes for each dataset
        request.setAttribute("dronesData", dronesData);
        request.setAttribute("reportsData", reportsData);
        request.setAttribute("trucksData", trucksData);
        request.setAttribute("name", "rayyan");

        // Forward the request to the map.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("map.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private String[][] retrieveDataFromTable(String query) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String[][] dataArray = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bushfire?useSSL=false", "root", "cocacola");

            // Execute query
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            // Get the number of rows in the result set
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            // Initialize the array with the correct size
            dataArray = new String[rowCount][3]; // 3 columns: Name, Latitude, Longitude

            // Iterate through the result set and add data to dataArray
            int i = 0;
            while (rs.next()) {
                String[] rowData = new String[3];
                rowData[0] = rs.getString(1); // Name
                // Splitting coordinates into latitude and longitude
                String[] coordinates = rs.getString(2).split(",");
                rowData[1] = coordinates[0].trim(); // Latitude
                rowData[2] = coordinates[1].trim(); // Longitude
                dataArray[i] = rowData;
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        } finally {
            // Close connections and resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return dataArray;
    }
}