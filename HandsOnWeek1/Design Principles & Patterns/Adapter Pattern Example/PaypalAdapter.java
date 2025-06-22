public class PaypalAdapter implements PaymentProcessor {
    private PaypalGateway paypal;

    public PaypalAdapter(PaypalGateway paypal) {
        this.paypal = paypal;
    }

    public void processPayment(double amount) {
        paypal.makePayment(amount);
    }
}
