package com.pablo.crudclub.controllers;

import com.pablo.crudclub.models.LoginUser;
import com.pablo.crudclub.models.User;
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
public class MainController {
    @Autowired
    private UserService userServ;
    // HOME PAGE RENDER
    @GetMapping("/")
    // can't do the (@ModelAttribute) annotation here bc we need two
    public String index (Model model) {
        // creates a new User model and attaches it to user
        model.addAttribute("user", new User());
        model.addAttribute("loginUser", new LoginUser());
            return "index.jsp";
    }
    // LOGIN PAGE RENDER
    @GetMapping("/login")
    public String getLogin(@ModelAttribute("loginUser") LoginUser loginUser) {
        return "index.jsp";
    }
    // LOGIN PAGE POST
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginUser") LoginUser loginUser, BindingResult result, Model model, HttpSession session) {
        // run login method
        User user = userServ.login(loginUser, result);
        if(user == null) {
            model.addAttribute("user", new User());
            return "index.jsp";
        }
        // add user to DB
        // log user in
        session.setAttribute("userId", user.getId());
        return "redirect:/dashboard";
    }
    // REGISTER
    @GetMapping("/register")
    public String getRegister(@ModelAttribute("user") User newUser) {
        return "register.jsp";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
        // run reg method
        User newUser = userServ.register(user, result);
        if(newUser == null) {
            model.addAttribute("loginUser", new LoginUser());
            return "register.jsp";
        }
        // add user to DB
        // log user in
        session.setAttribute("userId", newUser.getId());
        return "redirect:/dashboard";
    }
    // UPDATING
    @GetMapping("/user/{id}/edit")
    public String editUser(@PathVariable("id")Long id, Model model) {
        model.addAttribute("user", userServ.findById(id));
        return "editUser.jsp";
    }
    @PutMapping("/user/{id}/edit")
    // this has to come in this order valid, modelAtt etc...
    public String updateUser(
            @PathVariable("id") Long id, @RequestParam("username") String username) {
        User user = userServ.findById(id);
        user.setUsername(username);
        userServ.update(user);
        return "redirect:/dashboard";
    }
////     DASHBOARD
//    @GetMapping("/dashboard")
//    public String dashboard(Model model, HttpSession session) {
//        Long id = (Long) session.getAttribute("userId");
//        if (id==null) {
//            return "redirect:/";
//        }
//        else {
//            List<User> allUsers = userServ.getAll();
//            User loggedUser = userServ.findById(id);
//            model.addAttribute("user", loggedUser);
//            model.addAttribute("allUsers", allUsers);
//            return "userDash.jsp";
//        }
//    }
//    @GetMapping("/dashboard")
//    public String dashboard(Model model, HttpSession session) {
//        if (session.getAttribute("userId") == null) {
//            return "redirect:/";
//        }
//        Long id = (Long) session.getAttribute("userId");
//        User loggedUser = userServ.findById(id);
//        model.addAttribute("user", loggedUser);
//            return "userDash.jsp";
//        }
    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("userId", null);
        return "redirect:/";
    }
    // DELETE
    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userServ.delete(id);
        return "redirect:/dashboard";
    }


}
