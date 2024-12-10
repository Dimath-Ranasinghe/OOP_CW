package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Variables to hold configuration values
        int totalTickets;
        int ticketReleaseRate;
        int customerRetievalRate;
        int maxTicketCapacity;

        // Prompt the user for input
        System.out.print("Enter the Total Number of Tickets: ");
        totalTickets = scanner.nextInt();

        System.out.print("Enter Ticket Release Rate : ");
        ticketReleaseRate = scanner.nextInt();

        System.out.print("Enter the customer Retrieval Rate (in milliseconds): ");
        customerRetievalRate = scanner.nextInt();

        System.out.println("Enter Maximum Ticket Capacity: ");
        maxTicketCapacity = scanner.nextInt();

        // Initialize the configuration
        Configuration config = new Configuration();

        // Load configuration from file
        config.loadConfig();
        System.out.println("Current Configuration: " + config);

        // Update configuration if needed
        config.setTotalTickets(totalTickets);
        config.setTicketReleaseRate(ticketReleaseRate);
        config.setCustomerRetrievalRate(customerRetievalRate);
        config.setMaxTicketCapacity(maxTicketCapacity);

        // Save the updated configuration to the file
        config.saveConfig();

        // Use the configuration in the system
        TicketPool ticketPool = new TicketPool(config.getTotalTickets());

        // Create ticket vendor (producer)
        Vendor vendor = new Vendor(ticketPool, config.getTicketReleaseRate(), config.getTotalTickets());
        Thread vendorThread = new Thread(vendor, "Vendor");

        // Create multiple customers (consumers)
        Customer customer1 = new Customer(ticketPool, config.getCustomerRetrievalRate());
//        Customer customer2 = new Customer(ticketPool, config.getCustomerRetrievalRate());

        Thread customerThread1 = new Thread(customer1, "Customer1");
//        Thread customerThread2 = new Thread(customer2, "Customer2");

        // Start threads
        vendorThread.start();
        customerThread1.start();
//        customerThread2.start();

        try {
            vendorThread.join();
            customerThread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}