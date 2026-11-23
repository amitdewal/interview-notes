// package main.design-pattern.behavioral.strategy;

public class Main {
    public static void main(String[] args) {

        PaymentStrategy strategy = new UpiPayment();

        PaymentService paymentService =
                new PaymentService(strategy);

        paymentService.makePayment(1000);
    }
}

interface PaymentStrategy {

    void pay(double amount);
}


class CardPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using PayPal");
    }
}

class UpiPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using UPI");
    }
}


class PaymentService {

    private PaymentStrategy paymentStrategy;

    public PaymentService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void makePayment(double amount) {
        paymentStrategy.pay(amount);
    }
}