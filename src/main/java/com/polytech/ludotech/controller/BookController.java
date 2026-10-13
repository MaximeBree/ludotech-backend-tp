package com.polytech.ludotech.controller;

import com.polytech.ludotech.dto.BookDTO;
import com.polytech.ludotech.mappers.BookMapper;
import com.polytech.ludotech.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookMapper.toDTO(bookService.getAllBooks());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookMapper.toDTO(bookService.createBook(bookMapper.toEntity(bookDTO)));
    }

    @PutMapping("/{name}")
    public BookDTO editBook(@RequestBody BookDTO bookDTO, @PathVariable String name) {
        return bookMapper.toDTO(bookService.editBook(bookMapper.toEntity(bookDTO), name));
    }

    @DeleteMapping("/{name}")
    public void deleteBook(@PathVariable String name) {
        bookService.deleteBook(name);
    }
}
