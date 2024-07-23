package test;

import main.controllerPackage.UserController;
import main.exceptionPackage.*;
import main.modelPackage.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController userController;

    @BeforeEach
    void setUp() throws ConnectionDataAccessException {
        userController = new UserController();
    }

    @Test
    void createUser() throws UserCreationException {
        UserModel user = new UserModel();
        user.setEmail("test@test.com");
        user.setUsername("supertesteur");
        user.setPassword("abcdefg");
        user.setDateOfBirth(Date.valueOf("1990-01-01"));
        user.setGender('m');
        user.setStreetAndNumber("test street");
        user.setAdmin(false);
        user.setHome(1);
        assertTrue(userController.createUser(user));
    }

    @Test
    void getUsersByCountry() throws UserSearchException {
        assertNotNull(userController.getUsersByCountry("belgium"));
    }

    @Test
    void login() throws LoginException {
        assertTrue(userController.login(1, "test@test.com", "test1234"));
        System.out.println("Login successful");
    }
}