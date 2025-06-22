public class TestNotification {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();

        System.out.println("Sending notification with Email and SMS:");
        Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);
        smsNotifier.send("Hello World!");

        System.out.println("\nSending notification with Email and Slack:");
        Notifier slackNotifier = new SlackNotifierDecorator(emailNotifier);
        slackNotifier.send("Hello World!");

        System.out.println("\nSending notification with Email, SMS, and Slack:");
        Notifier fullNotifier = new SlackNotifierDecorator(
                                    new SMSNotifierDecorator(emailNotifier));
        fullNotifier.send("Hello World!");
    }
}
