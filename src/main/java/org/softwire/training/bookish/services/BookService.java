package org.softwire.training.bookish.services;

import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookForDB;
import org.softwire.training.bookish.models.database.Technology;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService extends DatabaseService {

    public List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                        //handle
                        //        .registerRowMapper(ConstructorMapper.factory(Book.class))
                        //        .createQuery("SELECT * FROM books")
                        //        .mapTo(Book.class)
                        //        .collect(Collectors.toList())

                handle
                        .createQuery("SELECT * FROM books")
                        .mapToBean(Book.class)
                        .list()
        );
    }

    //public Book getBookById(int bookId){
    //    return jdbi.withHandle(handle ->
    //            handle
    //                    .createQuery("SELECT * FROM books WHERE id=:id")
    //                    .bind("id", bookId)
    //                    .mapToBean(Book.class)
    //    );
    //}

    public Book getBookById(int bookId){
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
                //handle.createUpdate("INSERT INTO books (title, isbn, copies_total) VALUES (:title,:isbn,:copies_total)")
                //		.bind("title", book.getTitle())
                //		.bind("isbn", book.getIsbn())
                //		.bind("copies_total", book.getCopies_total())
                //		.execute()
        );
    }

    public void deleteBook(int bookId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM books WHERE id = :id")
                        .bind("id", bookId)
                        .execute()
        );
    }
}
