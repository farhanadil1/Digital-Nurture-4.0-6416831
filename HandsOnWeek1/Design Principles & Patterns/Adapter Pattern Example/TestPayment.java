public class TestPayment {
    public static void main(String[] args) {
        System.out.println("Testing Payment Processors with Adapter Pattern");
        // PayPal payment
        PaypalGateway paypal = new PaypalGateway();
        PaymentProcessor paypalAdapter = new PaypalAdapter(paypal);
        paypalAdapter.processPayment(1500.0);

        // Stripe payment
        StripeGateway stripe = new StripeGateway();
        PaymentProcessor stripeAdapter = new StripeAdapter(stripe);
        stripeAdapter.processPayment(2200.0);
    }
}
