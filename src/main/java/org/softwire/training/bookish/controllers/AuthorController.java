package org.softwire.training.bookish.controllers;


import org.softwire.training.bookish.models.database.Author;
import org.softwire.training.bookish.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("")
    ModelAndView allAuthorsView() {
        List<Author> allAuthors = authorService.getAllAuthors();
        //AllAuthorsModel allAuthorsModel = new AllAuthorsModel();
        //allAuthorsModel.setAllAuthors(allAuthors);
        //System.out.println("all authors size: " + allAuthorsModel.getAllAuthors().size());
        //return new ModelAndView("authors", "allAuthors", allAuthorsModel.getAllAuthors());
        return new ModelAndView("authors", "allAuthors", allAuthors);
    }

    @RequestMapping("/add-author")
    RedirectView addAuthor(@ModelAttribute Author author) {
        System.out.println("Author to add to db: " + author);
        authorService.addAuthor(author);
        return new RedirectView("/authors");
    }

    @RequestMapping("/delete-author")
    RedirectView deleteAuthor(@RequestParam int authorId){
        authorService.deleteAuthor(authorId);
        return new RedirectView("/authors");
    }

    @RequestMapping("/edit-author")
    //RedirectView editAuthor(@RequestParam int authorId){
    ModelAndView editAuthor(@RequestParam int authorId){
        Author author = authorService.getAuthor(authorId);
        System.out.println("Requested author: " + author.toString());
        return new ModelAndView("edit_author", "author", author);
    }

    @RequestMapping("/edit-author/submit")
    RedirectView editAuthorSubmit
        //(@RequestParam int authorId)
        (@ModelAttribute Author author , RedirectAttributes attr)
    {
        //System.out.println("author edited in Bcontroller: " + author.toString());
        authorService.editAuthor(author);
        attr.addFlashAttribute("successXY","Author edited successfully");
        return new RedirectView("/authors");
    }
}
