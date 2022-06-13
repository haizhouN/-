package book.pojo.service.Impl;

import book.pojo.Book;
import book.pojo.dao.BookDAO;
import book.pojo.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDAO bookDAO;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return bookDAO.getBook(id);
    }
}
