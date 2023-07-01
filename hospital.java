import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Visitor {
    String Name;
    String phonenumString;
    String email;
    private String DATEOFAPPONTMENT;
    private String TIMEOFAPPOINTMENT;

    // Constructor
    public Visitor(String Name, String phonenumString, String email) {
        this.Name = Name;
        this.phonenumString = phonenumString;
        this.email = email;
        this.DATEOFAPPONTMENT = "";
        this.TIMEOFAPPOINTMENT = "";
    }

    // Getter and Setter methods
    public String getName() {
        return Name;
    }

    public String getphonenumString() {
        return phonenumString;
    }

    public String getemail() {
        return email;
    }

    public String getDATEOFAPPONTMENT() {
        return DATEOFAPPONTMENT;
    }

    public void setDATEOFAPPONTMENT(String DATEOFAPPONTMENT) {
        this.DATEOFAPPONTMENT = DATEOFAPPONTMENT;
    }

    public String getTIMEOFAPPOINTMENT() {
        return TIMEOFAPPOINTMENT;
    }

    public void setTIMEOFAPPOINTMENT(String TIMEOFAPPOINTMENT) {
        this.TIMEOFAPPOINTMENT = TIMEOFAPPOINTMENT;
    }
}

class clinicmethod {
    private List<Visitor> visitors;

    public clinicmethod() {
        this.visitors = new ArrayList<>();
    }

    public void VISITORADD(Visitor visitor) {
        visitors.add(visitor);
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void editVisitorDetails(int index, String Name, String phonenumString, String email) {
        Visitor visitor = visitors.get(index);
        visitor.Name = Name;
        visitor.phonenumString = phonenumString;
        visitor.email = email;
    }

    public void APPOINTMENTSCHEDULE(String date) {
        System.out.println("Appointments for " + date + ":");
        for (Visitor visitor : visitors) {
            if (visitor.getDATEOFAPPONTMENT().equals(date)) {
                System.out.println("Name: " + visitor.getName());
                System.out.println("Time: " + visitor.getTIMEOFAPPOINTMENT());
                System.out.println("-----------------------");
            }
        }
    }

    public void bookAppointment(String Name, String phonenumString, String email, String date, String time) {
        Visitor visitor = new Visitor(Name, phonenumString, email);
        visitor.setDATEOFAPPONTMENT(date);
        visitor.setTIMEOFAPPOINTMENT(time);
        visitors.add(visitor);
        System.out.println("Appointment booked successfully!");
    }

    public void editAppointment(int index, String date, String time) {
        Visitor visitor = visitors.get(index);
        visitor.setDATEOFAPPONTMENT(date);
        visitor.setTIMEOFAPPOINTMENT(time);
        System.out.println("Appointment updated successfully!");
    }

    public void cancelAppointment(int index) {
        visitors.remove(index);
        System.out.println("Appointment canceled successfully!");
    }
}

public class hospital {
    public static void main(String[] args) {
        clinicmethod clinicmethod = new clinicmethod();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("----- Appointment Management System -----");
            System.out.println("1. View Visitors List");
            System.out.println("2. Add new Visitor");
            System.out.println("3. Edit Visitor Details");
            System.out.println("4. View Appointment Schedule for a Day");
            System.out.println("5. Book an Appointment");
            System.out.println("6. Edit/Cancel Appointment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("--- Visitors List --");
                    List<Visitor> visitors = clinicmethod.getVisitors();
                    for (int i = 0; i < visitors.size(); i++) {
                        Visitor visitor = visitors.get(i);
                        System.out.println("Visitor " + (i + 1) + ":");
                        System.out.println("Name: " + visitor.getName());
                        System.out.println("Contact Number: " + visitor.getphonenumString());
                        System.out.println("email: " + visitor.getemail());
                        System.out.println("Appointment Date: " + visitor.getDATEOFAPPONTMENT());
                        System.out.println("Appointment Time: " + visitor.getTIMEOFAPPOINTMENT());
                        System.out.println("-----------------------");
                    }
                    break;

                case 2:
                    System.out.println("----- Add new Visitor -----");
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter Visitor Name: ");
                    String Name = scanner.nextLine();
                    System.out.print("Enter Contact Number: ");
                    String phonenumString = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    Visitor newVisitor = new Visitor(Name, phonenumString, email);
                    clinicmethod.VISITORADD(newVisitor);
                    System.out.println("Visitor added successfully!");
                    break;

                case 3:
                    System.out.println("----- Edit Visitor Details -----");
                    System.out.print("Enter the index of the visitor to edit: ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index < clinicmethod.getVisitors().size()) {
                        scanner.nextLine(); // Consume newline character
                        System.out.print("Enter Visitor Name: ");
                        Name = scanner.nextLine();
                        System.out.print("Enter Contact Number: ");
                        phonenumString = scanner.nextLine();
                        System.out.print("Enter email: ");
                        email = scanner.nextLine();
                        clinicmethod.editVisitorDetails(index, Name, phonenumString, email);
                        System.out.println("Visitor details updated successfully!");
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 4:
                    System.out.println("----- View Appointment Schedule for a Day -----");
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter the appointment date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    clinicmethod.APPOINTMENTSCHEDULE(date);
                    break;

                case 5:
                    System.out.println("----- Book an Appointment -----");
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter Visitor Name: ");
                    Name = scanner.nextLine();
                    System.out.print("Enter Contact Number: ");
                    phonenumString = scanner.nextLine();
                    System.out.print("Enter email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                    date = scanner.nextLine();
                    System.out.print("Enter Appointment Time (HH:MM): ");
                    String time = scanner.nextLine();
                    clinicmethod.bookAppointment(Name, phonenumString, email, date, time);
                    break;

                case 6:
                    System.out.println("----- Edit/Cancel Appointment -----");
                    System.out.print("Enter the index of the appointment to edit/cancel: ");
                    index = scanner.nextInt();
                    if (index >= 0 && index < clinicmethod.getVisitors().size()) {
                        scanner.nextLine(); // Consume newline character
                        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                        date = scanner.nextLine();
                        System.out.print("Enter Appointment Time (HH:MM): ");
                        time = scanner.nextLine();
                        clinicmethod.editAppointment(index, date, time);
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
            System.out.println();
        } while (choice != 0);

        scanner.close();
    }
}
