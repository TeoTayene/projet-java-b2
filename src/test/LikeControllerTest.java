package test;

import main.controllerPackage.LikeController;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.LikeSearchException;
import main.modelPackage.LikeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LikeControllerTest {
    private LikeController likeController;

    @BeforeEach
    void setUp() throws ConnectionDataAccessException {
        likeController = new LikeController();
    }

    @Test
    void getLikesBetween() throws LikeSearchException {
        // verifie que la liste des likes depuis janvier 2024 n'est pas vide
        List<LikeModel> likes = likeController.getLikesBetween(Date.valueOf("2024-01-01"), Date.valueOf("2024-05-28"));
        assertNotEquals(0, likes.size());
    }
}