import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class User {
    private int id;
    private String name;
    private transient Library library;
    private List<Book> borrowedBooks = new ArrayList<>();


    public User(int id, String name, Library library) {
        this.id = id;
        this.name = name;
        this.library = library;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean borrowBook(Book book) {
        if (book.isAvailable()) {
            borrowedBooks.add(book);
            book.setAvailable(false);
            System.out.println(name + " borrowed: " + book.getTitle());
            return true;
        } else {
            System.out.println("Sorry, this book is not available.");
            return false;
        }
    }

    public boolean returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setAvailable(true);
            System.out.println(name + " returned: " + book.getTitle());
            return true;
        } else {
            System.out.println("This book is not borrowed by " + name);
            return false;
        }
    }

    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println(name + " has no borrowed books.");
        } else {
            System.out.println(name + "'s Borrowed Books:");
            for (Book book : borrowedBooks) {
                System.out.println(book);
            }
        }
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Name: " + name;
    }

    public void userMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n========= User Menu =========");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Show Borrowed Books");
            System.out.println("4. Show All Books");
            System.out.println("5. Search for a Book");
            System.out.println("6. Logout");
            System.out.println("=============================");

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
                    int borrowId = 0;
                    while (true) {
                        System.out.print("Enter Book ID to borrow: ");
                        try {
                            borrowId = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                            scanner.nextLine();
                        }
                    }
                    library.borrow_Book(id, borrowId);
                    break;

                case 2:
                    int returnId = 0;
                    while (true) {
                        System.out.print("Enter Book ID to return: ");
                        try {
                            returnId = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                            scanner.nextLine();
                        }
                    }
                    library.return_Book(id, returnId);
                    break;

                case 3:
                    if (borrowedBooks.isEmpty()) {
                        System.out.println(name + " has no borrowed books.");
                    } else {
                        System.out.println(name + "'s Borrowed Books:");
                        for (Book book : borrowedBooks) {
                            System.out.println(book);
                        }
                    }
                    break;

                case 4:
                    library.showBooks();
                    break;

                case 5:
                    System.out.print("Enter Book Title or ID to search: ");
                    String searchQuery = scanner.nextLine();
                    library.searchBook(searchQuery);
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