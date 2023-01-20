package com.service;

import com.model.Book;
import com.model.dto.BookCreationDTO;
import com.model.dto.BookDTO;

import java.util.List;

public class BookDtoMapper {
    private BookDtoMapper() {

    }

    public static BookDTO toDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setCategory(book.getCategory());
        return bookDTO;
    }

    public static Book toBook(BookCreationDTO bookCreationDTO) {
        Book book = new Book();
        book.setId(bookCreationDTO.getId());
        book.setTitle(bookCreationDTO.getTitle());
        book.setAuthor(bookCreationDTO.getAuthor());
        book.setCategory(bookCreationDTO.getCategory());
        return book;
    }

    public static List<BookDTO> allToDto(List<Book> books) {
        return books.stream()
                .map(BookDtoMapper::toDto)
                .toList();
    }
}
