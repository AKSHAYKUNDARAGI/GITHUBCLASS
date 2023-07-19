import java.util.Scanner;
import java.util.*;

public class Electronicsstore{
    static Map<Integer, String> map = new HashMap<>();
    static Map<Integer, Integer> counterMap = new HashMap<>();
    static Map<Integer, Integer> costMap = new HashMap<>();
    static Map<Integer, String> specMap = new HashMap<>();

    public static void main(String[] args) {
        initializeData();

        System.out.println("--------------------------------");
        System.out.println("Welcome to the SmartPoint Electronics Store");
        System.out.println("--------------------------------");

        choosen();
    }

    public static void initializeData() {
        map.put(101, "Mobile");
        map.put(102, "Laptop");
        map.put(103, "Tablet");
        map.put(104, "Portable HDD");
        map.put(105, "Bluetooth Headphone");
        map.put(106, "Smart-watch");
        map.put(107, "Digital Camera");
        map.put(108, "Portable Power bank");
        map.put(109, "Printer");
        map.put(110, "Wireless Router");

        counterMap.put(101, 20);
        counterMap.put(102, 21);
        counterMap.put(103, 22);
        counterMap.put(104, 23);
        counterMap.put(105, 24);
        counterMap.put(106, 25);
        counterMap.put(107, 26);
        counterMap.put(108, 27);
        counterMap.put(109, 28);
        counterMap.put(110, 29);

        costMap.put(101, 200);
        costMap.put(102, 500);
        costMap.put(103, 300);
        costMap.put(104, 100);
        costMap.put(105, 50);
        costMap.put(106, 150);
        costMap.put(107, 250);
        costMap.put(108, 40);
        costMap.put(109, 80);
        costMap.put(110, 70);

        specMap.put(101, "iOS and Android");
        specMap.put(102, "Water Resistance");
        specMap.put(103, "Water Resistance");
        specMap.put(104, "Water Resistance");
        specMap.put(105, "Water Resistance");
        specMap.put(106, "Sleep monitoring, Step counting");
        specMap.put(107, "Battery Life - 2 days");
        specMap.put(108, "Water Resistance");
        specMap.put(109, "Water Resistance");
        specMap.put(110, "Water Resistance");
    }

    public static void choosen() {
        System.out.println("--------------------------------");
        System.out.println("Please choose an option from the below menu:");
        System.out.println("1. View the complete list of products");
        System.out.println("2. Check the available count for a specific product");
        System.out.println("3. View the specifications and details of a specific product");
        System.out.println("4. Modify the details of a specific product");
        System.out.println("5. Update the inventory for a specific product");
        System.out.println("6. Exit");

        Scanner sc = new Scanner(System.in);
        int d = sc.nextInt();

        switch (d) {
            case 1:
                System.out.println("--------------------------------");
                System.out.println("List of all Products\n");
                System.out.println("Product ID\tProduct Name");
                TreeMap<Integer, String> sorted = new TreeMap<>(map);

                // Display the TreeMap which is naturally sorted
                for (Map.Entry<Integer, String> entry : sorted.entrySet()) {
                    System.out.println(entry.getKey() + "\t\t" + entry.getValue());
                }
                choose();
                break;

            case 2:
                System.out.print("Enter the Product ID: ");
                int p = sc.nextInt();
                System.out.println("\n" + map.get(p));
                System.out.println("Total available count: " + counterMap.get(p));
                choose();
                break;

            case 3:
                System.out.print("Enter the Product ID: ");
                int pId = sc.nextInt();
                System.out.println("--------------------------------");
                System.out.println(map.get(pId));
                System.out.println("Cost of the product is: " + costMap.get(pId));
                System.out.println("Total available count: " + counterMap.get(pId));
                System.out.println("Compatibility: " + specMap.get(pId));
                choose();
                break;

            case 4:
                System.out.print("Enter the Product ID: ");
                int pd = sc.nextInt();
                System.out.println("--------------------------------");
                System.out.print("Enter New Cost: ");
                int newCost = sc.nextInt();
                costMap.put(pd, newCost);

                System.out.print("Enter Updated count: ");
                int newCount = sc.nextInt();
                counterMap.put(pd, newCount);

                sc.nextLine(); // Clear the input buffer
                System.out.print("Enter Updated Specifications: ");
                String newSpec = sc.nextLine();
                specMap.put(pd, newSpec);

                System.out.println("Updated Cost values: " + costMap.get(pd));
                System.out.println("Updated Count values: " + counterMap.get(pd));
                System.out.println("Updated Compatibility: " + specMap.get(pd));
                choose();
                break;

            case 5:
                System.out.print("Enter the Product ID: ");
                int prd = sc.nextInt();
                sc.nextLine(); // Clear the input buffer
                System.out.print("Please type Increase Or Decrease: ");
                String update = sc.nextLine();

                int value1 = costMap.get(prd);
                int value2 = counterMap.get(prd);

                if (update.equalsIgnoreCase("Increase")) {
                    costMap.put(prd, value1 + 1);
                    counterMap.put(prd, value2 + 1);
                } else if (update.equalsIgnoreCase("Decrease")) {
                    costMap.put(prd, value1 - 1);
                    counterMap.put(prd, value2 - 1);
                }

                System.out.println("Updated Cost values: " + costMap.get(prd));
                System.out.println("Updated Count values: " + counterMap.get(prd));
                choose();
                break;

            case 6:
                System.out.println("Thank you for choosing our store");
                System.exit(0);
                break;

            default:
                System.out.println("Not listed in the menu");
                choose();
        }
    }

    public static void choose() {
        System.out.println("--------------------------------");
        System.out.print("Enter Y to return to the main menu or N to Exit: ");
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);

        if (Character.compare(c, 'Y') == 0 || Character.compare(c, 'y') == 0) {
            System.out.println("--------------------------------");
            System.out.println("Welcome to the SmartPoint Electronics Store\n");
            choosen();
        } else if (Character.compare(c, 'N') == 0 || Character.compare(c, 'n') == 0) {
            System.out.println("Thank you for choosing our store");
            System.exit(0);
        }
    }
}