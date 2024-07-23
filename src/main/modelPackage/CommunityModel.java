package main.modelPackage;

public class CommunityModel {
    private int id;
    private String name;
    private Integer creator;

    public CommunityModel(int id, String name, Integer creator) {
        setId(id);
        setName(name);
        setCreator(creator);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
