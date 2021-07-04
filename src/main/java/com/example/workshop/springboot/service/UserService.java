package com.example.workshop.springboot.service;

import com.example.workshop.springboot.model.UserModel;
import com.example.workshop.springboot.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Iterable<UserModel> getUsers() {
    log.info("Getting all users");
    return userRepository.findAll();
  }

  public UserModel getUser(Integer id) {
    log.info("Getting user by id {}", id);
    return userRepository.findById(id).orElseThrow(() -> {
      return new RuntimeException(String.format("User=[%s] was not found", id));
    });
  }

  public UserModel delete(Integer id) {
    log.info("Deleting user by id {}", id);
    UserModel user = userRepository.findById(id).orElseThrow(() -> {
      return new RuntimeException(String.format("User=[%s] was not found", id));
    });
    userRepository.deleteById(id);
    return user;
  }

  public UserModel createUser(String name, String email) {
    UserModel user = UserModel.builder().name(name).email(email).build();
    log.info("Creating {}", user);
    return userRepository.save(user);
  }

  public UserModel updateUser(Integer id, String name, String email) {
    log.info("Updating user by id {}", id);
    UserModel user = userRepository.findById(id).orElseThrow(() -> {
      return new RuntimeException(String.format("User=[%s] was not found", id));
    });

    if (name != null) {
      user.setName(name);
    }

    if (email != null) {
      user.setEmail(email);
    }

    userRepository.save(user);
    return user;
  }

}