public class Book implements Comparable<Book> {
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;

    public Book(int id, String title, String author, String genre, boolean isAvailable){
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = isAvailable;
    }
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available){
        this.isAvailable = available;
    }
    @Override
    public int compareTo(Book obj){
        return this.title.compareTo(obj.title);
    }
    @Override
    public String toString(){
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author +
                ", Genre: " + genre + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}
