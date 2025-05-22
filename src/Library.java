import java.util.*;


public class Library {
    private List<Book> books = new ArrayList<>();
    private Map<Integer, User> usersMap = new HashMap<>();
    private Queue<User> waitList = new LinkedList<>();

    //for save data
    public Library() {
        this.books = DataManager.loadBooks();
        this.usersMap = DataManager.loadUsers();

        for (User user : usersMap.values()) {
            user.setLibrary(this);
        }
    }

    public void saveAll() {
        DataManager.saveBooks(books);
        DataManager.saveUsers(usersMap);
    }


    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
        saveAll();
    }

    public boolean removeBook(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId && book.isAvailable()) {
                books.remove(book);
                System.out.println("Book removed: " + book.getTitle());
                return true;
            }
        }
        System.out.println("Book not found or currently borrowed.");
        saveAll();
        return false;
    }

    public boolean borrow_Book(int userId, int bookId) {
        User user = usersMap.get(userId);
        if (user == null) {
            System.out.println("User not found");
            return false;
        }
        for (Book book : books) {
            if (book.getId() == bookId) {
                if (book.isAvailable()) {
                    //return user.borrowBook(book);
                    boolean success = user.borrowBook(book);
                    if (success) saveAll();
                    return success;
                } else {
                    waitList.add(user);
                    System.out.println("Book is not available. User added to waitlist.");
                    return false;
                }
            }
        }
        System.out.println("Book not found.");
        return false;
    }

    public boolean return_Book(int userId, int bookId) {
        User user = usersMap.get(userId);
        if (user == null) {
            System.out.println("User not found");
            return false;
        }
        for (Book book : books) {
            if (book.getId() == bookId) {
                if (user.returnBook(book)) {
                    if (!waitList.isEmpty()) {
                        User nextUser = waitList.poll();
                        nextUser.borrowBook(book);
                    }
                    saveAll();
                    return true;
                }
            }
        }
        //System.out.println("Book not found or not borrowed by user.");
        return false;
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("Library Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    public void searchBook(String str) {
        for (Book book : books) {
            if (book.getTitle().toLowerCase().startsWith(str.toLowerCase()) || String.valueOf(book.getId()).equals(str)) {
                System.out.println("Book found: " + book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void addUser(User user) {
        usersMap.put(user.getId(), user);
        saveAll();
        System.out.println("User added: " + user.getName());
    }

    public void showUsers() {
        if (usersMap.isEmpty()) {
            System.out.println("No users exists yet.");
        } else {
            System.out.println("Library Users:");
            for (User user : usersMap.values()) {
                System.out.println(user);
            }
        }
    }

    public User loginUser(int userId) {
        if (usersMap.containsKey(userId)) {
            return usersMap.get(userId);
        } else {
            //System.out.println("Invalid User ID. Please try again.");
            return null;
        }
    }

    public User existUser(int userId) {
        if (usersMap.containsKey(userId)) {
            return usersMap.get(userId);
        } else {
            return null;
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public boolean bookIdExists(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId) return true;
        }
        return false;
    }
}
