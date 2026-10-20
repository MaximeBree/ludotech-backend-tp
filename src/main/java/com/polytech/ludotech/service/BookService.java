package com.polytech.ludotech.service;

import com.polytech.ludotech.entity.BookEntity;
import com.polytech.ludotech.entity.Status;
import com.polytech.ludotech.repository.BookRepository;
import com.polytech.ludotech.shared.BookAlreadyExistsException;
import com.polytech.ludotech.shared.BookNotExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final KpiService kpiService;

    public BookService(BookRepository bookRepository, KpiService kpiService) {
        this.bookRepository = bookRepository;
        this.kpiService = kpiService;
    }

    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public BookEntity createBook(BookEntity book) {
        if(bookRepository.findByName(book.getName()) != null)
            throw new BookAlreadyExistsException("Book "+book.getName()+ " already exists");
        BookEntity saved = bookRepository.save(book);
        kpiService.modifierLivre(saved.getStatus(), 1);
        return saved;
    }

    @Transactional
    public BookEntity editBook(BookEntity book, String name) {
        BookEntity existing = bookRepository.findByName(name);
        if(existing == null)
            throw new BookNotExistException("Book " + name + " does not exist");

        Status ancienStatus = existing.getStatus();

        existing.setName(book.getName());
        existing.setAuthor(book.getAuthor());
        existing.setGenre(book.getGenre());
        existing.setStatus(book.getStatus());
        existing.setYear(book.getYear());

        if (ancienStatus != book.getStatus()) {
            kpiService.modifierLivre(ancienStatus, -1);
            kpiService.modifierLivre(book.getStatus(), 1);
        }

        return bookRepository.save(existing);
    }

    @Transactional
    public void deleteBook(String name) {
        BookEntity existing = bookRepository.findByName(name);

        if(existing == null)
            throw new BookNotExistException("Book " + name + " does not exist");

        bookRepository.delete(existing);
        kpiService.modifierLivre(existing.getStatus(), -1);
    }
}
