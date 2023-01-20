package com.controller;

import com.dao.BookDAO;
import com.model.dto.BookCreationDTO;
import com.model.dto.BookDTO;
import com.service.BookDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookDAO bookDAO;

    @PostMapping
    public void create(@RequestBody BookCreationDTO book) {
        bookDAO.createBook(BookDtoMapper.toBook(book));
    }

    @GetMapping("{id}")
    public BookDTO read(@PathVariable Long id) {
        return BookDtoMapper.toDto(bookDAO.readBook(id));
    }

    @PutMapping
    public void update(@RequestBody BookCreationDTO book) {
        bookDAO.updateBook(BookDtoMapper.toBook(book));
    }

    @GetMapping
    public List<BookDTO> getAll() {
        return BookDtoMapper.allToDto(bookDAO.getAllBooks());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        bookDAO.deleteBook(id);
    }
}
