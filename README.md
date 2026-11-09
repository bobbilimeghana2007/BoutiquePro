# 🛍️ BoutiquePro – Boutique Inventory Management System

A Core Java-based console application designed to simplify boutique inventory management, stock tracking, employee management, and sales recording.

---

## 📌 1. Project Overview

**BoutiquePro** is a simple and user-friendly inventory management system developed specifically for small and medium-sized boutique businesses.

A boutique manages different types of products, such as sarees, kurtis, dresses, shirts, fabrics, and other fashion materials. Managing these products manually can become difficult when the number of products and daily transactions increases.

BoutiquePro provides a centralized system to manage product information, monitor available stock, record stock additions and removals, identify low-stock products, and maintain sales records.

The main purpose of this project is to reduce manual work, improve stock accuracy, and make boutique inventory management easier.

**Project Name:** BoutiquePro  
**Project Type:** Inventory Management System  
**Programming Language:** Java  
**Technology:** Core Java  
**Application Type:** Console-Based Application  
**Data Structure:** ArrayList  
**Execution:** Offline / Local System

---

## 🎯 2. Project Objectives

The main objectives of BoutiquePro are:

1. To maintain product details in a systematic manner.
2. To add, view, edit, and delete boutique products.
3. To track available stock quantities.
4. To record stock additions and removals.
5. To set minimum stock levels for each product.
6. To identify products that require restocking.
7. To prevent stock quantities from becoming negative.
8. To record sales and generate simple bills.
9. To maintain sales and stock transaction history.
10. To provide basic employee information management.

---

## 👥 3. Who Can Use This System?

BoutiquePro is designed around the activities of a boutique business.

| User / Role | Responsibility |
|---|---|
| Boutique Manager | Monitors overall inventory and business activities |
| Designer | Handles clothing designs and design-related materials |
| Tailor | Uses fabrics and other tailoring materials |
| Salesperson | Records product sales and customer purchases |
| Accountant | Reviews sales and inventory reports |

**Note:** The current version provides basic employee record management. Separate employee logins and role-based permissions are not implemented.

---

## 👗 4. Types of Products Managed

The system can maintain different categories of boutique products.

### Ready-Made Clothing
- Sarees
- Kurtis
- Dresses
- Shirts
- Trousers
- Traditional wear
- Western wear

### Fabrics and Materials
- Cotton fabric
- Silk fabric
- Linen fabric
- Thread
- Lace
- Buttons
- Embroidery materials

### Product Information

Each product can contain the following information:

| Field | Description |
|---|---|
| Product ID | Unique identification number |
| Product Name | Name of the product |
| Category | Type of boutique product |
| Gender | Men, Women, or Unisex |
| Type | Ready-made clothing or material |
| Size | Product size |
| Colour | Product colour |
| Price | Price per unit |
| Quantity | Available stock |
| Minimum Stock | Minimum quantity required |

---

## ✨ 5. Main Features

### 5.1 Product Management

The system allows users to manage boutique products.

**Add Product**
- Enter a unique product ID.
- Enter the product name.
- Select the category and product type.
- Enter gender, size, and colour.
- Enter product price and available quantity.
- Set the minimum stock level.

**View Products**

Displays all available products with their details.

**Edit Product**

Allows users to update existing product information.

**Delete Product**

Removes a product from the current inventory.

### 5.2 Stock Management

Stock management helps the boutique maintain accurate product quantities.

**Add Stock**
- Used when new products or materials arrive.
- Increases the available quantity.
- Records the stock addition in transaction history.

**Remove Stock**
- Used when stock is removed or materials are used.
- Decreases the available quantity.
- Records the stock removal.

**Stock Validation**

The system checks whether sufficient stock is available before reducing the quantity.

For example:

Available Stock = 10  
Requested Removal = 15

Result: Stock removal is rejected because sufficient stock is not available.

### 5.3 Minimum Stock Alert

Every product can have a minimum stock threshold.

The system identifies products whose available quantity is below the minimum stock level.

Example:

| Product | Available Stock | Minimum Stock | Status |
|---|---:|---:|---|
| Cotton Saree | 3 | 10 | LOW STOCK |
| Silk Saree | 15 | 5 | IN STOCK |
| Party Wear Dress | 0 | 5 | OUT OF STOCK |

This feature helps boutique owners identify products that need restocking.

### 5.4 Product Search

Users can search for products using product IDs or product names.

### 5.5 Sales Management

The sales module allows users to record product sales.

It:
- Accepts the product ID.
- Accepts the quantity sold.
- Checks available stock.
- Calculates the total amount.
- Updates the inventory quantity.
- Generates a simple sales bill.
- Records the sale and stock transaction.

**Example:**

Product: Cotton Saree  
Quantity Sold: 2  
Price per Unit: ₹800

Total Amount = 2 × 800 = ₹1,600

### 5.6 Sales Report

Displays recorded sales information, including:
- Sale ID
- Product ID
- Product name
- Quantity sold
- Unit price
- Total amount
- Sale date

### 5.7 Inventory Report

Displays inventory-related information, including:
- Total number of products
- Available product quantities
- Low-stock products
- Out-of-stock products

### 5.8 Stock Transaction History

Maintains records of stock additions and removals.

Each transaction includes:
- Transaction ID
- Product ID
- Product name
- Transaction type
- Quantity
- Transaction date

### 5.9 Employee Management

Allows users to add and view employee details.

Employee information includes:
- Employee ID
- Employee name
- Employee role

---

## 🧠 6. Project Architecture

BoutiquePro follows a simple object-oriented programming structure.

Each Java class has a separate responsibility.

```text
                  BOUTIQUE PRO
                       |
                       v
                    Main.java
                       |
                       v
                InventoryManager.java
                       |
        +--------------+--------------+
        |              |              |
        v              v              v
    Product.java   Employee.java   Sale.java
                       |
                       v
               StockTransaction.java
```

### Explanation of Java Files

| File | Responsibility |
|---|---|
| Main.java | Displays the menu and accepts user choices |
| Product.java | Stores and manages product information |
| Employee.java | Stores employee details |
| Sale.java | Stores sales information |
| StockTransaction.java | Stores stock addition and removal records |
| InventoryManager.java | Contains the main inventory management operations |

### Object-Oriented Programming Concepts

**1. Classes and Objects**

The project uses separate classes to represent products, employees, sales, and stock transactions.

**2. Encapsulation**

Private variables and public getter/setter methods are used to access and update object information.

**3. Abstraction**

The InventoryManager class provides methods for inventory operations without requiring the user to manage the internal ArrayList implementation.

---

## 🗂️ 7. Project Folder Structure

```text
BoutiquePro/
│
├── Main.java
├── Product.java
├── Employee.java
├── Sale.java
├── StockTransaction.java
├── InventoryManager.java
│
├── README.md
└── .gitignore
```

---

## 🛠️ 8. Technologies Used

| Technology | Purpose |
|---|---|
| Java | Main programming language |
| Core Java | Application development |
| OOP | Organizing the application into classes |
| ArrayList | Temporary storage of application records |
| Scanner | Reading user input |
| Date | Recording transaction and sale dates |
| Command Prompt / Terminal | Compiling and executing the application |
| Git and GitHub | Version control and project hosting |

---

## 💻 9. System Requirements

### Hardware Requirements
- Computer or laptop
- Minimum 4 GB RAM recommended
- Keyboard and display

### Software Requirements
- Windows, Linux, or macOS
- Java Development Kit (JDK)
- Command Prompt or terminal
- Text editor or Java IDE

The source code is written using older Java-compatible syntax, targeting Java 6 compatibility. Test it with the actual JDK available on your system before claiming that a particular version has been verified.

---

## 🚀 10. How to Install and Run

### Step 1: Install Java

Install a suitable Java Development Kit.

Verify the installation:

```bash
java -version
javac -version
```

### Step 2: Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/BoutiquePro.git
```

Replace `YOUR-USERNAME` with your GitHub username.

### Step 3: Open the Project Folder

```bash
cd BoutiquePro
```

### Step 4: Compile the Java Files

```bash
javac *.java
```

### Step 5: Run the Application

```bash
java Main
```

The BoutiquePro main menu will appear in the terminal.

---

## 📋 11. Application Menu

The application provides the following menu options:

```text
========================================
          BOUTIQUE PRO
  BOUTIQUE INVENTORY MANAGEMENT SYSTEM
========================================

1.  Add Product
2.  View All Products
3.  Edit Product
4.  Delete Product
5.  Add Stock
6.  Remove Stock
7.  Search Product
8.  Low Stock Products
9.  Out of Stock Products
10. Record Sales and Generate Bill
11. Sales Report
12. Inventory Report
13. Stock Transaction History
14. Add Employee
15. View Employees
0.  Exit

Enter your choice:
```

---

## 🧪 12. Sample Working Scenario

Consider a boutique that sells cotton sarees.

### Adding a Product

```text
Product ID: P101
Product Name: Cotton Saree
Category: Saree
Gender: Women
Type: Ready Made
Size: Free Size
Colour: Blue
Price: 800
Quantity: 10
Minimum Stock: 5
```

The product is added to the inventory.

### Adding Stock

```text
Product ID: P101
Quantity to Add: 5

Stock added successfully.
Updated Quantity: 15
```

### Recording a Sale

```text
Product ID: P101
Quantity Sold: 2

Sale Successful!

Product: Cotton Saree
Quantity: 2
Price per Unit: ₹800
Total Amount: ₹1600
```

Updated stock = 13.

### Low Stock Example

If the available quantity becomes 3 and the minimum stock is 5, the system identifies the product as LOW STOCK.

---

## 🔒 13. Validation and Error Handling

BoutiquePro includes basic input validation.

- Prevents duplicate product IDs.
- Prevents negative product prices.
- Prevents negative stock quantities.
- Prevents stock removal beyond available quantity.
- Checks product availability before recording a sale.
- Handles invalid numeric input.
- Displays messages when a product is not found.

These checks help maintain correct inventory information.

---

## 📊 14. Advantages

1. Easy to use.
2. Reduces manual inventory work.
3. Helps track boutique products.
4. Provides stock availability information.
5. Identifies low-stock products.
6. Prevents negative inventory quantities.
7. Maintains basic sales records.
8. Supports basic employee information management.
9. Works offline.
10. Uses simple Core Java concepts.

---

## ⚠️ 15. Current Limitations

This is an educational, console-based project.

- Records are stored in ArrayLists in memory.
- Data is not permanently saved after the application closes.
- No database is connected.
- No graphical user interface is included.
- Employee login and role-based permissions are not implemented.
- Sales are recorded for individual products rather than multi-item shopping carts.
- No tax, discount, or payment gateway integration is included.

---

## 🔮 16. Future Enhancements

The project can be improved with the following features:

1. MySQL or MongoDB database integration.
2. Web-based dashboard using HTML, CSS, and JavaScript.
3. Java backend integration.
4. Employee login and role-based access.
5. Customer management.
6. Multiple-product billing.
7. GST and discount calculation.
8. Barcode and QR code scanning.
9. PDF invoice generation.
10. Automatic low-stock notifications.
11. Sales analytics and graphical reports.
12. Cloud-based data backup.

---

## 🎓 17. Learning Outcomes

By developing BoutiquePro, students can understand:

- Java programming fundamentals.
- Object-oriented programming.
- Classes and objects.
- Encapsulation.
- ArrayList operations.
- Conditional statements and loops.
- Methods and constructors.
- Exception handling.
- Basic business logic.
- Inventory and stock management concepts.
- Git and GitHub project management.

---

## 👩‍💻 18. Project Purpose

BoutiquePro is developed as a practical Java project to demonstrate how programming concepts can solve a real-world business problem.

It connects Core Java concepts with a practical boutique inventory use case and provides a foundation for building more advanced business management applications.

---

## 🤝 19. Contributing

Contributions and suggestions are welcome.

If you would like to improve this project:

1. Fork the repository.
2. Create a new branch.
3. Make your changes.
4. Commit your changes.
5. Submit a pull request.

---

## 📜 20. License

This project is currently provided for educational and learning purposes. A formal open-source license can be added separately if the project is intended for public reuse.

---

## ⭐ Support

If you find this project useful, consider giving the repository a star on GitHub!

**Built with Java ❤️ | BoutiquePro – Smart Boutique Inventory Management System**
