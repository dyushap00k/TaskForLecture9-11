package com.controller;

import com.dao.BookDAO;
import com.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookDAO bookDAO;

    @PostMapping("/create")
    public void create(@RequestBody Book book){
        bookDAO.createBook(book);
    }

    @GetMapping("/read/{id}")
    public Book read(@PathVariable Long id){
        return bookDAO.readBook(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Book book){
        bookDAO.updateBook(book);
    }

    @GetMapping("/get-all")
    public List<Book> getAll(){
        return bookDAO.getAllBooks();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        bookDAO.deleteBook(id);
    }
}
