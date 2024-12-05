package org.example;

public class Main {
    public static void main(String[] args) {
        int poolCapacity = 5; // Maximum tickets in the pool
        TicketPool ticketPool = new TicketPool(poolCapacity);

        // Create ticket vendor (producer)
        Vendor vendor = new Vendor(ticketPool);
        Thread vendorThread = new Thread(vendor, "Vendor");

        // Create multiple customers (consumers)
        Customer customer1 = new Customer(ticketPool, "Customer1");
        Customer customer2 = new Customer(ticketPool, "Customer2");
        Thread customerThread1 = new Thread(customer1, "Customer1");
        Thread customerThread2 = new Thread(customer2, "Customer2");

        // Start threads
        customerThread1.start();
        vendorThread.start();
        customerThread2.start();
    }
}