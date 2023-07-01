import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    private String productname;
    private String pspecs;
    private double cost;
    private int productcounts;

    public Product(String productname, String pspecs, double cost, int productcounts) {
        this.productname = productname;
        this.pspecs = pspecs;
        this.cost = cost;
        this.productcounts = productcounts;
    }

    // Getters and Setters
    public String getName() {
        return productname;
    }

    public void setName(String productname) {
        this.productname = productname;
    }

    public String getSpecification() {
        return pspecs;
    }

    public void setSpecification(String pspecs) {
        this.pspecs = pspecs;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCount() {
        return productcounts;
    }

    public void setCount(int productcounts) {
        this.productcounts = productcounts;
    }
}

class managementsys {
    private List<Product> products;

    public managementsys() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void showproducts() {
        System.out.println("Product List:");
        for (Product product : products) {
            System.out.println(product.getName());
        }
    }

    public void showproductcounts(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                System.out.println("Product Count: " + product.getCount());
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public void showDetails(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                System.out.println("Product Specification: " + product.getSpecification());
                System.out.println("Product Cost: " + product.getCost());
                System.out.println("Product Count: " + product.getCount());
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public void editProduct(String productName, String pspecs, double cost) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.setSpecification(pspecs);
                product.setCost(cost);
                System.out.println("Product details updated successfully!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    public void updateInventory(String productName, int quantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                int newCount = product.getCount() + quantity;
                product.setCount(newCount);
                System.out.println("Inventory updated successfully!");
                return;
            }
        }
        System.out.println("Product not found!");
    }
}

public class electronicsel {
    public static void main(String[] args) {
        managementsys systemobj = new managementsys();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nInventory Management System Menu:");
            System.out.println("1. Product List");
            System.out.println("2. Product Count");
            System.out.println("3. View Product Details");
            System.out.println("4. Edit Product");
            System.out.println("5. Update Inventory");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    systemobj.showproducts();
                    break;
                case 2:
                    System.out.print("Enter product productname: ");
                    String pname = scanner.nextLine();
                    systemobj.showproductcounts(pname);
                    break;
                case 3:
                    System.out.print("Enter product productname: ");
                    String pdetails = scanner.nextLine();
                    systemobj.showDetails(pdetails);
                    break;
                case 4:
                    System.out.print("Enter product productname: ");
                    String pedit = scanner.nextLine();
                    System.out.print("Enter product pspecs: ");
                    String pspecs = scanner.nextLine();
                    System.out.print("Enter product cost: ");
                    double cost = scanner.nextDouble();
                    systemobj.editProduct(pedit, pspecs, cost);
                    break;
                case 5:
                    System.out.print("Enter product productname: ");
                    String pupdate = scanner.nextLine();
                    System.out.print("Enter quantity to add/delete: ");
                    int quantity = scanner.nextInt();
                    systemobj.updateInventory(pupdate, quantity);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}
