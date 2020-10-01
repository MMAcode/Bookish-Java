package org.softwire.training.bookish.models.database;

import java.util.Arrays;

public class BookWithAuthorsForWebView {
    private int id;
    private String title;
    private String isbn;
    private int copies_total;
    private String[] authors;
    private String[] authorIds;

    public BookWithAuthorsForWebView(BookWithAuthors b) {
        this.id = b.getId();
        this.title = b.getTitle();
        this.isbn = b.getIsbn();
        this.copies_total = b.getCopies_total();
        this.authors = b.getAuthors().split(",");
        this.authorIds = b.getAuthorIds().split(",");
    }

    public String[] getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(String[] authorIds) {
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

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String bookToString() {
        return
                "id=" + id +
                        ", title='" + title.toUpperCase() + '\'' +
                        ", isbn='" + isbn + '\'' +
                        ", copies_total=" + copies_total
                ;
    }

    public String authorsToString() {
        //return String.join(", ",authors) + String.join(", ",authorIds);
        String s = "";
        for (int i=0; i<authors.length;i++){
            s+=authors[i] + " (id: " + authorIds[i] + ")";
            if (i< authorIds.length-1) s+= ", ";
        }
        return s;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", title='" + title.toUpperCase() + '\'' +
                ", isbn='" + isbn + '\'' +
                ", copies_total=" + copies_total
                + ", authors=" + Arrays.toString(authors)
                ;
    }
}
