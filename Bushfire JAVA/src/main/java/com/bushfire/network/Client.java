package com.bushfire.network;

import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        Scanner scanner = new Scanner(System.in);

        try {
            boolean continueRunning = true;

            while (continueRunning) {
                socket = new Socket("localhost", 4442);

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                // Ask the client to select from three options
                System.out.println("Select Information you want to send?");
                System.out.println("1. Drone");
                System.out.println("2. Fire Truck");
                System.out.println("3. Fire Report");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                String role;
                switch (choice) {
                    case 1:
                        role = "drones";
                        break;
                    case 2:
                        role = "trucks";
                        break;
                    case 3:
                        role = "reports";
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        continue; // Go to next iteration
                }

                // Send the selected role to the server
                bufferedWriter.write(role);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                // Receive and print response from server
                System.out.println("Server: " + bufferedReader.readLine());

                // Depending on the selected role, prompt for specific information
                String answer1 = "";
                String answer2 = "";
                String answer3 = "";

                switch (choice) {
                    case 1:
                        System.out.println("Enter drone name: ");
                        answer1 = scanner.nextLine();
                        System.out.println("Enter coordinates [x,y]: ");
                        answer2 = scanner.nextLine();
                        System.out.println("Enter Model: ");
                        answer3 = scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("Enter truck name: ");
                        answer1 = scanner.nextLine();
                        System.out.println("Enter coordinates [x,y]: ");
                        answer2 = scanner.nextLine();
                        System.out.println("Enter Model: ");
                        answer3 = scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("Enter report name: ");
                        answer1 = scanner.nextLine();
                        System.out.println("Enter coordinates [x,y]: ");
                        answer2 = scanner.nextLine();
                        System.out.println("Enter drone id sending report: ");
                        answer3 = scanner.nextLine();
                        break;
                }

                // Send the role and answers to the server
                String message = role + "," + answer1 + "," + answer2 + "," + answer3;
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                // Receive and print response from server
                System.out.println("Server: " + bufferedReader.readLine());

                System.out.println("Do you want to continue? (yes/no)");
                String continueChoice = scanner.nextLine();
                if (continueChoice.equalsIgnoreCase("no")) {
                	System.out.println("Thankyou, Have a nice day.");
                    continueRunning = false;
                }

                // Close the socket
                socket.close();
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
}
