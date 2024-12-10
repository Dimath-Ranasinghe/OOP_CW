package org.example;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketPool {
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());
    private final BlockingQueue<Ticket> ticketList;
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.ticketList = new LinkedBlockingQueue<>(maxCapacity);
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (ticketList.size() >= maxCapacity) {
            logger.log(Level.INFO, "TicketPool is full. Waiting for a customer to buy a ticket...");
            Thread.sleep(100); // wait if pool is full
        }
        ticketList.add(ticket);
        System.out.println("Ticket added. Pool size: " + ticketList.size());
        notifyAll(); // Notify customers that a ticket is available
    }

    public synchronized Ticket buyTicket() throws InterruptedException {
        while (ticketList.isEmpty()) {
            logger.log(Level.INFO, "No tickets available. Waiting for a vendor to add a ticket...");
            wait();
        }
        Ticket ticket = ticketList.poll(1, TimeUnit.SECONDS);
        if (ticket != null) {
            ticket.setStatus("Sold");
            logger.log(Level.INFO, "Ticket purchased. Remaining: ", ticketList.size());
        }
        notifyAll(); // Notify vendors that space is available
        return ticket;
    }

    public synchronized boolean isEmpty() {
        return ticketList.isEmpty();
    }
}
