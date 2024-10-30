//package org.example.services;
//
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import java.util.HashMap;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//
//class UserServiceTest  {
//  static UserService userService;
//  @BeforeAll
//    static void initAll() {
//      userService = new UserService(null);
//      HashMap<String,String> testUserData = new HashMap<>();
//      testUserData.put("testUser","password123");
//      userService.userData = testUserData;
//  }
//
//
//  @Test
//   void IsValidUser(){
//      boolean isvalid = userService.isValidUser("testUser","password123");
//      assertTrue(isvalid);
//  }
//}
