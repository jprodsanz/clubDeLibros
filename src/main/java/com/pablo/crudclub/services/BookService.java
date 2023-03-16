package com.pablo.crudclub.services;

import com.pablo.crudclub.models.Book;
import com.pablo.crudclub.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepo;

    public List<Book> findAll() {
        return bookRepo.findAll();
    }
    public Book save(Book b) {
        return bookRepo.save(b);
    }
    public Book findOneById(Long id) {
        // optional has to do with SQL
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isPresent()){
            return optionalBook.get();
        }
        else {
            return null;
        }
    }
    public Book update(Book b) {
        return bookRepo.save(b);
    }
    public void delete(Long id) {
        bookRepo.deleteById(id);
    }
}
