package br.com.senai.controllers;


import br.com.senai.models.Brand;
import br.com.senai.models.Users;
import br.com.senai.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UsersController {
    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Users> getAllUsers() {return usersRepository.findAll(); }

    @PostMapping(value = "/createUsers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Users creatUsers(@RequestBody Users users){
        Users newUsers = new Users();

        newUsers.setPassword(users.getPassword());
        newUsers.setUsername(users.getUsername());

        return usersRepository.save(newUsers);

    }
    @PutMapping(value = "/updateUsers",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)

    public Users updateUsers(@RequestBody Users users) {
        Users getUsers = usersRepository
                .findById(users.getId()).orElseThrow();
        Users updateUsers = new Users();

        updateUsers.setId(users.getId());
        updateUsers.setPassword(users.getPassword());
        updateUsers.setUsername(users.getUsername());
        return usersRepository.save(updateUsers);

    }

    @DeleteMapping(value = "/deleteUsers/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Users deleteBrand(@PathVariable Long id) {
        Users getUsers = usersRepository.findById(id).orElseThrow();
        usersRepository.delete(getUsers);

        return getUsers;
    }
}
