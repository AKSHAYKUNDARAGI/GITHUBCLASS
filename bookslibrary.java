import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class bookslibrary{
    private static int maxsizeofbooks = 5; 
    private static int attributenumber = 5; 
    private static int idindex = 0;  
    private static int indexstatus = 3; 
    private static int issueindex = 4; 

    private static int totaldays = 7;
    private static double LCHARGE = 10.0; 
    public static String[][] bookslib = new String[maxsizeofbooks][attributenumber]; 
    private static int noofbooks = 5; 

    public static void main(String[] args) {
        addingbooktolib();
        menudetails();
    }

     static void menudetails() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("Welcome to the Unique Library");
            System.out.println("--------------------------------------------------");
            System.out.println("1. View the complete list of Books");
            System.out.println("2. Issue a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(System.console().readLine());
            System.out.println();

            switch (choice) {
                case 1:
                    showbook();
                    break;
                case 2:
                    issuebook();
                    break;
                case 3:
                    giveback();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting the Application. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    
    public static void showbook() {
        if (noofbooks == 0) {
            System.out.println("No books in the bookslib.");
        } else {
            System.out.println("Book id\tTitle\tAuthor\tAvailability\tIssue Date");
            for (int i = 0; i < noofbooks; i++) {
                for (int j = 0; j < attributenumber; j++) {
                    System.out.print(bookslib[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }
    public static void addingbooktolib(){
            bookslib[0][0] = "111";
            bookslib[0][1] = "maths1";
            bookslib[0][2] = "XXXX";
            bookslib[0][3] = "Available";
            bookslib[0][4] = "";

            bookslib[1][0] = "222";
            bookslib[1][1] = "maths2";
            bookslib[1][2] = "XXXX";
            bookslib[1][3] = "Not-Available";
            bookslib[1][4] = "31-09-2023";

            bookslib[2][0] = "333";
            bookslib[2][1] = "maths3";
            bookslib[2][2] = "XXXX";
            bookslib[2][3] = "Available";
            bookslib[2][4] = "";

            bookslib[3][0] = "444";
            bookslib[3][1] = "maths4";
            bookslib[3][2] = "XXXX";
            bookslib[3][3] = "Not-Available";
            bookslib[3][4] = "01-01-2023";

            bookslib[4][0] = "555";
            bookslib[4][1] = "maths5";
            bookslib[4][2] = "XXXX";
            bookslib[4][3] = "Available";
            bookslib[4][4] = "";
}
    private static void issuebook() {
        if (noofbooks == 0) {
            System.out.println("No books in the bookslib.");
        } else {
            System.out.print("Enter the Book id to issuebook: ");
            String id = System.console().readLine();
            boolean bookpresent = false;

            for (int i = 0; i < noofbooks; i++) {
                if (bookslib[i][idindex].equals(id)) {
                    if (bookslib[i][indexstatus].equals("available")) {
                        System.out.print("Enter the Student id: ");
                        String rollnumber = System.console().readLine();
                        bookslib[i][indexstatus] = rollnumber;
                        bookslib[i][issueindex] = LocalDate.now().toString();
                        System.out.println("Book issued successfully.");
                    } else {
                        System.out.println("Book is already issued to another student.");
                    }
                    bookpresent = true;
                    break;
                }
            }

            if (!bookpresent) {
                System.out.println("Book not found in the bookslib.");
            }
        }
    }

    private static void giveback() {
        if (noofbooks == 0) {
            System.out.println("No books in the bookslib.");
        } else {
            System.out.print("Enter the Book id to return: ");
            String id = System.console().readLine();
            boolean bookpresent = false;

            for (int i = 0; i < noofbooks; i++) {
                if (bookslib[i][idindex].equals(id)) {
                    if (!bookslib[i][indexstatus].equals("available")) {
                        LocalDate dateofrcv = LocalDate.parse(bookslib[i][issueindex]);
                        LocalDate presentdate = LocalDate.now();
                        long gap = ChronoUnit.DAYS.between(dateofrcv, presentdate);

                        if (gap <= totaldays) {
                            bookslib[i][indexstatus] = "available";
                            bookslib[i][issueindex] = "Null";
                            System.out.println("Book returned successfully.");
                        } else {
                            double LCHARGEs = (gap - totaldays) * LCHARGE;
                            System.out.println("Book is returned late. Late charges: Rs. " + LCHARGEs);
                            bookslib[i][indexstatus] = "available";
                            bookslib[i][issueindex] = "Null";
                        }
                    } else {
                        System.out.println("Book is not currently issued to any student.");
                    }
                    bookpresent = true;
                    break;
                }
            }

            if (!bookpresent) {
                System.out.println("Book not found in the bookslib.");
            }
        }
    }
}
