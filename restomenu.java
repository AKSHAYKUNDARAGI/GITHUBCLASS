import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.text.ParseException;


class Itemsm {
    private int id;
    private String name;
    private double price;

    public Itemsm(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class takingoreder {
    private int orderId;
    private List<Itemsm> items;
    private Date orderDate;
    private double totalAmount;
    private boolean isCancelled;

    public takingoreder(int orderId, List<Itemsm> items) {
        this.orderId = orderId;
        this.items = items;
        this.orderDate = new Date();
        this.totalAmount = calculateTotalAmount();
        this.isCancelled = false;
    }

    public void cancelOrder() {
        this.isCancelled = true;
    }

    private double calculateTotalAmount() {
        return items.stream().mapToDouble(Itemsm::getPrice).sum();
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Itemsm> getItems() {
        return items;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
     public boolean isCancelled() {
        return isCancelled;
    }
}

class CollectionReport {
    private Date date;
    private double totalCollection;

    public CollectionReport(Date date, double totalCollection) {
        this.date = date;
        this.totalCollection = totalCollection;
    }

    
     public Date getDate() {
        return date;
    }

    public double getTotalCollection() {
        return totalCollection;
    }
}

class Managingfile {
    private static final String MENU_FILE = "menu.csv";
    private static final String ORDERS_FILE = "orders.csv";
    private static final String COLLECTION_REPORT_FILE = "collection_report.csv";

    public static List<Itemsm> loadMenu() {
        
        return new ArrayList<>();
    }

    public static List<takingoreder> loadOrders() {
        
        return new ArrayList<>();
    }

    public static List<CollectionReport> loadCollectionReport() {
        
        return new ArrayList<>();
    }

    public static void saveOrders(List<takingoreder> orders) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ORDERS_FILE))) {
            for (takingoreder order : orders) {
                writer.println(order.getOrderId() + "," + order.getOrderDate() + "," + order.getTotalAmount() + "," + order.isCancelled());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveCollectionReport(CollectionReport report) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COLLECTION_REPORT_FILE, true))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(report.getDate());
            writer.println(formattedDate + "," + report.getTotalCollection());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

public class restomenu {
    private static List<Itemsm> menu;
    private static List<takingoreder> orders;
    private static List<CollectionReport> collectionReports;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu = Managingfile.loadMenu();
        orders = Managingfile.loadOrders();
        collectionReports = Managingfile.loadCollectionReport();
       
        
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    placeOrder(scanner);
                    break;
                case 2:
                    cancelOrder(scanner);
                    break;
                case 3:
                    generateCollectionReport();
                    break;
                case 4:
                    System.out.println("Exiting the application.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("Quick-Bites Restaurant Management");
        System.out.println("1. Place takingoreder");
        System.out.println("2. Cancel takingoreder");
        System.out.println("3. Generate Collection Report");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

   private static void placeOrder(Scanner scanner) {
    System.out.println("Menu Items:");
    for (Itemsm item : menu) {
        System.out.println(item.getId() + ". " + item.getName() + " - $" + item.getPrice());
    }
    
    System.out.print("Enter the IDs of menu items (comma-separated): ");
    String input = scanner.next();
    String[] itemIds = input.split(",");

    List<Itemsm> orderedItems = new ArrayList<>();
    double totalAmount = 0.0;

    for (String itemId : itemIds) {
        int id = Integer.parseInt(itemId);
        Itemsm Itemsm = menu.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
        if (Itemsm != null) {
            orderedItems.add(Itemsm);
            totalAmount += Itemsm.getPrice();
        }
    }

    takingoreder order = new takingoreder(orders.size() + 1, orderedItems);
    orders.add(order);
    Managingfile.saveOrders(orders);

    System.out.println("takingoreder placed successfully!");
}

private static void cancelOrder(Scanner scanner) {
    System.out.print("Enter the order ID to cancel: ");
    int orderId = scanner.nextInt();

    takingoreder orderToCancel = orders.stream().filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);

    if (orderToCancel != null && !orderToCancel.isCancelled()) {
        orderToCancel.cancelOrder();
        Managingfile.saveOrders(orders);
        System.out.println("takingoreder cancelled successfully.");
    } else {
        System.out.println("takingoreder not found or already cancelled.");
    }
}


   private static void generateCollectionReport() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    System.out.print("Enter the date (yyyy-MM-dd) for the collection report: ");
    String dateString = scanner.next();

    try {
        Date selectedDate = sdf.parse(dateString);
        double totalCollection = orders.stream()
            .filter(order -> !order.isCancelled() && sdf.format(order.getOrderDate()).equals(dateString))
            .mapToDouble(takingoreder::getTotalAmount)
            .sum();

        CollectionReport report = new CollectionReport(selectedDate, totalCollection);
        collectionReports.add(report);
        Managingfile.saveCollectionReport(report);

        System.out.println("Collection report for " + dateString + ": $" + totalCollection);
    } catch (ParseException e) {
        System.out.println("Invalid date format. Use yyyy-MM-dd.");
    }
}

}
