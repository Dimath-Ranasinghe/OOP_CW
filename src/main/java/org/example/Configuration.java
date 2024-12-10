package org.example;

import java.io.*;
import java.util.Properties;

public class Configuration {
    private int totalTickets;   // Maximum number of tickets in the pool
    private int ticketReleaseRate;    // Time delay for the vendor to add tickets (in milliseconds)
    private int customerRetrievalRate;  // Time delay for customers to buy tickets (in milliseconds)
    private int maxTicketCapacity;

    private static final String CONFIG_FILE = "config.properties";

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public Configuration() {
        // Set default values
        this.totalTickets = 5;
        this.ticketReleaseRate = 1000;
        this.customerRetrievalRate = 2000;
        this.maxTicketCapacity = 10;
    }

    // Getters and Setters
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int vendorDelay) {
        this.ticketReleaseRate = vendorDelay;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Load configuration from a file
    public void loadConfig() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
            System.out.println(properties.getProperty("totalTickets", "5"));
            this.totalTickets = Integer.parseInt(properties.getProperty("totalTickets", "5"));
            this.ticketReleaseRate = Integer.parseInt(properties.getProperty("ticketReleaseRate", "1000"));
            this.customerRetrievalRate = Integer.parseInt(properties.getProperty("customerRetrievalRate", "2000"));
            this.maxTicketCapacity = Integer.parseInt(properties.getProperty("maxTicketCapacity", "10"));
            System.out.println("Configuration loaded successfully.");
        } catch (IOException e) {
            System.out.println("Could not load configuration file. Using default values.");
        }
    }

    // Save configuration to a file
    public void saveConfig() {
        Properties properties = new Properties();
        properties.setProperty("totalTickets", String.valueOf(this.totalTickets));
        properties.setProperty("ticketReleaseRate", String.valueOf(this.ticketReleaseRate));
        properties.setProperty("customerRetrievalRate", String.valueOf(this.customerRetrievalRate));
        properties.setProperty("maxTicketCapacity", String.valueOf(this.maxTicketCapacity));

        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            properties.store(output, "Ticket System Configuration");
            System.out.println("Configuration saved successfully.");
        } catch (IOException e) {
            System.out.println("Could not save configuration.");
        }
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }
}

