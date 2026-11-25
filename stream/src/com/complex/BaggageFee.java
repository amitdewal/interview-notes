/**
 * 
 */
package com.start.test.stream;

public class BaggageFee {

    private int feeId;
    private int passengerId;
    private String passengerName;
    private double amount;
    private String status;

    public BaggageFee(int feeId, int passengerId, String passengerName, double amount, String status) {
        this.feeId = feeId;
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.amount = amount;
        this.status = status;
    }

    public int getFeeId() {
        return feeId;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}