package org.softwire.training.bookish.models.database;

import java.beans.ConstructorProperties;

public class BookForDB {
    String title;
    String isbn;
    int copies_total;

    @ConstructorProperties({"id", "title","isbn","copies_total"})
    public BookForDB(String title, String isbn, int copies_total) {
        this.title = title;
        this.isbn = isbn;
        this.copies_total = copies_total;
    }

    @Override
    public String toString() {
        return "Book{" +
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