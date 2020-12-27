package com.epam.izh.rd.online.repository;
import java.util.Arrays;
import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];
    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            authors = Arrays.copyOf(authors, authors.length +1);
            authors[authors.length - 1] = author;
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author auth: authors) {
            if (auth.getName().equals(name) && auth.getLastName().equals(lastname)) return auth;
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            Author[] remove = new Author[authors.length - 1];
            int i = 0;
            for (Author auth : authors) {
                if (!auth.equals(author)) {
                    remove[i] = auth;
                    i++;
                }
            }
            authors = remove;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int count() {
        return authors.length;
    }
}
