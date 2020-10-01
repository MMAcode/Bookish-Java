package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookWithAuthors;
import org.softwire.training.bookish.models.database.BookWithAuthorsForWebView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService extends DatabaseService {


    private Map<String, Object> getBookFields(Book book) {
        Map<String, Object> bookFields = new HashMap<>();
        bookFields.put("id", book.getId());
        bookFields.put("title", book.getTitle());
        bookFields.put("isbn", book.getIsbn());
        bookFields.put("copies_total", book.getCopies_total());
        return bookFields;
    }

    public List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                handle
                        .createQuery("SELECT * FROM books")
                        .mapToBean(Book.class)
                        .list()
        );
    }

    public Book getBookById(int bookId) {
        return jdbi.withHandle(handle ->
                handle
                        .createQuery("SELECT * FROM books WHERE id=:id")
                        .bind("id", bookId)
                        .mapToBean(Book.class)
                        .findOnly()
        );
    }

    public void addBook(Book book) {
        System.out.println("bookForDB: " + book.toString());
        jdbi.useHandle(handle ->
                        handle.execute("insert into books (title,isbn,copies_total) values (?, ?, ?)", book.getTitle(), book.getIsbn(), book.getId())
                //        handle.createUpdate("INSERT INTO books (title, isbn, copies_total) VALUES (:title,:isbn,:copies_total)")
                //        		.bind("title", book.getTitle())
                //        		.bind("isbn", book.getIsbn())
                //        		.bind("copies_total", book.getCopies_total())
                //        		.execute()
        );
    }

    public void editBook(Book book) {
        System.out.println("book in Service-Edited-ForDB: " + book.toString());
        jdbi.useHandle(handle ->
                //handle.execute("UPDATE books SET title=?,isbn=?,copies_total=? WHERE id=?",book.getTitle(),book.getIsbn(),book.getCopies_total(),book.getId())
                handle
                        .createUpdate("UPDATE books SET title=:title,isbn=:isbn,copies_total=:copies_total WHERE id=:id")
                        .bindMap(getBookFields(book))
                        .execute()
        );
    }

    public void deleteBook(int bookId) {
        jdbi.useHandle(handle ->
                //handle.createUpdate("DELETE FROM books WHERE id = :id")
                //        .bind("id", bookId)
                //        .execute()
                handle.execute("DELETE FROM books WHERE id = ?", bookId)
        );
    }

    public void addBookWithAuthors(BookWithAuthors bookWA){
        String[] authorsNames = bookWA.getAuthors().split(",");
        for (int i=0;i< authorsNames.length;i++) {
            authorsNames[i]=authorsNames[i].trim();
        }

        /////try to get that author's id from db
        //String id0 = jdbi.useHandle(handle -> handle
        //List<String> id0 = jdbi.withHandle(handle -> handle
        //        .createQuery("SELECT * FROM authors WHERE name=:name")
        //        .bind("name","a3")
        //        .mapTo(String.class)
        //        .list()
        //);

        //(if null) create that author
        for (int i=0;i< authorsNames.length;i++) {
            int j = i;
            jdbi.useHandle(handle -> handle.execute("insert into authors (name) values (?)", authorsNames[j]));
        }

        //create that book
        jdbi.useHandle(handle -> handle.execute("insert into books (title,isbn,copies_total) values (?, ?, ?)", bookWA.getTitle(), bookWA.getIsbn(), bookWA.getId()));


        //create link between author id and book id





    }

    //public List<BookWithAuthors> getAllBooksWithAuthors(){
    public List<BookWithAuthorsForWebView> getAllBooksWithAuthors(){
        List <BookWithAuthors> booksWithAuthors = booksWithAuthors = jdbi.withHandle(handle -> handle
                        .createQuery("" +
                                "SELECT " +
                                "b.id,b.isbn,b.title,b.copies_total," +
                                "t.authors, t.authorIds " +
                                "FROM books b " +
                                "INNER JOIN(" +
                                "" +
                                "SELECT " +
                                "ab.book_id, GROUP_CONCAT(a.name) AS authors, GROUP_CONCAT(a.id) AS authorIds  " +
                                "FROM author_books ab " +
                                "INNER JOIN authors a ON ab.author_id=a.id " +
                                "GROUP BY ab.book_id " +
                                ") t " +
                                "ON b.id=t.book_id" +
                                //"WHERE b.id=32" +
                                "")
                        .mapToBean(BookWithAuthors.class)
                        .list()
        );

        String s1 = booksWithAuthors.get(0).toString();
        System.out.println("Book1 with authors:");
        System.out.println(s1);
        List<BookWithAuthorsForWebView> list = new ArrayList<>();
        for(BookWithAuthors b : booksWithAuthors) list.add(new BookWithAuthorsForWebView(b));

        //return booksWithAuthors;
        return list;
    }
}
