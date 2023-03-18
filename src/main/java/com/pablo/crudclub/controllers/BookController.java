package com.pablo.crudclub.controllers;
import com.pablo.crudclub.models.Book;
import com.pablo.crudclub.models.User;
import com.pablo.crudclub.services.BookService;
import com.pablo.crudclub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class BookController {
    @Autowired
    private UserService userServ;

    @Autowired
    private BookService bookServ;

    @GetMapping("/dashboard")
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        model.addAttribute("books", bookServ.findAll());
        model.addAttribute("user", userServ.findById((Long) session.getAttribute("userId")));
        return "userDash.jsp";
    }

    @GetMapping("/book/new")
    public String newBook(@ModelAttribute("book") Book book, HttpSession session) {
        // this checks if user is logged into session
        if (session.getAttribute("userId") == null) {
            return "redirect:/";
        }
        return "newBook.jsp";
    }

    // CREATES BOOK
    @PostMapping("/book/new")
    public String createBook(
            @Valid @ModelAttribute("book") Book book, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            System.out.println(result);
            return "newBook.jsp";
        }
        Long userId = (Long) session.getAttribute("userId");
        // association to the user ...
        book.setSubmittedBy(userServ.findById(userId));
        bookServ.save(book);
        System.out.println(result);
        return "redirect:/dashboard";
    }

    @GetMapping("/books/{book_id}/view")
    public String viewBook(@PathVariable("book_id") Long book_id, Model model, HttpSession session) {
        Long id = (Long) session.getAttribute("userId");
        if (id == null) {
            return "redirect:/";
        }
        User loggedUser = userServ.findById(id);
        model.addAttribute("user", loggedUser);
        Book oneBook = bookServ.findOneById(book_id);
        model.addAttribute("oneBook", oneBook);
        return "viewBook.jsp";
    }

    // FINDS ONE BOOK
    @GetMapping("/books/{book_id}/update")
    public String editBook(@PathVariable("book_id") Long book_id, Model model, HttpSession session) {
        Long id = (Long) session.getAttribute("userId");
        Book oneBook = bookServ.findOneById(book_id);

        if (id != oneBook.getSubmittedBy().getId()) {
            session.setAttribute("userId", null);
            return "redirect:/";
        }
        User loggedUser = userServ.findById(id);
        model.addAttribute("user", loggedUser);
        model.addAttribute("updateBook", oneBook);
        return "editBook.jsp";
    }

    /// UPDATES BOOK
    @PutMapping("/books/{book_id}/update")
    // this has to come in this order valid, modelAtt etc...
    public String updateBook(
            @Valid @ModelAttribute
                    ("updateBook") Book updateBook, BindingResult result, @PathVariable("book_id") Long book_id, HttpSession session) {
        System.out.println(result);
        Long id = (Long) session.getAttribute("userId");
        Book oneBook = bookServ.findOneById(book_id);
        if (id != oneBook.getSubmittedBy().getId()) {
            session.setAttribute("userId", null);
            return "redirect:/";
        }
        if (result.hasErrors()) {
            return "editBook.jsp";
        }
        updateBook.setId(book_id);
        bookServ.update(updateBook);
        return "redirect:/dashboard";
    }


    // DELETES BOOK
    @GetMapping("/books/{book_id}/delete")
    public String deleteBook(@PathVariable("book_id") Long book_id, HttpSession session) {
        Long id = (Long) session.getAttribute("userId");

        Book oneBook = bookServ.findOneById(book_id);
        if (id != oneBook.getSubmittedBy().getId()) {
            session.setAttribute("userId", null);
            return "redirect:/";
        }
        bookServ.delete(book_id);
        return "redirect:/dashboard";
    }
}
