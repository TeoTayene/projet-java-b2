package main.exceptionPackage;

public class LocalityException extends Exception {
    private String error;

    public LocalityException(String e) {
        setError(e);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return "Erreur lors de la récupération des localités : " + error;
    }
}
