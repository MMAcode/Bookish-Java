package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Author;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorService extends DatabaseService {


    private Map<String, Object> getAuthorFields(Author author) {
        Map<String, Object> authorFields = new HashMap<>();
        authorFields.put("id", author.getId());
        authorFields.put("name", author.getName());
        return authorFields;
    }

    public List<Author> getAllAuthors() {
        return jdbi.withHandle(handle ->
                handle
                        .createQuery("SELECT * FROM authors")
                        .mapToBean(Author.class)
                        .list()
        );
    }

    public Author getAuthor(int authorId) {
        return jdbi.withHandle(handle -> handle
                        .createQuery("SELECT * FROM authors WHERE id=:id")
                        .bind("id", authorId)
                        .mapToBean(Author.class)
                        .findOnly()
        );
    }



    public void addAuthor(Author author) {
        jdbi.useHandle(handle ->
                        handle.execute("insert into authors (name) values (?)", author.getName())
        );
    }

    public void editAuthor(Author author) {
        jdbi.useHandle(handle ->
                handle
                        .createUpdate("UPDATE authors SET name=:name WHERE id=:id")
                        .bindMap(getAuthorFields(author))
                .execute()
        );
    }

    public void deleteAuthor(int authorId) {
        jdbi.useHandle(handle ->
                handle.execute("DELETE FROM authors WHERE id = ?", authorId)
        );
    }
}
