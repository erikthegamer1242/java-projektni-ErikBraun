package entity;

public class Stop {
    private Integer id;
    private String location;

    public Stop(Integer id, String location) {
        this.id = id;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public void printStop() {
        System.out.println("Stop Location: " + this.location);
    }
}
