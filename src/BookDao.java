import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "Admin123";
    private Connection connection;

    public BookDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("No driver found");
        } catch (SQLException e){
            System.out.println("Could not establish connection");
        }
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void save(Book book){
        final String sql = "insert into books(id, title, author, year, isbn) values(?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setLong(1,book.getId());
            prepStmt.setString(2,book.getTitle());
            prepStmt.setString(3,book.getAuthor());
            prepStmt.setString(4,book.getYear());
            prepStmt.setString(5,book.getIsbn());
            prepStmt.executeUpdate();
            System.out.println("Dodano książkę");
        } catch (SQLException e) {
            System.out.println("Could not save record");
           // e.printStackTrace();
        }
    }

    public Book read(long id){
        final String sql = "select * from books where id = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setLong(1,id);
            ResultSet result = prepStmt.executeQuery();
            if (result.next()){
                Book book = new Book();
                book.setId(result.getLong("id"));
                book.setTitle(result.getString("title"));
                book.setAuthor(result.getString("author"));
                book.setYear(result.getString("year"));
                book.setIsbn(result.getString("isbn"));
                return book;
            }
        } catch (SQLException e) {
            System.out.println("Could not get book");
        }
        return null;
    }

    public long checkIdOfLast(){
        List<Book> booksList = readAll();
        long id = booksList.get(booksList.size()-1).getId();
        return id;
    }

    public List<Book> readAll(){
        final String sql = "select * from books";
        List<Book> allBooks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                long id = result.getLong("id");
                String title = result.getString("title");
                String author = result.getString("author");
                String year = result.getString("year");
                String isbn = result.getString("isbn");
                Book book = new Book(id,title,author,year,isbn);
                allBooks.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Could not get books");
        }
        return allBooks;
    }

    public void update(Book book){
        final String sql = "update books set title=?, author=?, year=?, isbn=? where id = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, book.getTitle());
            prepStmt.setString(2, book.getAuthor());
            prepStmt.setString(3, book.getYear());
            prepStmt.setString(4, book.getIsbn());
            prepStmt.setLong(5, book.getId());
            prepStmt.executeUpdate();
            System.out.println("Zaktulizowano ksiązkę");
        } catch (SQLException e) {
            System.out.println("Could not update record");
        }
    }

    public void delete(long id){
        final String sql = "delete from books where id = ?";
        try {
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
            System.out.println("Usunięto książkę");
        } catch (SQLException e) {
            System.out.println("Could not delete row");
        }
    }



}
