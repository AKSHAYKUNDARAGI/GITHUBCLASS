import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class bookslibrary {
    private String libbookid;
    private String bookTitle;
    private String authorname;
    private String availability;
    private String dateOfIssue;

    public bookslibrary(String libbookid, String bookTitle, String authorname, String availability, String dateOfIssue) {
        this.libbookid = libbookid;
        this.bookTitle = bookTitle;
        this.authorname = authorname;
        this.availability = availability;
        this.dateOfIssue = dateOfIssue;
    }

    
    public String getBookID() {
        return libbookid;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return authorname;
    }

    public String getAvailability() {
        return availability;
    }

    public String getIssueDate() {
        return dateOfIssue;
    }
}

public class librarycatalog {

    private static final String file_name = "catalog_Lib.csv";

    public static void main(String[] args) {
        List<bookslibrary> catalog = new ArrayList<>();
        loadcatalog(catalog);

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    bookAdd(catalog, sc);
                    break;
                case 2:
                    catalogView(catalog);
                    break;
                case 3:
                    catalogsave(catalog);
                    break;
                case 4:
                    loadcatalog(catalog);
                    break;
                case 5:
                    System.out.println("..........exit........");
                    break;
                default:
                    System.out.println("Enter valid choice.");
                    break;
            }
        } while (choice != 5);
    }

    private static void displayMenu() {
        System.out.println("1. for a library add a book");
        System.out.println("2. catalog look");
        System.out.println("3. data given save it to catalog csv file");
        System.out.println("4. catalog load from csv");
        System.out.println("5. press if you want to exit from this");
        System.out.print("Enter any choice: ");
    }

    private static void bookAdd(List<bookslibrary> catalog, Scanner sc) {
        System.out.print("Enter book ID: ");
        String libbookid = sc.nextLine();
        System.out.print("Enter books Title: ");
        String bookTitle = sc.nextLine();
        System.out.print("Enter Author name: ");
        String authorname = sc.nextLine();
        System.out.print("Enter Availability: ");
        String availability = sc.nextLine();
        System.out.print("Enter Issue Date (or type Null if not issued): ");
        String dateOfIssue = sc.nextLine();

        catalog.add(new bookslibrary(libbookid, bookTitle, authorname, availability, dateOfIssue));
        System.out.println("added to books library successfully!");
    }

    private static void catalogView(List<bookslibrary> catalog) {
        System.out.println("\nCatalog:");
        for (bookslibrary book : catalog) {
            System.out.println("bookslibrary ID: " + book.getBookID());
            System.out.println("bookslibrary Title: " + book.getBookTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Availability: " + book.getAvailability());
            System.out.println("Issue Date: " + book.getIssueDate());
            System.out.println();
        }
    }

    private static void catalogsave(List<bookslibrary> catalog) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file_name))) {
            writer.println("bookslibrary ID,bookslibrary Title,Author,Availability,Issue Date");
            for (bookslibrary book : catalog) {
                String dateOfIssue = book.getIssueDate();
                if (dateOfIssue == null || dateOfIssue.isEmpty()) {
                    dateOfIssue = "null";
                }
                writer.println(book.getBookID() + "," +
                        book.getBookTitle() + "," +
                        book.getAuthor() + "," +
                        book.getAvailability() + "," +
                        dateOfIssue);
            }
            System.out.println("Catalog saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void loadcatalog(List<bookslibrary> catalog) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file_name))) {
            catalog.clear();
            String line;
            boolean headerSkipped = false;
            while ((line = reader.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; 
                }
                String[] data = line.split(",");
                if (data.length == 5) {
                    catalog.add(new bookslibrary(data[0], data[1], data[2], data[3], data[4]));
                }
            }
            System.out.println("Catalog loaded to CSV successfully!");
        } catch (IOException e) {
            System.out.println("Error loading catalog from CSV: " + e.getMessage());
        }
    }
}
