package service;

import model.Book;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private final String FILE_PATH = "data/books.txt";

    // Add a new book
    public void addBook(Book book) {
        FileUtil.writeBookToFile(book, FILE_PATH);
    }

    // View all books
    public List<Book> getAllBooks() {
        return FileUtil.readBooksFromFile(FILE_PATH);
    }

    // Search book by ID
    public Book searchBookById(int id) {
        List<Book> books = getAllBooks();
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null; // not found
    }

    // Update book by ID
    public boolean updateBook(Book updatedBook) {
        List<Book> books = getAllBooks();
        boolean found = false;

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == updatedBook.getId()) {
                books.set(i, updatedBook);
                found = true;
                break;
            }
        }

        if (found) {
            FileUtil.writeAllBooksToFile(books, FILE_PATH);
        }

        return found;
    }

    // Delete book by ID
    public boolean deleteBook(int id) {
        List<Book> books = getAllBooks();
        boolean removed = books.removeIf(book -> book.getId() == id);

        if (removed) {
            FileUtil.writeAllBooksToFile(books, FILE_PATH);
        }

        return removed;
    }
}
