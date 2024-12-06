package org.example;

import java.util.logging.Logger;

public class Customer implements Runnable {
    private static final Logger logger = Logger.getLogger(Customer.class.getName());
    private final TicketPool ticketPool;
    private final String customerName;
    private final int delay;

    public Customer(TicketPool ticketPool, String customerName, int delay) {
        this.ticketPool = ticketPool;
        this.customerName = customerName;
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Ticket ticket = ticketPool.buyTicket();
                if (ticket != null) {
                    System.out.println(customerName + " purchased " + ticket);
                }
                Thread.sleep(delay); // Simulate time taken to process the purchase
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(customerName + " interrupted");
        }
    }
}

