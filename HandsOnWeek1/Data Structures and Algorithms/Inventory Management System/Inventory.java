import java.util.HashMap;

public class Inventory {
    private HashMap<String, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public void updateProduct(String productId, Product updatedProduct) {
        products.put(productId, updatedProduct);
    }

    public void deleteProduct(String productId) {
        products.remove(productId);
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    @Override
    public String toString() {
        if (products.isEmpty()) {
            return "Inventory is empty.";
        }

        StringBuilder sb = new StringBuilder("Inventory List:\n");
        for (Product p : products.values()) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }
}
