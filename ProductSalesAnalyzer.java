import java.util.*;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

public class ProductSalesAnalyzer {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Product1", 25.0),
            new Product("Product2", 75.0),
            new Product("Product3", 150.0),
            new Product("Product4", 80.0),
            new Product("Product5", 30.0),
            new Product("Product6", 120.0),
            new Product("Product7", 200.0)
        );

        double[] priceRanges = { 0, 50, 100, 200, Double.MAX_VALUE };
        Map<String, Integer> productCounts = new LinkedHashMap<>();
        Map<String, Double> revenueByRange = new LinkedHashMap<>();
        for (int i = 0; i < priceRanges.length - 1; i++) {
            productCounts.put(String.format("$%.2f-$%.2f", priceRanges[i], priceRanges[i+1]), 0);
            revenueByRange.put(String.format("$%.2f-$%.2f", priceRanges[i], priceRanges[i+1]), 0.0);
        }

        for (Product product : products) {
            double price = product.getPrice();
            for (int i = 0; i < priceRanges.length - 1; i++) {
                if (price >= priceRanges[i] && price < priceRanges[i+1]) {
                    int count = productCounts.get(String.format("$%.2f-$%.2f", priceRanges[i], priceRanges[i+1]));
                    productCounts.put(String.format("$%.2f-$%.2f", priceRanges[i], priceRanges[i+1]), count + 1);

                    double revenue = revenueByRange.get(String.format("$%.2f-$%.2f", priceRanges[i], priceRanges[i+1]));
                    revenueByRange.put(String.format("$%.2f-$%.2f", priceRanges[i], priceRanges[i+1]), revenue + price);
                    break;
                }
            }
        }
        for (Map.Entry<String, Integer> entry : productCounts.entrySet()) {
            String range = entry.getKey();
            int count = entry.getValue();
            double revenue = revenueByRange.get(range);
            System.out.println("Price Range: " + range + ", Products Sold: " + count + ", Total Revenue: $" + revenue);
        }
    }
}
