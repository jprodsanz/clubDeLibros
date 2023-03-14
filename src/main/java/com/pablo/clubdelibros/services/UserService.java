package com.pablo.clubdelibros.services;

import com.pablo.clubdelibros.models.LoginUser;
import com.pablo.clubdelibros.models.User;
import com.pablo.clubdelibros.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    // LOGIN
    // this method returns a user object called "login/register"
    // takes in a user, and we pass binding results in
    public User login(LoginUser l, BindingResult result) {
        Optional<User> optUser = userRepo.findByEmail(l.getEmail());
        if (optUser.isEmpty()) {
            result.rejectValue("email", "Matches", "User not found");
            return null;
        }
        User user = optUser.get();
        if (!BCrypt.checkpw(l.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid Password");
            return null;
        }
        return user;
    }
    // REGISTER
    public User register(User u, BindingResult result) {
        if (!u.getConfirmPass().equals(u.getPassword())){
            result.rejectValue("email", "Matches", "Email is already taken ");
        }
        if (!u.getConfirmPass().equals(u.getPassword())){
            result.rejectValue("confirmPass", null, "Passwords do not match");
        }
        if (result.hasErrors()){
            return null;
        }
        String hashPW = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
        u.setPassword(hashPW);
        return userRepo.save(u);
    }
    // FIND USER BY ID
    public User findById(Long id) {
        Optional<User> optUser = userRepo.findById(id);
        if (optUser.isEmpty()) {
            return null;
        }
        return optUser.get();
    }
    //  GET ALL
    public List<User> getAll(){
        return userRepo.findAll();
    }
    // UPDATE
    public User update(User u){
        return userRepo.save(u);
    }
    // DELETE
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

}
