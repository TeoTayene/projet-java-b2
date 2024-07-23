package main.businessPackage;

import main.dataAccessPackage.UserDAO;
import main.dataAccessPackage.UserDAOImpl;
import main.exceptionPackage.*;
import main.modelPackage.LocalityModel;
import main.modelPackage.UserModel;
import main.utilPackage.FormValidator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class UserManager implements UserDAO {
    private UserDAO userDAO;

    public UserManager() throws ConnectionDataAccessException {
        setUserDAO(new UserDAOImpl());
    }

    private void setUserDAO(UserDAO dao) {
        this.userDAO = dao;
    }

    public Boolean createUser(UserModel user) throws UserCreationException {
        return validUser(user) ? userDAO.createUser(user) : false;
    }

    private Boolean validUser(UserModel user) throws UserCreationException {
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();
        Date dateOfBirth = user.getDateOfBirth();
        char gender = user.getGender();
        String streetAndNumber = user.getStreetAndNumber();
        String phoneNumber = user.getPhoneNumber();
        String biography = user.getBio();
        Boolean isAdmin = user.isAdmin();
        Integer home = user.getHome();

        if (FormValidator.isFieldNull(email)
                || FormValidator.isFieldNull(username)
                || FormValidator.isFieldNull(password)
                || FormValidator.isFieldNull(dateOfBirth)
                || FormValidator.isFieldNull(gender)
                || FormValidator.isFieldNull(streetAndNumber)
                || FormValidator.isFieldNull(isAdmin)
                || FormValidator.isFieldNull(home))
            throw new UserCreationException("Un ou plusieurs champs sont nuls");

        if (FormValidator.isOneStringEmpty(email, username, password, streetAndNumber))
            throw new UserCreationException("Un ou plusieurs champs sont vides");

        if (FormValidator.stringContainsSpace(email) || FormValidator.stringContainsSpace(username)
                || FormValidator.stringContainsSpace(password))
            throw new UserCreationException("Un ou plusieurs champs contiennent des espaces");

        if (!FormValidator.validStringLength(email, 1, 50))
            throw new UserCreationException("L'email doit être compris entre 1 et 50 caractères");

        if (!FormValidator.validStringLength(username, 1, 20))
            throw new UserCreationException("Le nom d'utilisateur doit être compris entre 1 et 20 caractères");

        if (!FormValidator.validStringLength(password, 6, 20))
            throw new UserCreationException("Le mot de passe doit être compris entre 6 et 20 caractères");

        if (!FormValidator.validDateOfBirth(dateOfBirth.toLocalDate()))
            throw new UserCreationException("La date de naissance n'est pas valide");

        if (!FormValidator.validGender(gender))
            throw new UserCreationException("Le genre n'est pas valide");

        if (!FormValidator.validStringLength(streetAndNumber, 1, 255))
            throw new UserCreationException("La rue et le numéro ne sont pas valide");

        if (!FormValidator.isFieldNull(phoneNumber) && !FormValidator.validStringLength(phoneNumber, 1, 20))
            throw new UserCreationException("Le numéro de téléphone n'est pas valide");

        if (!FormValidator.isFieldNull(biography) && !FormValidator.validStringLength(biography, 1, 255))
            throw new UserCreationException("La biographie n'est pas valide");

        if (!FormValidator.validBoolean(isAdmin))
            throw new UserCreationException("Le statut d'administrateur n'est pas valide");

        if (!FormValidator.validId(home))
            throw new UserCreationException("Le code postal n'est pas valide");

        return true;
    }

    @Override
    public List<UserModel> getAllUsers() throws UserSearchException {
        return userDAO.getAllUsers();
    }

    @Override
    public UserModel getUser(int id) throws UserSearchException { 
        return userDAO.getUser(id);
    }

    public Boolean updateUser(UserModel user) throws UpdateUserException, UserCreationException {
        return validUser(user) ? userDAO.updateUser(user) : false;
    }

    @Override
    public Boolean deleteUser(UserModel user) throws UserDeletionException {
        return userDAO.deleteUser(user);
    }

    @Override
    public List<LocalityModel> getLocality(String countryName) throws LocalityException {
        return userDAO.getLocality(countryName);
    }

    public List<String> getColumnsNames() throws UserSearchException {
        return userDAO.getColumnsNames();
    }

    @Override
    public String getCountryNameByHome(int userId) throws CountriesDAOException {
        return userDAO.getCountryNameByHome(userId);
    }

    public int getNbUser() throws UserSearchException {
        return userDAO.getNbUser();
    }

    public List<UserModel> getUsersByCountry(String name) throws UserSearchException {
        if (FormValidator.isFieldNull(name))
            throw new UserSearchException("Le nom du pays est nul");

        if (FormValidator.isOneStringEmpty(name))
            throw new UserSearchException("Le nom du pays est vide");

        try {
            CountriesManager countriesManager = new CountriesManager();
            if (!countriesManager.countryExists(name))
                throw new UserSearchException("Le pays " + name + " n'existe pas");
        } catch (ConnectionDataAccessException | CountriesDAOException e) {
            throw new UserSearchException(e.toString());
        }

        return userDAO.getUsersByCountry(name);
    }

    public List<UserModel> getUsersByAge(Date startDateOfBirth, Date endDateOfBirth) throws UserSearchException {
        int MAX_USERS = 100;
        if (FormValidator.isFieldNull(startDateOfBirth) || FormValidator.isFieldNull(endDateOfBirth))
            throw new UserSearchException("Les dates de naissance sont nulles");

        if (!FormValidator.validDateOfBirth(startDateOfBirth.toLocalDate()) || !FormValidator.validDateOfBirth(endDateOfBirth.toLocalDate()))
            throw new UserSearchException("Les dates de naissance ne sont pas valides");

        if (startDateOfBirth.after(endDateOfBirth))
            throw new UserSearchException("La date de début doit être antérieure à la date de fin");

        if (endDateOfBirth.toLocalDate().isAfter(Date.valueOf(LocalDate.now()).toLocalDate()))
            throw new UserSearchException("La date de fin ne peut pas être dans le futur");

        List<UserModel> users = userDAO.getUsersByAge(startDateOfBirth, endDateOfBirth);

        if (users.isEmpty())
            throw new UserSearchException("Aucun utilisateur trouvé");

        if (users.size() > MAX_USERS)
            throw new UserSearchException("Trop d'utilisateurs trouvés");

        return users;
    }

    @Override
    public Boolean login(int id, String email, String password) throws LoginException {
        return userDAO.login(id, email, password);
    }
}
