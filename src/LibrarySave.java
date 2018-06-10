import java.util.Scanner;

public class LibrarySave {

    private long id;
    private String title;
    private String author;
    private String year;
    private String isbn;
    private Book book;
    private BookDao bookDao;


    public LibrarySave(BookDao bookDao) {
        //book = new Book();
        this.bookDao = bookDao;
    }

    public void addBook(){
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

        long number = bookDao.checkIdOfLast();
        id = number+1;

        book = new Book(id,title,author,year,isbn);
        //System.out.println(book.toString());
        bookDao.save(book);


    }

}
