package entity;

public class Stop {
    private int id;
    private String location;

    public Stop(int id, String location) {
        this.id = id;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
