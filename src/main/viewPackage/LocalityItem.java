package main.viewPackage;

public class LocalityItem {
    private String formattedLocality;
    private int localityId;

    public LocalityItem(String formattedLocality, int localityId) {
        this.formattedLocality = formattedLocality;
        this.localityId = localityId;
    }

    @Override
    public String toString() {
        return formattedLocality;
    }

    public int getLocalityId() {
        return localityId;
    }
}
