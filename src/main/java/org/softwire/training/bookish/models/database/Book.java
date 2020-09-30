package org.softwire.training.bookish.models.database;

import java.beans.ConstructorProperties;

public class Book {
    private int id;
    private String title;
    private String isbn;
    private int copies_total;

    //@ConstructorProperties({"id", "title","isbn","copies_total"})
    //public Book(int id, String title, String isbn, int copies_total) {
    //    this.id = id;
    //    this.title = title;
    //    this.isbn = isbn;
    //    this.copies_total = copies_total;
    //}


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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", copies_total=" + copies_total +
                '}';
    }
}

//   return new Book(
//           rs.getInt("id"),
//           rs.getString("title"),
//           rs.getString("isbn"),
//           rs.getInt("id")
//           )