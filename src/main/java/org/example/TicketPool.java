package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Ticket> ticketQueue;
    private final int capacity;

    public TicketPool(int capacity) {
        this.ticketQueue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (ticketQueue.size() == capacity) {
            System.out.println("TicketPool is full. Waiting for a customer to buy a ticket...");
            wait();
        }
        ticketQueue.add(ticket);
        System.out.println("Ticket added: " + ticket);
        notifyAll(); // Notify customers that a ticket is available
    }

    public synchronized Ticket buyTicket() throws InterruptedException {
        while (ticketQueue.isEmpty()) {
            System.out.println("No tickets available. Waiting for a vendor to add a ticket...");
            wait();
        }
        Ticket ticket = ticketQueue.poll();
        if (ticket != null) {
            ticket.setStatus("Sold");
            System.out.println("Ticket bought: " + ticket);
        }
        notifyAll(); // Notify vendors that space is available
        return ticket;
    }

    public synchronized boolean isEmpty() {
        return ticketQueue.isEmpty();
    }
}
