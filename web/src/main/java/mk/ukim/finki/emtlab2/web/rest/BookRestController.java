package mk.ukim.finki.emtlab2.web.rest;

import mk.ukim.finki.emtlab2.model.Book;
import mk.ukim.finki.emtlab2.model.dto.BookDto;
import mk.ukim.finki.emtlab2.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookRestController {
    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    List<Book> listAll(){
        return this.bookService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
