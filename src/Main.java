import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Bhusal Central Library!!!\nPlease Sign up or Login first.");
        Scanner sc = null;
        sc = new Scanner(System.in);
        System.out.print("Name: ");
        String member_name = sc.nextLine();
        System.out.print("ID: ");
        String member_id = sc.nextLine();
        // sc.close();
        Member user = new Member(member_id, member_name);
        boolean entry = true;
        while (entry) {
            String[] guide = { "Book Operations: ",
                    "List all Books",
                    "Book Search",
                    "Book Request",
                    "Book Return",
                    "Books with me",
                    "EXIT" };

            System.out.println(guide[0]);
            for (int i = 1; i < guide.length; i++) {
                System.out.println(i + ". " + guide[i]);
            }
            sc = new Scanner(System.in);
            System.out.print("Enter the number of your preferred action: ");
            int choice = sc.nextInt();
            // sc.close();
            System.out.println();
            String book_id = "";
            Book book = null;
            switch (choice) {
                case 1:
                    // System.out.println("Do somethings");
                    user.listAllBooks();
                    break;
                case 2:
                    // System.out.println("Do somethings");
                    sc = new Scanner(System.in);
                    System.out.print("Book ID: ");
                    book_id = sc.nextLine();
                    // sc.close();
                    book = user.searchBook(book_id);
                    System.out.println("Book Name: " + book.name + "\nGenre: " + book.genre
                            + "\nAuthor: " + book.author + "\n");
                    break;
                case 3:
                    // System.out.println("Do somethings");
                    sc = new Scanner(System.in);
                    System.out.print("Book ID: ");
                    book_id = sc.nextLine();
                    // sc.close();
                    book = user.searchBook(book_id);
                    user.requestBook(book);
                    break;
                case 4:
                    // System.out.println("Do somethings");
                    sc = new Scanner(System.in);
                    System.out.print("Book ID: ");
                    book_id = sc.nextLine();
                    // sc.close();
                    for (Book each : user.requested_books) {
                        if (each.id.equals(book_id))
                            book = each;
                    }
                    user.returnBook(book);
                    break;
                case 5:
                    sc = new Scanner(System.in);
                    System.out.println("Books with me: ");

                    for (Book each : user.requested_books) {
                        System.out.println(each.name + "(" + each.id + ")");
                    }
                    System.out.println("Total books: " + user.requested_books.size());

                    System.out.println();
                    break;
                case 6:
                    System.out.println("Thank you for visiting our Library. ");
                    entry = false;
                    break;
                default:
                    System.out.println("Select valid operation");
            }
        }
    }

}
