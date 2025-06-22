public class ProxyPatternTest {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // Lazy loading, loads from remote only when display is called
        image1.display(); // Loads + Displays
        System.out.println();

        image1.display(); // Just Displays 
        System.out.println();

        image2.display(); // Loads + Displays
    }
}
