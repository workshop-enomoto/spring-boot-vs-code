package com.example.workshop.springboot.controller;

import javax.validation.Valid;

import com.example.workshop.springboot.dto.UserDTO;
import com.example.workshop.springboot.model.UserModel;
import com.example.workshop.springboot.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Iterable<UserModel>> getUsers() {
    Iterable<UserModel> users = userService.getUsers();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserModel> getPerson(@PathVariable Integer id) {
    UserModel user = userService.getUser(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping
  public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserDTO userDTO) {
    UserModel user = userService.createUser(userDTO.getName(), userDTO.getEmail());
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserModel> createUser(@PathVariable Integer id,
      @RequestBody @Valid UserDTO userDTO) {
    UserModel user = userService.updateUser(id, userDTO.getName(), userDTO.getEmail());
    return ResponseEntity.ok(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<UserModel> deletePerson(@PathVariable Integer id) {
    UserModel user = userService.delete(id);
    return ResponseEntity.ok(user);
  }

}