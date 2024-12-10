package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRate;
    private final int totalTickets;
    private final AtomicInteger ticketCounter;

    public Vendor(TicketPool ticketPool, int releaseRate, int totalTickets ) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
        this.totalTickets = totalTickets;
        this.ticketCounter = new AtomicInteger(1);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < totalTickets; i++) {
                Ticket ticket = new Ticket(ticketCounter.getAndIncrement());
                ticketPool.addTicket(ticket);
                Thread.sleep(releaseRate); // Simulate time taken to produce a ticket
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("TicketVendor interrupted");
        }
    }
}
