package util;

import model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    // Save one book to the file (append mode)
    public static void writeBookToFile(Book book, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String line = book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getYear();
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Read all books from the file
    public static List<Book> readBooksFromFile(String filePath) {
        List<Book> books = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) return books;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    books.add(new Book(id, title, author, year));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return books;
    }

    // Overwrite file with a full list of books (used for update/delete)
    public static void writeAllBooksToFile(List<Book> books, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                String line = book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getYear();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
