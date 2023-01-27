package com.controller;

import com.model.Book;
import com.model.dto.BookCreationDTO;
import com.model.dto.BookDTO;
import com.repos.BookRepo;
import com.service.BookDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookRepo bookRepo;

    @Autowired
    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @PostMapping
    public void create(@RequestBody BookCreationDTO book) {
        bookRepo.save(BookDtoMapper.toBook(book));
    }

    @GetMapping("{id}")
    public BookDTO read(@PathVariable Long id) {
        return BookDtoMapper.toDto(bookRepo.findById(id).get());
    }

    @PutMapping
    public void update(@RequestBody BookCreationDTO book) {
        bookRepo.save(BookDtoMapper.toBook(book));
    }

    @GetMapping
    public List<BookDTO> getAll() {
        return BookDtoMapper.allToDto((List<Book>) bookRepo.findAll());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        bookRepo.deleteById(id);
    }
}
