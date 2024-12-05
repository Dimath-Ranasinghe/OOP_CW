package org.example;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final String customerName;

    public Customer(TicketPool ticketPool, String customerName) {
        this.ticketPool = ticketPool;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Ticket ticket = ticketPool.buyTicket();
                if (ticket != null) {
                    System.out.println(customerName + " purchased " + ticket);
                }
                Thread.sleep(2000); // Simulate time taken to process the purchase
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(customerName + " interrupted");
        }
    }
}

