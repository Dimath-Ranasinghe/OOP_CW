package org.example;

import java.math.BigDecimal;

public class Ticket {
    private int ticketId;
    private String status;

    public Ticket(int ticketId) {
        this.ticketId = ticketId;
        this.status = "Available";
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + ticketId +
                ", status='" + status + '\'' +
                '}';
    }
}
