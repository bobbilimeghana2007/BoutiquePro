/**
 * Product.java
 * Stores the details of one boutique product (ready-made garment, fabric or accessory).
 * Java 1.6 compatible.
 */
public class Product {

    private String id;
    private String name;
    private String category;
    private String gender;      // Men / Women / Kids
    private String type;        // Ready-made / Fabric / Accessories
    private String size;
    private String colour;
    private double price;
    private int quantity;
    private int minStock;

    public Product() {
    }

    public Product(String id, String name, String category, String gender, String type,
                   String size, String colour, double price, int quantity, int minStock) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.gender = gender;
        this.type = type;
        this.size = size;
        this.colour = colour;
        this.price = price;
        this.quantity = quantity;
        this.minStock = minStock;
    }

    // ---------- Getters ----------
    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getGender() { return gender; }
    public String getType() { return type; }
    public String getSize() { return size; }
    public String getColour() { return colour; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getMinStock() { return minStock; }

    // ---------- Setters ----------
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setGender(String gender) { this.gender = gender; }
    public void setType(String type) { this.type = type; }
    public void setSize(String size) { this.size = size; }
    public void setColour(String colour) { this.colour = colour; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setMinStock(int minStock) { this.minStock = minStock; }

    /** Value of the stock on hand for this product. */
    public double getStockValue() {
        return price * quantity;
    }

    /** Clear inventory status of this product. */
    public String getStatus() {
        if (quantity == 0) {
            return "OUT OF STOCK";
        }
        if (quantity <= minStock) {
            return "LOW STOCK";
        }
        return "IN STOCK";
    }

    /** Prints the full details of the product as a card. */
    public void display() {
        System.out.println("  Product ID     : " + id);
        System.out.println("  Name           : " + name);
        System.out.println("  Category       : " + category);
        System.out.println("  Gender         : " + gender);
        System.out.println("  Type           : " + type);
        System.out.println("  Size           : " + size);
        System.out.println("  Colour         : " + colour);
        System.out.println("  Price          : Rs. " + String.format("%.2f", price));
        System.out.println("  Available Qty  : " + quantity);
        System.out.println("  Min Stock Level: " + minStock);
        System.out.println("  Status         : " + getStatus());
    }

    /** Prints the header line used for product tables. */
    public static void printTableHeader() {
        System.out.println(repeat("-", 104));
        System.out.printf("%-8s %-24s %-12s %-6s %-10s %10s %5s %5s  %-13s%n",
                "ID", "Name", "Type", "Size", "Colour", "Price", "Qty", "Min", "Status");
        System.out.println(repeat("-", 104));
    }

    /** Prints this product as one table row. */
    public void printTableRow() {
        System.out.printf("%-8s %-24s %-12s %-6s %-10s %10.2f %5d %5d  %-13s%n",
                fit(id, 8), fit(name, 24), fit(type, 12), fit(size, 6), fit(colour, 10),
                price, quantity, minStock, getStatus());
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

    private static String repeat(String s, int times) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < times; i++) {
            sb.append(s);
        }
        return sb.toString();
    }
}
