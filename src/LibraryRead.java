import java.util.List;

public class LibraryRead {

    private BookDao bookDao;

    public LibraryRead(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void readBooks(){
        System.out.println("Pobrane książki:");
        List<Book> books = bookDao.readAll();

        for (Book book : books){
            System.out.println(book.toString());
        }
    }
}
