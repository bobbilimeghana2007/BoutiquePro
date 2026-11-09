import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Sale.java
 * Stores one sale: sale ID, product ID, product name, quantity, unit price,
 * total amount and the date/time of the sale.
 * Java 1.6 compatible.
 */
public class Sale {

    private String saleId;
    private String productId;
    private String productName;
    private int quantity;
    private double price;
    private double totalAmount;
    private Date date;

    public Sale(String saleId, String productId, String productName, int quantity, double price) {
        this.saleId = saleId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = price * quantity;
        this.date = new Date();
    }

    public String getSaleId() { return saleId; }
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public double getTotalAmount() { return totalAmount; }
    public Date getDate() { return date; }

    public String getDateString() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
    }

    public void printTableRow() {
        System.out.printf("%-8s %-8s %-24s %4d %10.2f %12.2f  %s%n",
                saleId, productId, fit(productName, 24), quantity, price, totalAmount, getDateString());
    }

    private static String fit(String s, int max) {
        if (s == null) {
            return "";
        }
        if (s.length() <= max) {
            return s;
        }
        return s.substring(0, max - 1) + ".";
    }
}
