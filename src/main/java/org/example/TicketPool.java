package org.example;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketPool {
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());
    private final LinkedList<Ticket> ticketList;
    private final int capacity;

    public TicketPool(int capacity) {
        this.ticketList = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (ticketList.size() == capacity) {
            logger.log(Level.INFO, "TicketPool is full. Waiting for a customer to buy a ticket...");
            wait();
        }
        ticketList.add(ticket);
        System.out.println("Ticket added: " + ticket);
        notifyAll(); // Notify customers that a ticket is available
    }

    public synchronized Ticket buyTicket() throws InterruptedException {
        while (ticketList.isEmpty()) {
            logger.log(Level.INFO, "No tickets available. Waiting for a vendor to add a ticket...");
            wait();
        }
        Ticket ticket = ticketList.poll();
        if (ticket != null) {
            ticket.setStatus("Sold");
            logger.log(Level.INFO, "Ticket bought: {0}", ticket);
        }
        notifyAll(); // Notify vendors that space is available
        return ticket;
    }

    public synchronized boolean isEmpty() {
        return ticketList.isEmpty();
    }
}
