package com.polytech.ludotech.service;

import com.polytech.ludotech.entity.BookEntity;
import com.polytech.ludotech.repository.BookRepository;
import com.polytech.ludotech.shared.BookAlreadyExistsException;
import com.polytech.ludotech.shared.BookNotExistException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookEntity createBook(BookEntity book) {
        if(bookRepository.findByName(book.getName()) != null)
            throw new BookAlreadyExistsException("Book "+book.getName()+ " already exists");
        return bookRepository.save(book);
    }

    public BookEntity editBook(BookEntity book, String name) {
        BookEntity existing = bookRepository.findByName(name);
        if(existing == null)
            throw new BookNotExistException("Book " + name + " does not exist");

        existing.setName(book.getName());
        existing.setAuthor(book.getAuthor());
        existing.setGenre(book.getGenre());
        existing.setStatus(book.getStatus());
        existing.setYear(book.getYear());

        return bookRepository.save(existing);
    }

    public void deleteBook(String name) {
        BookEntity existing = bookRepository.findByName(name);

        if(existing == null)
            throw new BookNotExistException("Book " + name + " does not exist");

        bookRepository.delete(existing);
    }
}
