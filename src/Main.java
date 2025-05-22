import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        Admin admin = new Admin(library);

        while (true) {
            System.out.println("========= Main Menu =========");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as User");
            System.out.println("3. Exit");
            System.out.println("==================================");

            int choice = 0;
            boolean validInput = false;
            while (!validInput) {
                System.out.print("Enter choice: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine(); // clear invalid input
                }
            }
            switch (choice) {
                case 1:
                    System.out.println("Admin logged in.");
                    admin.adminMinu(scanner);
                    break;
                case 2:
                    int userId = 0;
                    validInput = false;
                    while (!validInput) {
                        System.out.print("Enter your Id: ");
                        try {
                            userId = scanner.nextInt();
                            scanner.nextLine();
                            validInput = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                            scanner.nextLine();
                        }
                    }
                    User user = library.loginUser(userId);
                    if (user != null) {
                        user.userMenu(scanner);
                    } else {
                        System.out.println("User not found. Please try again.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
