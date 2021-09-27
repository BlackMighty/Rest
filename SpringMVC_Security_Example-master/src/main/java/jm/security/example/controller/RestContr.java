package jm.security.example.controller;

import jm.security.example.model.User;
import jm.security.example.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rest")
public class RestContr {

    private final UserServiceImpl userServiceImpl;

    public RestContr(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<User>> allUsers() {
        List<User> list = userServiceImpl.allUsers();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(userServiceImpl.getUserById(id));
    }

    @PostMapping(value = "/newUser")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        try {
            userServiceImpl.saveUser(user);
            return new ResponseEntity<>("User save", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("User not save", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/edit")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try{
            userServiceImpl.saveUser(user);
            return new ResponseEntity<>("User update", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("User not update", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try{
            userServiceImpl.deleteUser(userServiceImpl.getUserById(id));
            return new ResponseEntity<>("User delete", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("User not delete", HttpStatus.NOT_FOUND);
        }
    }
}