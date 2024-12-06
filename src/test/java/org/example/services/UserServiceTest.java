package org.example.services;


import org.example.model.User;
import org.example.repositories.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UserServiceTest  {
  static UserService userService;
  @BeforeAll
    static void initAll() {
      userService = new UserService(new UserRepositoryImpl());

  }


  @Test
   void IsValidUser(){
      userService.addUser("testUser","testPassword");
      boolean isvalid = userService.isValidUser("testUser","testPassword");
      assertFalse(isvalid);
  }
}
