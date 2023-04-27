import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exceptions.BookNotFoundException;

public class Member {
    String name, id;
    ArrayList<Book> requested_books;

    Member(String id, String name) {
        this.name = name;
        this.id = id;
        requested_books = new ArrayList<Book>();
    }

    void requestBook(Book book) throws IOException {
        try {
            if (book.id.equals("-1"))
                throw new BookNotFoundException("This book is not available in the database");
            requested_books.add(book);

            String filepath = "src/files/books.csv";
            FileReader reader = new FileReader(filepath);
            // reader.read
            BufferedReader b_reader = new BufferedReader(reader);
            String line;
            String new_content = "";

            while ((line = b_reader.readLine()) != null) {
                String[] arr = line.split(",");
                if (!(arr[0].equals(book.id))) {
                    new_content += line + "\n";
                }
            }
            try (
                    FileWriter writer = new FileWriter(filepath);) {
                writer.write(new_content);
            } catch (IOException e) {
                System.out.println("IOEXpection: " + e.getStackTrace());
            }

            System.out.println("Book request Successful: " + book.name + "(" + book.id + ")");
        } catch (BookNotFoundException bne) {
            // System.out.println("Book request failed.");
            // System.out.println("Error: " + bne.getMessage());
        }
    }

    Book searchBook(String id) throws IOException, BookNotFoundException {
        String filepath = "src/files/books.csv";
        FileReader reader = new FileReader(filepath);
        BufferedReader b_reader = new BufferedReader(reader);
        String line;
        try {
            while ((line = b_reader.readLine()) != null) {
                String[] arr = line.split(",");
                if (arr[0].equals(id))
                    return new Book(arr[0], arr[1], arr[2], arr[3]);
            }
            throw new BookNotFoundException("This book is not available in the database");
        } catch (IOException e) {
            System.out.println("IOExpection error: " + e);
        } catch (BookNotFoundException bne) {
            System.out.println("Error: " + bne.getMessage());
        }
        return new Book("-1", "-1", "-1", "-1");
    }

    void returnBook(Book book) throws IOException, NullPointerException {
        String filepath = "src/files/books.csv";
        try (FileWriter writer = new FileWriter(filepath, true);) {
            String content = book.id + "," + book.name + "," + book.genre + "," + book.author + "\n";
            writer.write(content);
            requested_books.remove(book);
            System.out.println("Book Reture Successful: " + book.name + "(" + book.id + ")");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NullPointerException npe) {
            System.out.println("You don't have that book to return.");
        }
    }

    void listAllBooks() throws IOException {
        String filepath = "src/files/books.csv";
        FileReader reader = new FileReader(filepath);
        BufferedReader b_reader = new BufferedReader(reader);
        String line;
        try {
            while ((line = b_reader.readLine()) != null) {
                String[] arr = line.split(",");
                System.out.println(arr[0] + "," + arr[1] + "," + arr[2] + "," + arr[3]);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("IOExpection error: " + e);
        }
    }
}