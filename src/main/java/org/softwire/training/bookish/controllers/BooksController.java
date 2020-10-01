package org.softwire.training.bookish.controllers;


import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.BookWithAuthors;
import org.softwire.training.bookish.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    @Autowired
    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("")
    ModelAndView allBooksView() {
        return new ModelAndView("books", "allBooks", bookService.getAllBooks());
    }

    @RequestMapping("/allBooksWithAuthors")
    ModelAndView allBooksWithAuthorsView() {
        System.out.println("ROUTE @RequestMapping(books/allBooksWithAuthors activated");
        //return new ModelAndView("books_with_authors", "allBooks", bookService.getAllBooksWithAuthors());
        return new ModelAndView("books_with_authors", "allBooks", bookService.getAllBooksWithAuthors());
        //return new ModelAndView("books", "allBooks", bookService.getAllBooks());
    }

    @RequestMapping("/add-book")
    RedirectView addBook(@ModelAttribute Book book, RedirectAttributes attr) {
        //System.out.println("Book to add to db: " + book);
        bookService.addBook(book);
        attr.addFlashAttribute("successXY","Book added successfully");
        return new RedirectView("/books");
    }

    @RequestMapping("/add_book_with_authors")
    ModelAndView addBookWithAuthors() {
        return new ModelAndView("add_book_with_authors");
    }

    @RequestMapping("/add_book_with_authors/submit")
    RedirectView addBook(@ModelAttribute BookWithAuthors bookWA, RedirectAttributes attr) {
        System.out.println("Book with authors to add to db: " + bookWA);
        bookService.addBookWithAuthors(bookWA);
        attr.addFlashAttribute("successXY","Book with authors added successfully");
        return new RedirectView("/books");
    }

    @RequestMapping("/delete-book")
    RedirectView deleteBook(@RequestParam int bookId, RedirectAttributes attr){
        bookService.deleteBook(bookId);
        attr.addFlashAttribute("successXY","Book deleted successfully");
        return new RedirectView("/books");
    }

    //@RequestMapping("/delete-book")
    //RedirectView deleteBook(@RequestParam int bookId){
    //    bookService.deleteBook(bookId);
    //    return new RedirectView("/books");
    //}

    @RequestMapping("/edit-book")
    //RedirectView editBook(@RequestParam int bookId){
    ModelAndView editBook(@RequestParam int bookId){
        Book book = bookService.getBookById(bookId);
        System.out.println("Requested book: " + book.toString());
        return new ModelAndView("edit_book", "book", book);
    }

    @RequestMapping("/edit-book/submit")
    RedirectView editBookSubmit
        //(@RequestParam int bookId)
        (@ModelAttribute Book book , RedirectAttributes attr)
    {
        //System.out.println("book edited in Bcontroller: " + book.toString());
        bookService.editBook(book);
        attr.addFlashAttribute("successXY","Book edited successfully");
        return new RedirectView("/books");
    }
}
