package com.pablo.clubdelibros.repositories;

import com.pablo.clubdelibros.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    // crud repository does not come with a find by email, so make one
    Optional<User>  findByEmail(String email);

    @Override
    List<User> findAll();

}
