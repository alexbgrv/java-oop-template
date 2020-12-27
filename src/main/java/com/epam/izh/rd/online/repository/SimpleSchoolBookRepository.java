package com.epam.izh.rd.online.repository;

import java.util.Arrays;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];
    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length + 1);
        schoolBooks[schoolBooks.length - 1] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        for (SchoolBook books :schoolBooks) {
            if (books.getName().equals(name)) return schoolBooks;
        }
        return new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        int count = 1;
        for (int i = 0; i < schoolBooks.length; i++) {
            if (schoolBooks[i].getName().equals(name)) {
                schoolBooks[i] = schoolBooks[schoolBooks.length - count];
                count++;
            }
        }
        if (count > 1) {
            schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - (count - 1));
            return true;
        }
        else
            return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
