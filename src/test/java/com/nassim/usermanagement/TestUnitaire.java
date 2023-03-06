package com.nassim.usermanagement;

import com.nassim.usermanagement.Controller.UserController;
import com.nassim.usermanagement.Service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUnitaire {

@Test
    public void return_helloworld_when_get_hello(){
    UserService userService = new UserService();
    UserController userController = new UserController();
    userController.userService = userService; // inject the userService into the controller
    String message = userController.hello();
    assertEquals(message, "Hello World");
}

    @Test
    public void return_helloworld_when_get_hello2(){
        UserService userService = new UserService();
        UserController userController = new UserController();
        userController.userService = userService; // inject the userService into the controller
        String message = userController.hello();
        assertEquals(message, "Hello World");
    }
}
