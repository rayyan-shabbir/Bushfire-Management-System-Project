package com.bushfire.network;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.io.*;


public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4442);
            
            System.out.println("Server is Ready...");

            while (true) {

                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                String role = bufferedReader.readLine();

                // Send acknowledgement to client
                bufferedWriter.write("Role received. Now Please enter details.");
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String[] answers = bufferedReader.readLine().split(",");

//                System.out.println("Go");
                // Display received information
                System.out.println("Role: " + role);
                System.out.println("Name: " + answers[1]);
                System.out.println("Coordinates : " + answers[2] + "," + answers[3]);
                System.out.println("Model : " + answers[4]);

                // Send acknowledgement to client
                bufferedWriter.write("Details received.");
                bufferedWriter.newLine();
                bufferedWriter.flush();
                
                Server.saveToDatabase(role, answers[1], answers[2], answers[3], answers[4]);
                
                if (role.equalsIgnoreCase("BYE"))
                    break;

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();

                if (inputStreamReader != null)
                    inputStreamReader.close();

                if (outputStreamWriter != null)
                    outputStreamWriter.close();

                if (bufferedReader != null)
                    bufferedReader.close();

                if (bufferedWriter != null)
                    bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveToDatabase(String role, String str1, String str2, String str3, String str4) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
    	Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/bushfire?useSSL=false";
        String username = "root";
        String password = "cocacola";
        
        String coordinates = str2 + "," + str3;

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
        	System.out.println("Connection DB successfull.");
        	if (role.equals("reports")) {
        		
        		try {
        		    int rdroneid = Integer.parseInt(str4);
        		    String query = "INSERT INTO reports(rname, rcoordinates, rdroneid) VALUES (?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, str1);
                        preparedStatement.setString(2, coordinates);
                        preparedStatement.setInt(3, rdroneid);
                        int rowCount = preparedStatement.executeUpdate();
                        System.out.println("Reports: Inserted " + rowCount + " rows into " + role + " table.");
                    }
        		} catch (NumberFormatException e) {
        		     System.out.println(e);
        		}
        		
        	    
        	} else if (role.equals("drones")){
        		String query = "INSERT INTO drones(dname, dcoordinates, dmodel) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, str1);
                    preparedStatement.setString(2, coordinates);
                    preparedStatement.setString(3, str4);
                    int rowCount = preparedStatement.executeUpdate();
                    System.out.println("Drones: Inserted " + rowCount + " rows into " + role + " table.");
                }
                
        	} else if (role.equals("trucks")){
        		String query = "INSERT INTO trucks(tname, tcoordinates, tmodel) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, str1);
                    preparedStatement.setString(2, coordinates);
                    preparedStatement.setString(3, str4);
                    int rowCount = preparedStatement.executeUpdate();
                    System.out.println("Trucks: Inserted " + rowCount + " rows into " + role + " table.");
                }
        	}
        	
            
        }
	}
}
