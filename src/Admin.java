import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    private Library library;

    public Admin(Library library) {
        this.library = library;
    }

    public void adminMinu(Scanner scanner) {
        while (true) {
            System.out.println("========= Admin Menu =========");
            System.out.println("1. Add User");
            System.out.println("2. Add Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Display Books");
            System.out.println("5. Display Users");
            System.out.println("6. Logout");
            System.out.println("==================================");

            int choice = 0;
            while (true) {
                System.out.print("Enter choice: ");
                try {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a number.");
                    scanner.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    int userId = 0;
                    while (true) {
                        System.out.print("Enter User ID: ");
                        try {
                            userId = scanner.nextInt();
                            scanner.nextLine();
                            if (library.existUser(userId) != null) {
                                System.out.println("User ID already exists. Returning to menu.");
                                break;
                            }
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                            scanner.nextLine();
                        }
                    }
                    if (library.existUser(userId) == null) {
                        System.out.print("Enter User Name: ");
                        String userName = scanner.nextLine();
                        User newUser = new User(userId, userName, library);
                        library.addUser(newUser);
                    }
                    break;

                case 2:
                    int bookId = 0;
                    while (true) {
                        System.out.print("Enter Book ID: ");
                        try {
                            bookId = scanner.nextInt();
                            scanner.nextLine();
                            boolean exists = false;
                            for (Book b : library.getBooks()) {
                                if (b.getId() == bookId) {
                                    exists = true;
                                    break;
                                }
                            }
                            if (exists) {
                                System.out.println("Book ID already exists. Returning to menu.");
                                break;
                            }
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                            scanner.nextLine();
                        }
                    }
                    if (!library.bookIdExists(bookId)) {
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Enter Genre: ");
                        String genre = scanner.nextLine();
                        library.addBook(new Book(bookId, title, author, genre, true));
                    }
                    break;


                case 3:
                    int Id = 0;
                    while (true) {
                        System.out.print("Enter Book ID: ");
                        try {
                            Id = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                            scanner.nextLine();
                        }
                    }
                    library.removeBook(Id);
                    break;

                case 4:
                    library.showBooks();
                    break;

                case 5:
                    library.showUsers();
                    break;

                case 6:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
