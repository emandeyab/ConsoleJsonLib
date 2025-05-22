# ConsoleJsonLib (Java Console App)

ConsoleJsonLib is a simple Library Management System implemented in **Java** using **OOP principles**, **Java Collections** and **JSON-based file storage** to store data persistently. The system supports interaction for both **administrators** and **users**, allowing full control over books and borrowing functionality via a command-line interface.

---

## Features

### Authentication & User Management
- Add new users with a unique ID and name.
- User login with ID.
- Each user has a list of borrowed books.
- Handles invalid user input safely.

### Book Management
- Add new books with ID, title, author, genre, and availability status.
- Remove books (only if it exists or it not currently borrowed).
- Search for a book by full **title** or partial **starting characters** or **ID**.
- Display all books in the library with details.

### Borrowing & Returning System
- Users can borrow available books.
- If the book is unavailable, user is added to a **waiting list**.
- Returning a book automatically assigns it to the next user in the waiting list.
- Prevents double borrowing or returning non-borrowed books.

### JSON-Based Data Persistence
- Data (books and users) are saved to and loaded from **JSON files**.
- When the program starts, it loads existing data from `.json` files.
- All actions (add/remove/borrow/return) update the files automatically.

### Console-Based Menu System
- Easy-to-navigate menu for both users and admin.
- Input validation to handle unexpected or wrong data types.
- Clear feedback messages after each action.


