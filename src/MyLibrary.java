import java.util.Scanner;

public class MyLibrary {

    public static void main(String[] args) {

        BookDao bookDao = new BookDao();

        System.out.println("Wybierz opcję\n1 - Dodaj nowy rekord\n"+
                        "2 - Wyświetl wszystkie książki\n3 - Usuń książkę\n"+
                        "4 - Zaktualizuj książkę\n5 - Zakończ");

        String choice = new String();

        while (!"5".equals(choice)) {

        Scanner in = new Scanner(System.in);
        choice = in.nextLine();

            if ("1".equals(choice)) {
                LibrarySave librarySave = new LibrarySave(bookDao);
                librarySave.addBook();
            } else if ("2".equals(choice)) {
                LibraryRead libraryRead = new LibraryRead(bookDao);
                libraryRead.readBooks();
            } else if ("3".equals(choice)) {
                LibraryDelete libraryDelete = new LibraryDelete(bookDao);
                libraryDelete.deleteBook();
            } else if ("4".equals(choice)) {
                LibraryUpdate libraryUpdate = new LibraryUpdate(bookDao);
                libraryUpdate.updateBook();
            } else if ("5".equals(choice)) {
                System.out.println("Zakończono");
            } else
                System.out.println("Wprowadź liczbe od 1 do 5");

        }

        bookDao.close();

    }
}
