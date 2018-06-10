import java.util.Scanner;

public class LibraryUpdate {

    private long id;
    private String title;
    private String author;
    private String year;
    private String isbn;
    private Book book;
    private BookDao bookDao;

    public LibraryUpdate(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void updateBook(){

        System.out.println("Podaj id");
        Scanner inId = new Scanner(System.in);
        id = Long.parseLong(inId.nextLine());

        System.out.println("Podaj tytuł");
        Scanner inTitle = new Scanner(System.in);
        title = inTitle.nextLine();

        System.out.println("Podaj autora");
        Scanner inAuthor = new Scanner(System.in);
        author = inAuthor.nextLine();

        System.out.println("Podaj rok");
        Scanner inYear = new Scanner(System.in);
        year = inYear.nextLine();

        System.out.println("Podaj numer isbn");
        Scanner inIsbn = new Scanner(System.in);
        isbn = inIsbn.nextLine();

        book = new Book(id, title, author, year, isbn);
        bookDao.update(book);

    }
}
