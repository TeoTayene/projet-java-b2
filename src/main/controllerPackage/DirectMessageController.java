package main.controllerPackage;

import main.businessPackage.DirectMessageManager;
import main.exceptionPackage.ConnectionDataAccessException;
import main.exceptionPackage.DirectMessageException;
import main.modelPackage.DirectMessageModel;
import java.util.List;

public class DirectMessageController {
    private DirectMessageManager directMessageManager;

    public DirectMessageController() throws ConnectionDataAccessException {
        directMessageManager = new DirectMessageManager();
    }

    public List<DirectMessageModel> getDirectMessagesByUserId(int userId) throws DirectMessageException {
        return directMessageManager.getDirectMessagesByUserId(userId);
    }
}
