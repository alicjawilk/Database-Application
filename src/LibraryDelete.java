import java.util.Scanner;

public class LibraryDelete {

    private BookDao bookDao;

    public LibraryDelete(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void deleteBook(){
        System.out.println("Podaj numer id");
        Scanner in = new Scanner(System.in);
        String id = in.nextLine();

        bookDao.delete(Long.parseLong(id));

    }
}
