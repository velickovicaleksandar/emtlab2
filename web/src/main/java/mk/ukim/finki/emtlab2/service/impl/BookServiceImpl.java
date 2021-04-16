package mk.ukim.finki.emtlab2.service.impl;

import mk.ukim.finki.emtlab2.model.Author;
import mk.ukim.finki.emtlab2.model.Book;
import mk.ukim.finki.emtlab2.model.dto.BookDto;
import mk.ukim.finki.emtlab2.model.enumeration.Category;
import mk.ukim.finki.emtlab2.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emtlab2.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emtlab2.model.exceptions.BookOutOfStorageException;
import mk.ukim.finki.emtlab2.repository.AuthorRepository;
import mk.ukim.finki.emtlab2.repository.BookRepository;
import mk.ukim.finki.emtlab2.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }



    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {
            Author author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new AuthorNotFoundException(authorId));
            this.bookRepository.deleteByName(name);
        Book book = new Book(name,category,author,availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);
    }



    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);
        this.bookRepository.save(book);
        return Optional.of(book);

    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);

    }

    @Override
    public void MarkAsTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        int availableCopies = book.getAvailableCopies();
        if(availableCopies<1){
            throw new BookOutOfStorageException(id);
        }
        int new_availableCopies = availableCopies -1;
        book.setAvailableCopies(new_availableCopies);
    }

    @Override
    public void BookReturned(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        int availableCopies = book.getAvailableCopies();

        int new_availableCopies = availableCopies +1;
        book.setAvailableCopies(new_availableCopies);
    }

    public Optional<Book> save(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }


}
