package src;

import model.Book;
import service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	System.out.println("\u001B[36m\t LIBRARY MANAGEMENT SYSTEM \u001B[0m"); // Cyan
        	System.out.println("\u001B[33m1.\u001B[0m Add Book");
        	System.out.println("\u001B[33m2.\u001B[0m View All Books");
        	System.out.println("\u001B[33m3.\u001B[0m Search Book by ID");
        	System.out.println("\u001B[33m4.\u001B[0m Update Book");
        	System.out.println("\u001B[33m5.\u001B[0m Delete Book");
        	System.out.println("\u001B[33m6.\u001B[0m Exit");
        	System.out.print("\u001B[32mEnter your choice: \u001B[0m"); // Green


            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Year: ");
                    int year = Integer.parseInt(scanner.nextLine());

                    Book book = new Book(id, title, author, year);
                    libraryService.addBook(book);
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    List<Book> books = libraryService.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("No books found.");
                    } else {
                        for (Book b : books) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Book ID to search: ");
                    int searchId = Integer.parseInt(scanner.nextLine());
                    Book found = libraryService.searchBookById(searchId);
                    if (found != null) {
                        System.out.println("Found: " + found);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter ID of book to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    Book existing = libraryService.searchBookById(updateId);
                    if (existing == null) {
                        System.out.println("Book not found.");
                        break;
                    }

                    System.out.print("Enter new Title: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new Author: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter new Year: ");
                    int newYear = Integer.parseInt(scanner.nextLine());

                    Book updatedBook = new Book(updateId, newTitle, newAuthor, newYear);
                    if (libraryService.updateBook(updatedBook)) {
                        System.out.println("Book updated successfully.");
                    } else {
                        System.out.println("Update failed.");
                    }
                    break;

                case 5:
                    System.out.print("Enter ID of book to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    if (libraryService.deleteBook(deleteId)) {
                        System.out.println("Book deleted successfully.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
