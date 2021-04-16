package mk.ukim.finki.emtlab2.service;

import mk.ukim.finki.emtlab2.model.Book;
import mk.ukim.finki.emtlab2.model.dto.BookDto;
import mk.ukim.finki.emtlab2.model.enumeration.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> findByName(String name);
    Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id,String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> edit(Long id,BookDto bookDto);
    void deleteById(Long id);
    void MarkAsTaken(Long id);
    void BookReturned(Long id);
}
