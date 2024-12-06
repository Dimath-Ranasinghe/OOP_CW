package org.example;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Variables to hold configuration values
        int poolCapacity;
        int vendorDelay;
        int customerDelay;

        // Prompt the user for input
        System.out.print("Enter the pool capacity: ");
        poolCapacity = scanner.nextInt();

        System.out.print("Enter the vendor delay (in milliseconds): ");
        vendorDelay = scanner.nextInt();

        System.out.print("Enter the customer delay (in milliseconds): ");
        customerDelay = scanner.nextInt();

        // Initialize the configuration
        Configuration config = new Configuration();

        // Load configuration from file
        config.loadConfig();
        System.out.println("Current Configuration: " + config);

        // Update configuration if needed
        config.setPoolCapacity(poolCapacity);
        config.setVendorDelay(vendorDelay);
        config.setCustomerDelay(customerDelay);

        // Save the updated configuration to the file
        config.saveConfig();

        // Use the configuration in the system
        TicketPool ticketPool = new TicketPool(config.getPoolCapacity());

        // Create ticket vendor (producer)
        Vendor vendor = new Vendor(ticketPool, 5);
        Thread vendorThread = new Thread(vendor, "Vendor");

        // Create multiple customers (consumers)
        Customer customer1 = new Customer(ticketPool, "Customer1", config.getCustomerDelay());
        Customer customer2 = new Customer(ticketPool, "Customer2", config.getCustomerDelay());

        Thread customerThread1 = new Thread(customer1, "Customer1");
        Thread customerThread2 = new Thread(customer2, "Customer2");

        // Start threads
        customerThread1.start();
        vendorThread.start();
        customerThread2.start();

        try {
            vendorThread.join();
            customerThread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}