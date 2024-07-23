package main.exceptionPackage;

public class LoginException extends Exception {
    private String error;

    public LoginException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Erreur lors de la connexion de l'utilisateur : " + error;
    }
}

