package test;

import main.controllerPackage.DirectMessageController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.DirectMessageException;
import main.modelPackage.DirectMessageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectMessageControllerTest {
    private DirectMessageController directMessageController;

    @BeforeEach
    void setUp() throws ConnectionDataAccessException {
        directMessageController = new DirectMessageController();
    }

    @Test
    void getDirectMessagesByUserId() throws DirectMessageException {
        // verifie que la liste des messages privés n'est pas vide
        List<DirectMessageModel> directMessages = directMessageController.getDirectMessagesByUserId(76);
        assertFalse(directMessages.isEmpty());
    }
}