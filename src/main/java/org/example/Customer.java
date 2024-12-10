package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Customer implements Runnable {
    private static final Logger logger = Logger.getLogger(Customer.class.getName());
    private final TicketPool ticketPool;
    private final int retrievalRate;
    private final List<Ticket> purchasedTickets;


    public Customer(TicketPool ticketPool, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
        this.purchasedTickets = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            while (!ticketPool.isEmpty()) {
                Ticket ticket = ticketPool.buyTicket();

                if (ticket != null) {
                    purchasedTickets.add(ticket);
                    System.out.println(" purchased " + ticket);

                    Thread.sleep(retrievalRate); // Simulate time taken to process the purchase
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("interrupted");
        }
    }

    public List<Ticket> getPurchasedTickets() {
        return purchasedTickets;
    }
}

