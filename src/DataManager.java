import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class DataManager {
    private static final String BOOKS_FILE = "books.json";
    private static final String USERS_FILE = "users.json";
    private static final Gson gson = new Gson();

    // Load books from JSON file
    public static List<Book> loadBooks() {
        try (Reader reader = new FileReader(BOOKS_FILE)) {
            Type bookListType = new TypeToken<List<Book>>() {}.getType();
            List<Book> books = gson.fromJson(reader, bookListType);
            return books != null ? books : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Books data file not found, starting with empty list.");
            return new ArrayList<>();
        }
    }

    // Save books to JSON file
    public static void saveBooks(List<Book> books) {
        try (Writer writer = new FileWriter(BOOKS_FILE)) {
            gson.toJson(books, writer);
        } catch (IOException e) {
            System.out.println("Failed to save books data: " + e.getMessage());
        }
    }

    // Load users from JSON file
    public static Map<Integer, User> loadUsers() {
        try (Reader reader = new FileReader(USERS_FILE)) {
            Type userMapType = new TypeToken<Map<Integer, User>>() {}.getType();
            Map<Integer, User> users = gson.fromJson(reader, userMapType);
            return users != null ? users : new HashMap<>();
        } catch (IOException e) {
            System.out.println("Users data file not found, starting with empty map.");
            return new HashMap<>();
        }
    }

    // Save users to JSON file
    public static void saveUsers(Map<Integer, User> users) {
        try (Writer writer = new FileWriter(USERS_FILE)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            System.out.println("Failed to save users data: " + e.getMessage());
        }
    }
}
