import java.util.Scanner;

/**
 * Main.java
 * Entry point. Shows the menu in a do-while loop and routes each choice
 * to InventoryManager using a switch statement.
 * Java 1.6 compatible.
 */
public class Main {

    private static void printMenu() {
        System.out.println();
        System.out.println("==============================================");
        System.out.println("   BOUTIQUE PRO - Smart Inventory Management");
        System.out.println("==============================================");
        System.out.println("   1. Add Product");
        System.out.println("   2. View All Products");
        System.out.println("   3. Edit Product");
        System.out.println("   4. Delete Product");
        System.out.println("   5. Add Stock");
        System.out.println("   6. Remove Stock");
        System.out.println("   7. Search Product");
        System.out.println("   8. Low Stock Products");
        System.out.println("   9. Out of Stock Products");
        System.out.println("  10. Record Sales and Generate Bill");
        System.out.println("  11. Sales Report");
        System.out.println("  12. Inventory Report");
        System.out.println("  13. Stock Transaction History");
        System.out.println("  14. Add Employee");
        System.out.println("  15. View Employees");
        System.out.println("  16. Exit");
        System.out.println("==============================================");
        System.out.print("Enter your choice (1-16): ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager(scanner);
        int choice = 0;

        System.out.println("Welcome to BOUTIQUE PRO. Sample products are loaded for demonstration.");
        System.out.println("(Data is kept in memory only and is cleared when you exit.)");

        do {
            printMenu();
            String input = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:  manager.addProduct(); break;
                case 2:  manager.viewAllProducts(); break;
                case 3:  manager.editProduct(); break;
                case 4:  manager.deleteProduct(); break;
                case 5:  manager.addStock(); break;
                case 6:  manager.removeStock(); break;
                case 7:  manager.searchProduct(); break;
                case 8:  manager.showLowStock(); break;
                case 9:  manager.showOutOfStock(); break;
                case 10: manager.recordSale(); break;
                case 11: manager.salesReport(); break;
                case 12: manager.inventoryReport(); break;
                case 13: manager.stockTransactionHistory(); break;
                case 14: manager.addEmployee(); break;
                case 15: manager.viewEmployees(); break;
                case 16:
                    System.out.println("\nThank you for using BOUTIQUE PRO. Goodbye!");
                    break;
                default:
                    System.out.println("  [ERROR] Invalid choice. Please enter a number from 1 to 16.");
            }
        } while (choice != 16);

        scanner.close();
    }
}
