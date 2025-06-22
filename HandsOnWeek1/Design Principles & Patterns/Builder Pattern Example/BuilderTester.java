public class BuilderTester {
    public static void main(String[] args) {
        Computer basicPC = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM("8GB")
                .build();

        Computer gamingPC = new Computer.Builder()
                .setCPU("AMD Ryzen 9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGraphicsCard("NVIDIA RTX 4070")
                .build();

        System.out.println("Basic PC:");
        System.out.println(basicPC);

        System.out.println("\nGaming PC:");
        System.out.println(gamingPC);
    }
}
