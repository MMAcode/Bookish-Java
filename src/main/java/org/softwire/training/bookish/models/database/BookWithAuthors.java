package org.softwire.training.bookish.models.database;

import org.softwire.training.bookish.models.database.Author;

import java.util.ArrayList;
import java.util.List;

public class BookWithAuthors {
    private int id;
    private String title;
    private String isbn;
    private int copies_total;
    //private List<Author> authors = new ArrayList<>();
    private String authors;
    private String authorIds;

    public String getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(String authorIds) {
        this.authorIds = authorIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCopies_total() {
        return copies_total;
    }

    public void setCopies_total(int copies_total) {
        this.copies_total = copies_total;
    }

    //public List<Author> getAuthors() {
    //    return authors;
    //}
    //
    //public void setAuthors(List<Author> authors) {
    //    this.authors = authors;
    //}
    //public void addAuthors(List<Author> authors) {
    //    this.authors.addAll(authors);
    //}
    //public void addAuthor(Author author) {
    //    this.authors.add(author);
    //}


    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "BookWithAuthors{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", copies_total=" + copies_total +
                ", authors='" + authors + '\'' +
                ", authorIds='" + authorIds + '\'' +
                '}';
    }
}
