package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final AtomicInteger ticketCounter;
    private final int releaseRate;

    public Vendor(TicketPool ticketPool, int releaseRate) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
        this.ticketCounter = new AtomicInteger(1);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < releaseRate; i++) {
                Ticket ticket = new Ticket(ticketCounter.getAndIncrement());
                ticketPool.addTicket(ticket);
                Thread.sleep(1000); // Simulate time taken to produce a ticket
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("TicketVendor interrupted");
        }
    }
}
