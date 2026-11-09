/**
 * Employee.java
 * Stores one employee's ID, name and role.
 * Valid roles: Manager, Designer, Accountant, Tailor, Salesperson.
 * Java 1.6 compatible.
 */
public class Employee {

    public static final String[] ROLES = {
        "Manager", "Designer", "Accountant", "Tailor", "Salesperson"
    };

    private String id;
    private String name;
    private String role;

    public Employee(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }

    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }

    /** Returns the canonical role name for a typed value (case-insensitive), or null if invalid. */
    public static String matchRole(String input) {
        if (input == null) {
            return null;
        }
        for (int i = 0; i < ROLES.length; i++) {
            if (ROLES[i].equalsIgnoreCase(input.trim())) {
                return ROLES[i];
            }
        }
        return null;
    }

    public void display() {
        System.out.printf("  %-8s %-24s %-12s%n", id, name, role);
    }
}
