package org.softwire.training.bookish;

import java.beans.ConstructorProperties;

public class Book {
    int id;
    String title;
    String isbn;
    int copies_total;

    @ConstructorProperties({"id", "title","isbn","copies_total"})
    public Book(int id, String title, String isbn, int copies_total) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
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