package com.example.demo;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200") 
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User existingUser = optionalUser.get();
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setEmail(userDetails.getEmail());
        existingUser.setPhoneNumber(userDetails.getPhoneNumber());
        existingUser.setAddress(userDetails.getAddress());

        User updatedUser = userRepository.save(existingUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    
    
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        Optional<User> optional = userRepository.findById(id);

        if (optional.isPresent()) {
            userRepository.delete(optional.get());
            return new ResponseEntity<>("User deleted with id: " + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    
    }
}