package com.pablo.crudclub.repositories;

import com.pablo.crudclub.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends CrudRepository <Book, Long> {
    @Override
    List<Book> findAll();
}
