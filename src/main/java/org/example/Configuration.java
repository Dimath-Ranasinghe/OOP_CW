package org.example;

import java.io.*;
import java.util.Properties;

public class Configuration {
    private int poolCapacity;   // Maximum number of tickets in the pool
    private int vendorDelay;    // Time delay for the vendor to add tickets (in milliseconds)
    private int customerDelay;  // Time delay for customers to buy tickets (in milliseconds)
    private int releaseRate;

    private static final String CONFIG_FILE = "config.properties";

    public Configuration() {
        // Set default values
        this.poolCapacity = 5;
        this.vendorDelay = 1000;
        this.customerDelay = 2000;
    }

    // Getters and Setters
    public int getPoolCapacity() {
        return poolCapacity;
    }

    public void setPoolCapacity(int poolCapacity) {
        this.poolCapacity = poolCapacity;
    }

    public int getVendorDelay() {
        return vendorDelay;
    }

    public void setVendorDelay(int vendorDelay) {
        this.vendorDelay = vendorDelay;
    }

    public int getCustomerDelay() {
        return customerDelay;
    }

    public void setCustomerDelay(int customerDelay) {
        this.customerDelay = customerDelay;
    }

    // Load configuration from a file
    public void loadConfig() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
            System.out.println(properties.getProperty("poolCapacity", "5"));
            this.poolCapacity = Integer.parseInt(properties.getProperty("poolCapacity", "5"));
            this.vendorDelay = Integer.parseInt(properties.getProperty("vendorDelay", "1000"));
            this.customerDelay = Integer.parseInt(properties.getProperty("customerDelay", "2000"));
            System.out.println("Configuration loaded successfully.");
        } catch (IOException e) {
            System.out.println("Could not load configuration file. Using default values.");
        }
    }

    // Save configuration to a file
    public void saveConfig() {
        Properties properties = new Properties();
        properties.setProperty("poolCapacity", String.valueOf(this.poolCapacity));
        properties.setProperty("vendorDelay", String.valueOf(this.vendorDelay));
        properties.setProperty("customerDelay", String.valueOf(this.customerDelay));

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
                "poolCapacity=" + poolCapacity +
                ", vendorDelay=" + vendorDelay +
                ", customerDelay=" + customerDelay +
                '}';
    }
}

