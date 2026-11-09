import java.util.ArrayList;
import java.util.Scanner;

/**
 * InventoryManager.java
 * Holds all data (ArrayLists) and implements every operation:
 * products, stock, low-stock, employees, sales/billing and reports.
 * Java 1.6 compatible (no diamond operator, no String switch, no try-with-resources).
 */
public class InventoryManager {

    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private ArrayList<Sale> sales = new ArrayList<Sale>();
    private ArrayList<StockTransaction> transactions = new ArrayList<StockTransaction>();

    private int saleCounter = 0;
    private int transactionCounter = 0;

    private Scanner in;

    public InventoryManager(Scanner in) {
        this.in = in;
        loadSampleData();
    }

    // ------------------------------------------------------------------
    // Sample data
    // ------------------------------------------------------------------
    private void loadSampleData() {
        products.add(new Product("P001", "Kanjivaram Silk Saree", "Saree", "Women", "Ready-made", "Free", "Maroon", 8500.00, 12, 4));
        products.add(new Product("P002", "Cotton Printed Kurti", "Kurti", "Women", "Ready-made", "M", "Teal", 749.00, 3, 5));
        products.add(new Product("P003", "Linen Formal Shirt", "Shirt", "Men", "Ready-made", "L", "White", 1299.00, 20, 6));
        products.add(new Product("P004", "Party Wear Dress", "Dress", "Women", "Ready-made", "S", "Navy", 2499.00, 0, 3));
        products.add(new Product("P005", "Pure Cotton Fabric (per m)", "Fabric", "Women", "Fabric", "NA", "Cream", 320.00, 85, 20));
        products.add(new Product("P006", "Kids Frock", "Frock", "Kids", "Ready-made", "6Y", "Pink", 899.00, 9, 4));
        products.add(new Product("P007", "Pearl Necklace Set", "Jewellery", "Women", "Accessories", "NA", "Ivory", 650.00, 2, 5));
        products.add(new Product("P008", "Leather Belt", "Belt", "Men", "Accessories", "34", "Brown", 499.00, 15, 5));

        employees.add(new Employee("E001", "Meera Nair", "Manager"));
        employees.add(new Employee("E002", "Arjun Rao", "Salesperson"));
        employees.add(new Employee("E003", "Sana Iqbal", "Designer"));
    }

    // ------------------------------------------------------------------
    // Input helpers (all validation lives here)
    // ------------------------------------------------------------------
    private String readText(String prompt) {
        String s = "";
        while (s.length() == 0) {
            System.out.print(prompt);
            s = in.nextLine().trim();
            if (s.length() == 0) {
                System.out.println("  [ERROR] This field cannot be empty.");
            }
        }
        return s;
    }

    /** Reads an integer >= 0. Rejects negatives and non-numbers. */
    private int readNonNegativeInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            try {
                int v = Integer.parseInt(s);
                if (v < 0) {
                    System.out.println("  [ERROR] Negative values are not allowed.");
                } else {
                    return v;
                }
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Please enter a valid whole number.");
            }
        }
    }

    /** Reads an integer > 0. */
    private int readPositiveInt(String prompt) {
        while (true) {
            int v = readNonNegativeInt(prompt);
            if (v > 0) {
                return v;
            }
            System.out.println("  [ERROR] Quantity must be greater than zero.");
        }
    }

    /** Reads a price >= 0. Rejects negatives and non-numbers. */
    private double readPrice(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            try {
                double v = Double.parseDouble(s);
                if (v < 0) {
                    System.out.println("  [ERROR] Price cannot be negative.");
                } else {
                    return v;
                }
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Please enter a valid price, e.g. 499.50");
            }
        }
    }

    /** Reads one choice from a fixed list; returns the canonical spelling. */
    private String readChoice(String label, String[] options) {
        StringBuffer shown = new StringBuffer();
        for (int i = 0; i < options.length; i++) {
            if (i > 0) {
                shown.append("/");
            }
            shown.append(options[i]);
        }
        while (true) {
            String s = readText(label + " (" + shown + "): ");
            for (int i = 0; i < options.length; i++) {
                if (options[i].equalsIgnoreCase(s)) {
                    return options[i];
                }
            }
            System.out.println("  [ERROR] Please choose one of: " + shown);
        }
    }

    private Product findById(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equalsIgnoreCase(id)) {
                return products.get(i);
            }
        }
        return null;
    }

    private Employee findEmployeeById(String id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equalsIgnoreCase(id)) {
                return employees.get(i);
            }
        }
        return null;
    }

    private String nextSaleId() {
        saleCounter++;
        return String.format("S%03d", saleCounter);
    }

    private String nextTransactionId() {
        transactionCounter++;
        return String.format("T%03d", transactionCounter);
    }

    private void line() {
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    // ------------------------------------------------------------------
    // MODULE 1: Product management
    // ------------------------------------------------------------------
    public void addProduct() {
        System.out.println("\n=== ADD PRODUCT ===");
        String id = readText("Product ID: ");
        if (findById(id) != null) {
            System.out.println("  [ERROR] A product with ID '" + id + "' already exists. Product not added.");
            return;
        }
        String name = readText("Product Name: ");
        String category = readText("Category (e.g. Saree, Kurti, Shirt): ");
        String gender = readChoice("Gender", new String[] {"Men", "Women", "Kids"});
        String type = readChoice("Product Type", new String[] {"Ready-made", "Fabric", "Accessories"});
        String size = readText("Size: ");
        String colour = readText("Colour: ");
        double price = readPrice("Price (Rs.): ");
        int qty = readNonNegativeInt("Available Quantity: ");
        int min = readNonNegativeInt("Minimum Stock Level: ");

        Product p = new Product(id, name, category, gender, type, size, colour, price, qty, min);
        products.add(p);
        if (qty > 0) {
            transactions.add(new StockTransaction(nextTransactionId(), id, StockTransaction.STOCK_IN, qty));
        }
        System.out.println("  [OK] Product '" + name + "' added successfully. Status: " + p.getStatus());
    }

    public void viewAllProducts() {
        System.out.println("\n=== ALL PRODUCTS ===");
        if (products.isEmpty()) {
            System.out.println("  No products available.");
            return;
        }
        Product.printTableHeader();
        for (int i = 0; i < products.size(); i++) {
            products.get(i).printTableRow();
        }
        System.out.println("Total products: " + products.size());
    }

    public void editProduct() {
        System.out.println("\n=== EDIT PRODUCT ===");
        String id = readText("Enter Product ID to edit: ");
        Product p = findById(id);
        if (p == null) {
            System.out.println("  [ERROR] No product found with ID '" + id + "'.");
            return;
        }
        System.out.println("Current details:");
        p.display();
        System.out.println("Press ENTER to keep the current value.");

        String s;
        System.out.print("Name [" + p.getName() + "]: ");
        s = in.nextLine().trim();
        if (s.length() > 0) { p.setName(s); }

        System.out.print("Category [" + p.getCategory() + "]: ");
        s = in.nextLine().trim();
        if (s.length() > 0) { p.setCategory(s); }

        System.out.print("Gender Men/Women/Kids [" + p.getGender() + "]: ");
        s = in.nextLine().trim();
        if (s.length() > 0) {
            String[] g = {"Men", "Women", "Kids"};
            boolean ok = false;
            for (int i = 0; i < g.length; i++) {
                if (g[i].equalsIgnoreCase(s)) { p.setGender(g[i]); ok = true; }
            }
            if (!ok) { System.out.println("  [ERROR] Invalid gender. Kept old value."); }
        }

        System.out.print("Type Ready-made/Fabric/Accessories [" + p.getType() + "]: ");
        s = in.nextLine().trim();
        if (s.length() > 0) {
            String[] t = {"Ready-made", "Fabric", "Accessories"};
            boolean ok = false;
            for (int i = 0; i < t.length; i++) {
                if (t[i].equalsIgnoreCase(s)) { p.setType(t[i]); ok = true; }
            }
            if (!ok) { System.out.println("  [ERROR] Invalid type. Kept old value."); }
        }

        System.out.print("Size [" + p.getSize() + "]: ");
        s = in.nextLine().trim();
        if (s.length() > 0) { p.setSize(s); }

        System.out.print("Colour [" + p.getColour() + "]: ");
        s = in.nextLine().trim();
        if (s.length() > 0) { p.setColour(s); }

        System.out.print("Price [" + p.getPrice() + "]: ");
        s = in.nextLine().trim();
        if (s.length() > 0) {
            try {
                double v = Double.parseDouble(s);
                if (v < 0) {
                    System.out.println("  [ERROR] Price cannot be negative. Kept old value.");
                } else {
                    p.setPrice(v);
                }
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Invalid price. Kept old value.");
            }
        }

        System.out.print("Minimum Stock Level [" + p.getMinStock() + "]: ");
        s = in.nextLine().trim();
        if (s.length() > 0) {
            try {
                int v = Integer.parseInt(s);
                if (v < 0) {
                    System.out.println("  [ERROR] Minimum stock cannot be negative. Kept old value.");
                } else {
                    p.setMinStock(v);
                }
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Invalid number. Kept old value.");
            }
        }
        System.out.println("  [OK] Product updated. Use 'Add Stock' / 'Remove Stock' to change quantity.");
    }

    public void deleteProduct() {
        System.out.println("\n=== DELETE PRODUCT ===");
        String id = readText("Enter Product ID to delete: ");
        Product p = findById(id);
        if (p == null) {
            System.out.println("  [ERROR] No product found with ID '" + id + "'.");
            return;
        }
        String c = readText("Delete '" + p.getName() + "'? (yes/no): ");
        if (c.equalsIgnoreCase("yes") || c.equalsIgnoreCase("y")) {
            products.remove(p);
            System.out.println("  [OK] Product deleted.");
        } else {
            System.out.println("  Deletion cancelled.");
        }
    }

    public void searchProduct() {
        System.out.println("\n=== SEARCH PRODUCT ===");
        String key = readText("Enter Product ID or part of the name: ").toLowerCase();
        ArrayList<Product> found = new ArrayList<Product>();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getId().toLowerCase().equals(key) || p.getName().toLowerCase().indexOf(key) >= 0) {
                found.add(p);
            }
        }
        if (found.isEmpty()) {
            System.out.println("  No products matched '" + key + "'.");
            return;
        }
        Product.printTableHeader();
        for (int i = 0; i < found.size(); i++) {
            found.get(i).printTableRow();
        }
        System.out.println(found.size() + " match(es) found.");
    }

    // ------------------------------------------------------------------
    // MODULE 2: Stock management
    // ------------------------------------------------------------------
    public void addStock() {
        System.out.println("\n=== ADD STOCK ===");
        String id = readText("Product ID: ");
        Product p = findById(id);
        if (p == null) {
            System.out.println("  [ERROR] No product found with ID '" + id + "'.");
            return;
        }
        System.out.println("  " + p.getName() + " - current stock: " + p.getQuantity());
        int qty = readPositiveInt("Quantity to add: ");
        p.setQuantity(p.getQuantity() + qty);
        transactions.add(new StockTransaction(nextTransactionId(), p.getId(), StockTransaction.STOCK_IN, qty));
        System.out.println("  [OK] Added " + qty + " unit(s). Remaining stock: " + p.getQuantity() + " (" + p.getStatus() + ")");
    }

    public void removeStock() {
        System.out.println("\n=== REMOVE STOCK ===");
        String id = readText("Product ID: ");
        Product p = findById(id);
        if (p == null) {
            System.out.println("  [ERROR] No product found with ID '" + id + "'.");
            return;
        }
        System.out.println("  " + p.getName() + " - current stock: " + p.getQuantity());
        int qty = readPositiveInt("Quantity to remove: ");
        if (qty > p.getQuantity()) {
            System.out.println("  [ERROR] Cannot remove " + qty + " unit(s); only " + p.getQuantity() + " available.");
            return;
        }
        p.setQuantity(p.getQuantity() - qty);
        transactions.add(new StockTransaction(nextTransactionId(), p.getId(), StockTransaction.STOCK_OUT, qty));
        System.out.println("  [OK] Removed " + qty + " unit(s). Remaining stock: " + p.getQuantity() + " (" + p.getStatus() + ")");
    }

    // ------------------------------------------------------------------
    // MODULE 3: Low stock management
    // ------------------------------------------------------------------
    public void showLowStock() {
        System.out.println("\n=== LOW STOCK PRODUCTS ===");
        int count = 0;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getQuantity() > 0 && p.getQuantity() <= p.getMinStock()) {
                if (count == 0) {
                    Product.printTableHeader();
                }
                p.printTableRow();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("  [OK] No products are low on stock.");
        } else {
            System.out.println(count + " product(s) need restocking soon.");
        }
    }

    public void showOutOfStock() {
        System.out.println("\n=== OUT OF STOCK PRODUCTS ===");
        int count = 0;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getQuantity() == 0) {
                if (count == 0) {
                    Product.printTableHeader();
                }
                p.printTableRow();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("  [OK] No products are out of stock.");
        } else {
            System.out.println(count + " product(s) are out of stock.");
        }
    }

    // ------------------------------------------------------------------
    // MODULE 4: Employee management
    // ------------------------------------------------------------------
    public void addEmployee() {
        System.out.println("\n=== ADD EMPLOYEE ===");
        String id = readText("Employee ID: ");
        if (findEmployeeById(id) != null) {
            System.out.println("  [ERROR] An employee with ID '" + id + "' already exists.");
            return;
        }
        String name = readText("Employee Name: ");
        String role = readChoice("Role", Employee.ROLES);
        employees.add(new Employee(id, name, role));
        System.out.println("  [OK] Employee '" + name + "' added as " + role + ".");
    }

    public void viewEmployees() {
        System.out.println("\n=== EMPLOYEES ===");
        if (employees.isEmpty()) {
            System.out.println("  No employees added yet.");
            return;
        }
        System.out.printf("  %-8s %-24s %-12s%n", "ID", "Name", "Role");
        System.out.println("  " + "--------------------------------------------");
        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).display();
        }
        System.out.println("Total employees: " + employees.size());
    }

    // ------------------------------------------------------------------
    // MODULE 5: Sales and billing
    // ------------------------------------------------------------------
    public void recordSale() {
        System.out.println("\n=== RECORD SALE & GENERATE BILL ===");
        String id = readText("Product ID: ");
        Product p = findById(id);
        if (p == null) {
            System.out.println("  [ERROR] No product found with ID '" + id + "'.");
            return;
        }
        if (p.getQuantity() == 0) {
            System.out.println("  [ERROR] '" + p.getName() + "' is out of stock.");
            return;
        }
        System.out.println("  " + p.getName() + " - Rs. " + String.format("%.2f", p.getPrice())
                + " - available: " + p.getQuantity());
        int qty = readPositiveInt("Quantity to sell: ");
        if (qty > p.getQuantity()) {
            System.out.println("  [ERROR] Only " + p.getQuantity() + " unit(s) available. Sale cancelled.");
            return;
        }
        String customer = readText("Customer name: ");

        p.setQuantity(p.getQuantity() - qty);
        transactions.add(new StockTransaction(nextTransactionId(), p.getId(), StockTransaction.STOCK_OUT, qty));
        Sale s = new Sale(nextSaleId(), p.getId(), p.getName(), qty, p.getPrice());
        sales.add(s);
        printBill(s, customer);
        if (p.getQuantity() <= p.getMinStock()) {
            System.out.println("  [ALERT] " + p.getName() + " is now " + p.getStatus() + " (" + p.getQuantity() + " left).");
        }
    }

    private void printBill(Sale s, String customer) {
        System.out.println();
        System.out.println("  ==========================================");
        System.out.println("               BOUTIQUE PRO");
        System.out.println("            Customer Invoice");
        System.out.println("  ==========================================");
        System.out.println("  Bill No  : " + s.getSaleId());
        System.out.println("  Date     : " + s.getDateString());
        System.out.println("  Customer : " + customer);
        System.out.println("  ------------------------------------------");
        System.out.println("  Item     : " + s.getProductName() + " (" + s.getProductId() + ")");
        System.out.println("  Quantity : " + s.getQuantity());
        System.out.println("  Price    : Rs. " + String.format("%.2f", s.getPrice()));
        System.out.println("  ------------------------------------------");
        System.out.println("  TOTAL    : Rs. " + String.format("%.2f", s.getTotalAmount()));
        System.out.println("  ==========================================");
        System.out.println("        Thank you for shopping with us!");
        System.out.println();
    }

    public void salesReport() {
        System.out.println("\n=== SALES REPORT ===");
        if (sales.isEmpty()) {
            System.out.println("  No sales recorded yet.");
            return;
        }
        System.out.printf("%-8s %-8s %-24s %4s %10s %12s  %s%n",
                "Sale", "Prod", "Name", "Qty", "Price", "Total", "Date");
        line();
        double revenue = 0;
        int units = 0;
        for (int i = 0; i < sales.size(); i++) {
            Sale s = sales.get(i);
            s.printTableRow();
            revenue += s.getTotalAmount();
            units += s.getQuantity();
        }
        line();
        System.out.println("Total sales: " + sales.size() + "   Units sold: " + units);
        System.out.println("TOTAL REVENUE: Rs. " + String.format("%.2f", revenue));
    }

    // ------------------------------------------------------------------
    // MODULE 6: Reports
    // ------------------------------------------------------------------
    public void inventoryReport() {
        System.out.println("\n=== INVENTORY REPORT ===");
        int totalQty = 0;
        double totalValue = 0;
        int low = 0;
        int out = 0;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            totalQty += p.getQuantity();
            totalValue += p.getStockValue();
            if (p.getQuantity() == 0) {
                out++;
            } else if (p.getQuantity() <= p.getMinStock()) {
                low++;
            }
        }
        System.out.println("  Total number of products : " + products.size());
        System.out.println("  Total available quantity : " + totalQty);
        System.out.println("  Total inventory value    : Rs. " + String.format("%.2f", totalValue));
        System.out.println("  Low-stock products       : " + low);
        System.out.println("  Out-of-stock products    : " + out);
        showLowStock();
        showOutOfStock();
    }

    public void stockTransactionHistory() {
        System.out.println("\n=== STOCK TRANSACTION HISTORY ===");
        if (transactions.isEmpty()) {
            System.out.println("  No stock transactions recorded yet.");
            return;
        }
        System.out.printf("%-8s %-8s %-10s %6s  %s%n", "Txn", "Product", "Type", "Qty", "Date");
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < transactions.size(); i++) {
            transactions.get(i).printTableRow();
        }
        System.out.println("Total transactions: " + transactions.size());
    }
}
