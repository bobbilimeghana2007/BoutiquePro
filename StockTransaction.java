import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * StockTransaction.java
 * Records one movement of stock: transaction ID, product ID,
 * type (STOCK-IN / STOCK-OUT), quantity and date/time.
 * Java 1.6 compatible.
 */
public class StockTransaction {

    public static final String STOCK_IN = "STOCK-IN";
    public static final String STOCK_OUT = "STOCK-OUT";

    private String transactionId;
    private String productId;
    private String type;
    private int quantity;
    private Date date;

    public StockTransaction(String transactionId, String productId, String type, int quantity) {
        this.transactionId = transactionId;
        this.productId = productId;
        this.type = type;
        this.quantity = quantity;
        this.date = new Date();
    }

    public String getTransactionId() { return transactionId; }
    public String getProductId() { return productId; }
    public String getType() { return type; }
    public int getQuantity() { return quantity; }
    public Date getDate() { return date; }

    public String getDateString() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);
    }

    public void printTableRow() {
        System.out.printf("%-8s %-8s %-10s %6d  %s%n",
                transactionId, productId, type, quantity, getDateString());
    }
}
