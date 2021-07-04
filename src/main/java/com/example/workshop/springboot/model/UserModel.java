package com.example.workshop.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "user_application")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// User Entity
public class UserModel {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;  
  private String name;
  @Email(message = "Seu e-mail nao é valido, por favor digite um e-mail valido")
  private String email;  
}